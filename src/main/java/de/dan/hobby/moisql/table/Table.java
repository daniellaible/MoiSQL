package de.dan.hobby.moisql.table;

import de.dan.hobby.moisql.datatype.IDataType;
import de.dan.hobby.moisql.datatype.text.VarChar;
import de.dan.hobby.moisql.tree.BPTree;

/**
 * @author Daniel Laible
 * @since 0.0.3
 *
 * This class represents the interface of a table.
 *
 */
public class Table {

  private BPTree tree;

  public Table(String tableName, IDataType[] typeRow, VarChar[] rownames) {
    tree = new BPTree(3, new VarChar(tableName));
    tree.specifyDataStructure(typeRow, rownames);
  }

  //TODO needs implementation
  public void insertInto(){}

  //TODO needs implementaion
  public void deleteFrom(){}

  //TODO need implementation
  public IDataType find(int key){return null;}

  //TODO need implementation
  public void save(){}

  //TODO needs implementation
  public void load(){}

  //TODO needs implementation
  public void flush(){}

  //TODO needs implementation
  public void removeFromMemory(){}

  public String getTableName(){
    return tree.getTableName();
  }

  public String getRowNames(){
    var names = tree.getColumnNames();
    StringBuilder sb = new StringBuilder();
    for(VarChar name : names){
      sb.append(name.getValue()+ " ");
    }
    return sb.toString().trim();
  }

  public String getColumnTypes() {
    final IDataType[] dataStructure = tree.getDataStructure();
    StringBuilder sb = new StringBuilder();
    for (IDataType dataType : dataStructure) {
      String type = dataType.getName();
      sb.append(type + " ");
    }
    return sb.toString().trim();
  }
}
