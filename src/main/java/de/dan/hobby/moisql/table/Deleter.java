package de.dan.hobby.moisql.table;

import de.dan.hobby.moisql.datatype.IDataType;
import de.dan.hobby.moisql.tree.BPTree;
import org.jetbrains.annotations.NotNull;

/**
 * @since 0.1.4
 * @author Daniel Laible
 *
 * This class handles all the delete functionality of a table
 */
public class Deleter {

  /**
   * Constructor that does all the work. No need to run certain methods.
   * @param tree
   * @param id
   */
  public Deleter(@NotNull BPTree tree, int id) {
    if(tree.findRow(id) != null) {
      tree.delete(id);
    }
  }
}
