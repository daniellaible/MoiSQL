package de.dan.hobby.moisql.table;

import de.dan.hobby.moisql.datatype.DataType;
import de.dan.hobby.moisql.datatype.IDataType;
import de.dan.hobby.moisql.datatype.text.VarChar;
import de.dan.hobby.moisql.tree.BPTree;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.util.ArrayList;
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
    if (checkDirValid(directory)) {
      //String uuidAsString = uuid.toString().replace("-", "");
      String fileName = uuid + ".moi";
      String path = directory.getAbsolutePath() + File.separator + fileName;
      FileOutputStream out = new FileOutputStream(path);

      VarChar tableName = new VarChar(name);

      out.write(tableName.toByteArray());

      final VarChar[] columnNames = tree.getColumnNames();
      final IDataType[] dataStructure = tree.getDataStructure();

      int columnsLength = tree.getColumnNames().length;
      for(int i = 0; i < 65; i++) {
        if(i < columnsLength ) {
          out.write(columnNames[i].toByteArray());
        }else{
          out.write(new VarChar("").toByteArray());
        }
      }

      int dataStructurLength = tree.getDataStructure().length;
      for(int i = 0; i < 65; i++) {
        if(i < dataStructurLength) {
          DataType type = dataStructure[i].getDataType();
          byte[] bytes = type.toString().getBytes();
          if(bytes.length < 255){
            byte[] temp = new byte[255];
            for(int j = 0; j < bytes.length; j++) {
              temp[j] = bytes[j];
            }
            bytes = temp;
          }

          out.write(bytes);
        }else{
          out.write(new VarChar("").toByteArray());
        }
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
  }

  private boolean checkDirValid(File directory) {
    if (directory.exists() && directory.isDirectory()) {
      return true;
    }
    return false;
  }
}
