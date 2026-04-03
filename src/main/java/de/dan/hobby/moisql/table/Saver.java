package de.dan.hobby.moisql.table;

import de.dan.hobby.moisql.datatype.DataType;
import de.dan.hobby.moisql.datatype.IDataType;
import de.dan.hobby.moisql.datatype.numeric.Decimal;
import de.dan.hobby.moisql.datatype.text.VarChar;
import de.dan.hobby.moisql.tree.BPTree;
import de.dan.hobby.moisql.tree.LeafNode;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.nio.file.NoSuchFileException;
import java.util.HexFormat;
import java.util.List;
import java.util.UUID;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Daniel Laible
 * @since 0.1.5
 *
 * This class is used to save a table onto the filesystem.
 *
 * Please look at the document moi_files_description.txt to find the definition of the structure of
 * a file that is generated using this class.
 * To this point no multipart files are supported.
 */
public class Saver {

  private static final Logger logger = LoggerFactory.getLogger(Saver.class);

  public Saver(@NotNull File directory, @NotNull BPTree tree, @NotNull UUID uuid, @NotNull String name, float version)
      throws IOException {

    long start = System.currentTimeMillis();
    if (checkDirValid(directory)) {
      String fileName = uuid + ".moi";
      String path = directory.getAbsolutePath() + File.separator + fileName;
      FileOutputStream out = new FileOutputStream(path);

      HexFormat hexFormat = HexFormat.of();
      final byte[] locoloco = hexFormat.parseHex("10C010C0");
      out.write(locoloco);

      //This saves the version
      Decimal decimalVersion = new Decimal(version);
      out.write(decimalVersion.toByteArray());

      //This writes the number of columns
      Short numberOfColumns = (short) tree.getDataStructure().length;
      ByteBuffer numberOfColumnsBuffer = ByteBuffer.allocate(Short.BYTES);
      numberOfColumnsBuffer.putShort(numberOfColumns);
      out.write(numberOfColumnsBuffer.array());

      //This saves the part
      ByteBuffer partBuffer = ByteBuffer.allocate(Short.BYTES);
      final byte[] partInBytes = partBuffer.putShort((short) 1).array();
      out.write(partInBytes);

      //This saves the partOf
      ByteBuffer partOfBuffer = ByteBuffer.allocate(Short.BYTES);
      final byte[] partOfInBytes = partOfBuffer.putShort((short) 1).array();
      out.write(partOfInBytes);

      //This saves the next file (part x of y)
      VarChar nextFile = new VarChar("");
      short nextFileLength = (short) nextFile.getValue().length();
      ByteBuffer nextFileLengthBuffer = ByteBuffer.allocate(Short.BYTES);
      final byte[] nextFileLengthBytes = nextFileLengthBuffer.putShort(nextFileLength).array();
      out.write(nextFileLengthBytes);
      out.write(nextFile.getValue().getBytes());

      //This saves the tableName
      VarChar tableName = new VarChar(name);
      short tableNameLength = (short) tableName.getValue().length();
      ByteBuffer tableNameBuffer = ByteBuffer.allocate(Short.BYTES);
      final byte[] tableLengthBytes = tableNameBuffer.putShort(tableNameLength).array();
      out.write(tableLengthBytes);
      out.write(tableName.getValue().getBytes());

      final VarChar[] columnNames = tree.getColumnNames();
      final IDataType[] dataStructure = tree.getDataStructure();

      //This saves the columnNames to the file
      int columnsLength = tree.getColumnNames().length;
      for (int i = 0; i < columnsLength; i++) {
        short columnNameLength = (short) columnNames[i].getValue().length();
        ByteBuffer columnNameLengthBuffer = ByteBuffer.allocate(Short.BYTES);
        final byte[] columnNameLengthBytes = columnNameLengthBuffer.putShort(columnNameLength).array();
        out.write(columnNameLengthBytes);
        out.write(columnNames[i].getValue().trim().getBytes());
      }

      //This saves the dataTypes to the file
      int dataStructurLength = tree.getDataStructure().length;
      for (int i = 0; i < dataStructurLength; i++) {
        DataType type = dataStructure[i].getDataType();
        VarChar varCharType = new VarChar(type.toString());
        final String trimmed = varCharType.getValue().trim();

        short definitionLength = (short) trimmed.length();
        ByteBuffer definitionLengthBuffer = ByteBuffer.allocate(Short.BYTES);
        final byte[] definitionLengthBytes = definitionLengthBuffer.putShort(definitionLength).array();

        byte[] bytes = trimmed.getBytes(StandardCharsets.US_ASCII);
        out.write(definitionLengthBytes);
        out.write(bytes);
      }

      //Writes the data
      final LeafNode firstLeaf = tree.findFirstLeaf();
      LeafNode currentLeaf = firstLeaf;
      while (currentLeaf.getNext() != null) {
        final List<IDataType[]> rows = currentLeaf.getRows();
        for (IDataType[] row : rows) {
          for (IDataType cell : row) {
            if (cell instanceof VarChar) {
              VarChar varChar = (VarChar) cell;
              final String trimmed = varChar.getValue().trim();
              ByteBuffer cellDataLengthBuffer = ByteBuffer.allocate(Short.BYTES);
              final byte[] cellLengthBytes = cellDataLengthBuffer.putShort((short) trimmed.length()).array();
              final byte[] byteValue = trimmed.getBytes(StandardCharsets.US_ASCII);
              out.write(cellLengthBytes);
              out.write(byteValue);
            } else {
              out.write(cell.toByteArray());
            }
          }
        }
        currentLeaf = currentLeaf.getNext();
      }

      out.close();
    } else {
      throw new NoSuchFileException("directory provided unavailable");
    }
    long stop = System.currentTimeMillis();
    logger.info("Save took {} ms", (stop - start));
  }

  private boolean checkDirValid(File directory) {
    if (directory.exists() && directory.isDirectory()) {
      return true;
    }
    return false;
  }
}
