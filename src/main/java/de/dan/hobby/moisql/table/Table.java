package de.dan.hobby.moisql.table;

import de.dan.hobby.moisql.datatype.IDataType;
import de.dan.hobby.moisql.datatype.text.VarChar;
import de.dan.hobby.moisql.tree.BPTree;
import de.dan.hobby.moisql.tree.LeafNode;


/**
 * @author Daniel Laible
 * @since 0.0.3
 * <p>
 * This class represents the interface of a table.
 */
public class Table {

  private BPTree tableTree;


  public Table(String tableName, IDataType[] typeRow, VarChar[] columnNames) {
    tableTree = new BPTree(3, new VarChar(tableName));
    tableTree.specifyDataStructure(typeRow, columnNames);
  }

  public void insert(IDataType[] dataRow) {
    Inserter inserter = new Inserter(dataRow, tableTree);
  }

  public void delete(int id) {
    Deleter deleter = new Deleter(tableTree, id);
  }

  //TODO needs implementation
  public void edit(IDataType newValue, String rowName, int key ) {
  }

  public IDataType[] find(int id) {
    return tableTree.findRow(id);
  }

  public LeafNode findFirstLeaf(){
    return tableTree.findFirstLeaf();
  }

  //TODO need implementation
  public void save() {
  }

  //TODO needs implementation
  public void load() {
  }

  //TODO needs implementation
  public void flush() {
  }

  //TODO needs implementation
  public void removeFromMemory() {
  }

  public void print(){
    tableTree.printTree();
  }

  public String getTableName() {
    return tableTree.getTableName();
  }

  public String getRowNames() {
    var names = tableTree.getColumnNames();
    StringBuilder sb = new StringBuilder();
    for (VarChar name : names) {
      sb.append(name.getValue() + " ");
    }
    return sb.toString().trim();
  }

  public String getColumnTypes() {
    final IDataType[] dataStructure = tableTree.getDataStructure();
    StringBuilder sb = new StringBuilder();
    for (IDataType dataType : dataStructure) {
      String type = dataType.getDataType();
      sb.append(type + " ");
    }
    return sb.toString().trim();
  }
}
