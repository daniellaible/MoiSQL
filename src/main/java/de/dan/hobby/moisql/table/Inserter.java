package de.dan.hobby.moisql.table;

import de.dan.hobby.moisql.datatype.IDataType;
import de.dan.hobby.moisql.datatype.numeric.Int;
import de.dan.hobby.moisql.tree.BPTree;

public class Inserter {


  public Inserter(IDataType[] tableRow, BPTree tree) throws IllegalArgumentException{
      if(!checkDimensions(tableRow, tree)){
        throw new IllegalArgumentException("row does not fit into table");
      }
      if(!checkDataTypes(tableRow, tree)){
        throw new IllegalArgumentException("datatypes are not consistent with table");
      }

      Int tempId = (Int)tableRow[0];
      //if(tree.findRow(tempId.getValue()) == null){
        tree.insertRow(tableRow);

      //}
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
