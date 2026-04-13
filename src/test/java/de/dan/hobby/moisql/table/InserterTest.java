package de.dan.hobby.moisql.table;

import static org.junit.jupiter.api.Assertions.assertThrows;

import de.dan.hobby.moisql.datatype.IDataType;
import de.dan.hobby.moisql.datatype.numeric.BigInt;
import de.dan.hobby.moisql.datatype.numeric.Int;
import de.dan.hobby.moisql.datatype.text.VarChar;
import de.dan.hobby.moisql.tree.BPTree;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class InserterTest {

  private BPTree tree;

  @BeforeEach
  void generateTree(){
    BPTree generatedTree = new BPTree(3);
    IDataType[] typeStructure = new IDataType[]{new BigInt(0l), new VarChar(""), new VarChar("")};
    VarChar[] columNames = new VarChar[]{new VarChar("Id"), new VarChar("FirstName"), new VarChar("LastName")};
    generatedTree.specifyDataStructure(typeStructure, columNames);
    tree = generatedTree;
  }

  @Test
  void testDataRowDims() {
    IDataType[] dataRow = new IDataType[]{new BigInt(1L), new VarChar("Bob"), new VarChar("Seeger")};

    //There should be no exception, but also no return value
    Inserter inserter = new Inserter(dataRow, tree);
  }

  @Test
  void testDataRowWrongDims(){
    IDataType[] dataRow = new IDataType[]{new BigInt(1L), new VarChar("Bob")};
    assertThrows(IllegalArgumentException.class, () -> new Inserter(dataRow, tree));
  }

  @Test
  void testDataRowWrongDataType(){
    IDataType[] dataRow = new IDataType[]{new BigInt(1L), new VarChar("Bob"), new Int(2)};
    assertThrows(IllegalArgumentException.class, () -> new Inserter(dataRow, tree));
  }

  @Test
  void testInsertionSingleRow(){
    IDataType[] dataRow = new IDataType[]{new BigInt(1L), new VarChar("Bob"), new VarChar("Seeger")};
    Inserter inserter = new Inserter(dataRow, tree);
    tree.printTree();
  }

  @Test
  void testInsertionMultiRows(){
    IDataType[] dataRow = new IDataType[]{new BigInt(1L), new VarChar("Bob"), new VarChar("Seeger")};
    IDataType[] dataRow2 = new IDataType[]{new BigInt(2L), new VarChar("Keith"), new VarChar("Richards")};
    IDataType[] dataRow3 = new IDataType[]{new BigInt(3L), new VarChar("David"), new VarChar("Bowie")};
    IDataType[] dataRow4 = new IDataType[]{new BigInt(4L), new VarChar("Paul"), new VarChar("Young")};
    IDataType[] dataRow5 = new IDataType[]{new BigInt(5L), new VarChar("Elton"), new VarChar("John")};

    Inserter inserter = new Inserter(dataRow, tree);
    Inserter inserter2 = new Inserter(dataRow2, tree);
    Inserter inserter3 = new Inserter(dataRow3, tree);
    Inserter inserter4 = new Inserter(dataRow4, tree);
    Inserter inserter5 = new Inserter(dataRow5, tree);
    tree.printTree();
  }

}