package de.dan.hobby.moisql.table;

import de.dan.hobby.moisql.tree.BPTree;
import java.io.File;
import java.nio.file.NoSuchFileException;
import java.util.UUID;
import org.jetbrains.annotations.NotNull;

/**
 * @author Daniel Laible
 * @since 0.1.5
 *
 * This class is used to save a table onto the filesystem.
 */
public class Saver {

  public Saver(@NotNull File directory,@NotNull BPTree tree,@NotNull UUID uuid) throws NoSuchFileException {
      if(checkDirValid(directory)){

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
