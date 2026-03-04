package de.dan.hobby.moisql.table;

import static org.junit.jupiter.api.Assertions.assertThrows;

import de.dan.hobby.moisql.datatype.IDataType;
import de.dan.hobby.moisql.datatype.numeric.Int;
import de.dan.hobby.moisql.datatype.text.VarChar;
import de.dan.hobby.moisql.tree.BPTree;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class InserterTest {

  private BPTree tree;

  @BeforeEach
  void generateTree(){
    BPTree generatedTree = new BPTree(3, new VarChar("TestTable"));
    IDataType[] typeStructure = new IDataType[]{new Int(0), new VarChar(""), new VarChar("")};
    VarChar[] columNames = new VarChar[]{new VarChar("Id"), new VarChar("FirstName"), new VarChar("LastName")};
    generatedTree.specifyDataStructure(typeStructure, columNames);
    tree = generatedTree;
  }

  @Test
  void testDataRowDims() {
    IDataType[] dataRow = new IDataType[]{new Int(1), new VarChar("Bob"), new VarChar("Seeger")};

    //There should be no exception, but also no return value
    Inserter inserter = new Inserter(dataRow, tree);
  }

  @Test
  void testDataRowWrongDims(){
    IDataType[] dataRow = new IDataType[]{new Int(1), new VarChar("Bob")};
    assertThrows(IllegalArgumentException.class, () -> new Inserter(dataRow, tree));
  }

  @Test
  void testDataRowWrongDataType(){
    IDataType[] dataRow = new IDataType[]{new Int(1), new VarChar("Bob"), new Int(2)};
    assertThrows(IllegalArgumentException.class, () -> new Inserter(dataRow, tree));
  }

  @Test
  void testInsertionSingleRow(){
    IDataType[] dataRow = new IDataType[]{new Int(1), new VarChar("Bob"), new VarChar("Seeger")};
    Inserter inserter = new Inserter(dataRow, tree);
    tree.printTree();
  }

}