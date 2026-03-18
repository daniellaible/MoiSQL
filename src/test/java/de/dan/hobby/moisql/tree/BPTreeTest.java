package de.dan.hobby.moisql.tree;

import static org.junit.jupiter.api.Assertions.assertThrows;

import de.dan.hobby.moisql.datatype.IDataType;
import de.dan.hobby.moisql.datatype.numeric.BigInt;
import de.dan.hobby.moisql.datatype.text.VarChar;
import org.junit.jupiter.api.Test;

class BPTreeTest {

  @Test
  public void createBtTree() {
    BPTree tree = new BPTree(3);
  }

  @Test
  public void createBtTreeWithBadMagnitude() {
    assertThrows(IllegalArgumentException.class, () -> new BPTree(2));
  }

  @Test
  public void insertAndDeleteBPTree() {

    BPTree tree = new BPTree(3);
    IDataType[] row1 = new IDataType[]{new BigInt(10L), new VarChar("ten")};
    tree.insertRow(row1);

    IDataType[] row2 = new IDataType[]{new BigInt(100L), new VarChar("hundred")};
    tree.insertRow(row2);

    IDataType[] row3 = new IDataType[]{new BigInt(75L), new VarChar("seventyfive")};
    tree.insertRow(row3);

    IDataType[] row4 = new IDataType[]{new BigInt(85L), new VarChar("eightyfive")};
    tree.insertRow(row4);

    IDataType[] row5 = new IDataType[]{new BigInt(95L), new VarChar("nintyfive")};
    tree.insertRow(row5);

    IDataType[] row6 = new IDataType[]{new BigInt(105L), new VarChar("hundredfive")};
    tree.insertRow(row6);

    IDataType[] row7 = new IDataType[]{new BigInt(106L), new VarChar("hundredsix")};
    tree.insertRow(row7);
    tree.printTree();
    System.out.println();

/*    tree.delete(10);
    tree.delete(85);
    tree.printTree();*/
  }
}