package de.dan.hobby.moisql.table;

import de.dan.hobby.moisql.datatype.IDataType;
import de.dan.hobby.moisql.datatype.text.VarChar;
import de.dan.hobby.moisql.tree.BPTree;
import de.dan.hobby.moisql.tree.LeafNode;
import java.io.File;
import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.util.UUID;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author Daniel Laible
 * @since 0.0.3
 *
 * This class represents a table.
 */
public class Table {

  private static final Logger logger = LoggerFactory.getLogger(Table.class);

  private String tableName;

  private BPTree tableTree;

  private UUID uuid;

  /**
   * With this constructor a new table in the database is created
   *
   * @param typeRow an array of IDataType which specifies the datatype of each column
   * @param columnNames an array of VarChar the names each column and is used as an identifier
   * @param tableName how this table is named
   */
  public Table(@NotNull IDataType[] typeRow,@NotNull VarChar[] columnNames, @NotNull String tableName) throws IllegalArgumentException {
    this.tableName = tableName;
    tableTree = new BPTree(3);
    tableTree.specifyDataStructure(typeRow, columnNames);
    this.uuid = generateUUID(tableName);
    if(!checkDuplicatesInNames(columnNames)){
      throw new IllegalArgumentException("Duplicate column names");
    }
  }

  private boolean checkDuplicatesInNames(VarChar[] columnNames) {
    for(int i = 0; i < columnNames.length; i++) {
      for (int j = i + 1; j < columnNames.length; j++) {
        if (columnNames[i].equals(columnNames[j]) && i != j) {
          return false;
        }
      }
    }
    return true;
  }


  /**
   * This retrives the name of the table
   *
   * @return the unique name of this table
   */
  public String getTableName(){
    return tableName;
  }


  /**
   * This retrieves the uuid of the table. The uuid is a combination of the timestamp when the
   * table was created and the name of the table.
   *
   * @return the uuid of the table
   */
  public UUID getUuid(){
    return uuid;
  }


  /**
   * This is used to insert a whole new row into the table
   * Make sure that the IDataRow[] has the same specification as the table
   * Also ensure that the first element of the row is its id and that it is
   * of type BigInt
   * @param dataRow
   */
  public void insert(IDataType[] dataRow) {
    new Inserter(dataRow, tableTree);
  }

  /**
   *
   *
   * @param id the id of a row with this id
   */
  public void delete(int id) {
    new Deleter(tableTree, id);
  }


  //TODO needs implementation
  public void edit(IDataType newValue, String rowName, int key ) {
  }


  /**
   * You can use this method to find a row in the table by providing the id
   *
   * @param id of the row you are looking for
   * @return a row of the table or null if nothing has been found
   */
  public IDataType[] find(long id) {
    return tableTree.findRow(id);
  }


  /**
   * Find the leaf with the lowest id of the table
   *
   * @return the leaf with the lowest id
   */
  public LeafNode findFirstLeaf(){
    return tableTree.findFirstLeaf();
  }

  /**
   * Saves the table / B+Tree on disc
   *
   * @param directory of a File Object that contains the directory where the table is saved
   * @throws NoSuchFileException if File Object does not contain a directory
   */
  public void save(File directory) throws IOException {
    new Saver(directory, tableTree, uuid, tableName, 1.0f);
  }


  //TODO needs implementation
  public void flush() {
  }

  //TODO needs implementation
  public void removeFromMemory() {
  }

  /**
   * Prints out the tree that stores the data of the table
   */
  public void print(){
    tableTree.printTree();
  }

  /**
   * This method retrieves the named identifier of each column
   * @return a String representation of the column names
   */
  public String getRowNames() {
    var names = tableTree.getColumnNames();
    StringBuilder sb = new StringBuilder();
    for (VarChar name : names) {
      sb.append(name.getValue() + " ");
    }
    return sb.toString().trim();
  }

  /**
   *
   * @return a String with the definition of all the column types
   */
  public IDataType[] getColumnTypes() {
    return tableTree.getDataStructure();
  }

  private UUID generateUUID(String tableName) {
    byte[] time = Long.toString(System.currentTimeMillis()).getBytes();
    byte[] nameAsBytes = tableName.getBytes();
    byte[] concat = new byte[nameAsBytes.length + time.length];
    return UUID.nameUUIDFromBytes(concat);
  }
}
