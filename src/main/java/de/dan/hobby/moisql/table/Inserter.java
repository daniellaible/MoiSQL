package de.dan.hobby.moisql.table;

import de.dan.hobby.moisql.datatype.IDataType;
import de.dan.hobby.moisql.datatype.numeric.Int;
import de.dan.hobby.moisql.tree.BPTree;

/**
 * @author Daniel Laible
 * @since 0.0.3
 *
 * This class is used to insert rows into a table.
 * It does the background work before actually using
 * the BPTree class to insert it into the tree.
 */
public class Inserter {


  /**
   * Constructor that does all the work. No need to run certain methods.
   *
   * @param tableRow
   * @param tree
   * @throws IllegalArgumentException
   */
  public Inserter(IDataType[] tableRow, BPTree tree) throws IllegalArgumentException{
      if(!checkDimensions(tableRow, tree)){
        throw new IllegalArgumentException("row does not fit into table");
      }
      if(!checkDataTypes(tableRow, tree)){
        throw new IllegalArgumentException("datatypes are not consistent with table");
      }

      Int tempId = (Int)tableRow[0];
      if(tree.findRow(tempId.getValue()) == null){
        tree.insertRow(tableRow);
      }
  }


  private boolean checkDataTypes(IDataType[] tableRow, BPTree tree) {
    for(int i = 0; i < tableRow.length; i++){
      if(!tableRow[i].getDataType().equals(tree.getDataStructure()[i].getDataType())){
        return false;
      }
    }
    return true;
  }


  private boolean checkDimensions(IDataType[] tableRow, BPTree tree) {
    if(tableRow.length == tree.getDataStructure().length){
      return true;
    }
    return false;
  }
}
