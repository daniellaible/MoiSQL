package de.dan.hobby.moisql.table;

import de.dan.hobby.moisql.datatype.IDataType;
import de.dan.hobby.moisql.tree.BPTree;
import de.dan.hobby.moisql.tree.LeafNode;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.util.List;
import java.util.UUID;
import org.jetbrains.annotations.NotNull;

/**
 * @author Daniel Laible
 * @since 0.1.5
 *
 * This class is used to save a table onto the filesystem.
 */
public class Saver {

  public Saver(@NotNull File directory,@NotNull BPTree tree,@NotNull UUID uuid) throws IOException {
      if(checkDirValid(directory)){
        String temp = uuid.toString().replace("-", "");
        String fileName = temp + ".moi";
        String path = directory.getAbsolutePath() + File.separator + fileName;
        BufferedOutputStream bufferedOutputStream=new BufferedOutputStream(new FileOutputStream(path));

        final LeafNode firstLeaf = tree.findFirstLeaf();
        LeafNode currentLeaf = firstLeaf;
        while(currentLeaf.getNext() != null){
          final List<IDataType[]> rows = currentLeaf.getRows();
          for(IDataType[] row : rows){
            for(IDataType cell : row){

            }
          }
        }


        bufferedOutputStream.write(2000);
        bufferedOutputStream.close();
      }else{
        throw new NoSuchFileException("directory provided unavailable");
      }
  }

  private boolean checkDirValid(File directory) {
    if(directory.exists() && directory.isDirectory()){
      return true;
    }
    return false;
  }
}
