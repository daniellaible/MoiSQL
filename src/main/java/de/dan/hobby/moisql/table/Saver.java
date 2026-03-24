package de.dan.hobby.moisql.table;

import de.dan.hobby.moisql.datatype.IDataType;
import de.dan.hobby.moisql.datatype.text.VarChar;
import de.dan.hobby.moisql.tree.BPTree;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.NoSuchFileException;
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
      String temp = uuid.toString().replace("-", "");
      String fileName = temp + ".moi";
      String path = directory.getAbsolutePath() + File.separator + fileName;
      BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(path));

      VarChar tableName = new VarChar(name);
      bufferedOutputStream.write(tableName.toByteArray());

      final VarChar[] columnNames = tree.getColumnNames();
      final IDataType[] dataStructure = tree.getDataStructure();

      int columnsLength = tree.getColumnNames().length;
      for(int i = 0; i < 65; i++) {
        if(i < columnsLength -1 ) {
          bufferedOutputStream.write(columnNames[i].toByteArray());
        }else{
          bufferedOutputStream.write(new VarChar("").toByteArray());
        }
      }

      int dataStructurLength = tree.getDataStructure().length;
      for(int i = 0; i < 65; i++) {
        if(i < dataStructurLength -1 ) {
          bufferedOutputStream.write(dataStructure[i].getDataType().getBytes());
        }else{
          bufferedOutputStream.write(new VarChar("").toByteArray());
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

      bufferedOutputStream.close();
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
