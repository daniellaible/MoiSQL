package de.dan.hobby.moisql.table;

import de.dan.hobby.moisql.datatype.DataType;
import de.dan.hobby.moisql.datatype.IDataType;
import de.dan.hobby.moisql.datatype.text.VarChar;
import de.dan.hobby.moisql.tree.BPTree;
import de.dan.hobby.moisql.tree.LeafNode;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.util.List;
import java.util.UUID;
import org.jetbrains.annotations.NotNull;

/**
 * @author Daniel Laible
 * @since 0.1.5
 * <p>
 * This class is used to save a table onto the filesystem.
 */
public class Saver {

  public Saver(@NotNull File directory, @NotNull BPTree tree, @NotNull UUID uuid, @NotNull String name)
      throws IOException {
    long start = System.currentTimeMillis();
    if (checkDirValid(directory)) {
      String fileName = uuid + ".moi";
      String path = directory.getAbsolutePath() + File.separator + fileName;
      FileOutputStream out = new FileOutputStream(path);

      //This saves the
      VarChar tableName = new VarChar(name);
      out.write(tableName.toByteArray());

      final VarChar[] columnNames = tree.getColumnNames();
      final IDataType[] dataStructure = tree.getDataStructure();

      //This saves the rowNames to the file
      int columnsLength = tree.getColumnNames().length;
      for (int i = 0; i < 65; i++) {
        if (i < columnsLength) {
          out.write(columnNames[i].toByteArray());
        } else {
          out.write(new VarChar("").toByteArray());
        }
      }

      //This saves the dataTypes to the file
      int dataStructurLength = tree.getDataStructure().length;
      for (int i = 0; i < 65; i++) {
        if (i < dataStructurLength) {
          DataType type = dataStructure[i].getDataType();
          VarChar varCharType = new VarChar(type.toString());
          byte[] bytes = varCharType.toByteArray();
          out.write(bytes);
        } else {
          out.write(new VarChar("").toByteArray());
        }
      }

      final LeafNode firstLeaf = tree.findFirstLeaf();
      LeafNode currentLeaf = firstLeaf;
      while(currentLeaf.getNext() != null){
        final List<IDataType[]> rows = currentLeaf.getRows();
        for(IDataType[] row : rows){
          for(IDataType type : row){
            out.write(type.toByteArray());
          }
        }
        currentLeaf = currentLeaf.getNext();
      }

/*        final LeafNode firstLeaf = tree.findFirstLeaf();
        LeafNode currentLeaf = firstLeaf;
        while(currentLeaf.getNext() != null){
          final List<IDataType[]> rows = currentLeaf.getRows();
          for(IDataType[] row : rows){
            for(IDataType cell : row){

            }
          }
        }*/

      out.close();
    } else {
      throw new NoSuchFileException("directory provided unavailable");
    }
    long stop = System.currentTimeMillis();
    System.out.println("Save took " + (stop - start) + "ms");
  }

  private boolean checkDirValid(File directory) {
    if (directory.exists() && directory.isDirectory()) {
      return true;
    }
    return false;
  }
}
