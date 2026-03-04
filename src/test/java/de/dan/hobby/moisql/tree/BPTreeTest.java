package de.dan.hobby.moisql.tree;

import static org.junit.jupiter.api.Assertions.assertThrows;

import de.dan.hobby.moisql.datatype.IDataType;
import de.dan.hobby.moisql.datatype.numeric.Int;
import de.dan.hobby.moisql.datatype.text.VarChar;
import org.junit.jupiter.api.Test;

class BPTreeTest {

  @Test
  public void createBtTree() {
    BPTree tree = new BPTree(3, new VarChar("Test"));
  }

  @Test
  public void createBtTreeWithBadMagnitude() {
    assertThrows(IllegalArgumentException.class, () -> new BPTree(2, new VarChar("Test")));
  }

  @Test
  public void insertAndDeleteBPTree() {

    BPTree tree = new BPTree(3, new VarChar("Test"));
    IDataType[] row1 = new IDataType[]{new Int(10),new VarChar("ten")};
    tree.insertRow(row1);

    IDataType[] row2 = new IDataType[]{new Int(100),new VarChar("hundred")};
    tree.insertRow(row2);

    IDataType[] row3 = new IDataType[]{new Int(75),new VarChar("seventyfive")};
    tree.insertRow(row3);

    IDataType[] row4 = new IDataType[]{new Int(85),new VarChar("eightyfive")};
    tree.insertRow(row4);

    IDataType[] row5 = new IDataType[]{new Int(95),new VarChar("nintyfive")};
    tree.insertRow(row5);

    IDataType[] row6 = new IDataType[]{new Int(105),new VarChar("hundredfive")};
    tree.insertRow(row6);

    IDataType[] row7 = new IDataType[]{new Int(106),new VarChar("hundredsix")};
    tree.insertRow(row7);
    tree.printTree();
    System.out.println();

/*    tree.delete(10);
    tree.delete(85);
    tree.printTree();*/
  }
}