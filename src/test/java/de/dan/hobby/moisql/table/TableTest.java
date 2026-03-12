package de.dan.hobby.moisql.table;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import de.dan.hobby.moisql.datatype.IDataType;
import de.dan.hobby.moisql.datatype.numeric.BigInt;
import de.dan.hobby.moisql.datatype.numeric.Int;
import de.dan.hobby.moisql.datatype.text.VarChar;
import de.dan.hobby.moisql.tree.LeafNode;
import org.junit.jupiter.api.Test;

class TableTest extends BaseTest{

  @Test
  public void createTable(){
    String name = new String("Test-Table");
    IDataType[] types = new IDataType[] {new BigInt(0L), new VarChar(""), new VarChar(""), new VarChar("")};
    VarChar[] names = new VarChar[] {new VarChar("ID"), new VarChar("Name"), new VarChar("Surname"), new VarChar("Place")};
    Table table = new Table(name, types, names);

    assertThat(table).isNotNull();
    assertThat(table.getTableName()).isEqualTo("Test-Table");
    assertThat(table.getRowNames()).isEqualTo("ID Name Surname Place");
    assertThat(table.getColumnTypes()).isEqualTo("BIGINT VARCHAR VARCHAR VARCHAR");
  }

  @Test
  public void dataStructureMishapTable(){
    String name = new String("Test-Table");
    IDataType[] types = new IDataType[] {new BigInt(0L), new VarChar(""), new VarChar(""), new VarChar("")};
    VarChar[] names = new VarChar[] {new VarChar("ID"), new VarChar("Name"), new VarChar("Surname")};

    assertThrows(IllegalArgumentException.class, () -> new Table(name, types, names));
  }

  @Test
  public void findFirstLeafTest(){
    LeafNode firstLeaf = table.findFirstLeaf();
    if(firstLeaf != null && !firstLeaf.getRows().isEmpty()) {
      final IDataType[] row = firstLeaf.getRows().get(0);
      Int id = (Int) row[0];
      assertThat(id.getValue()).isEqualTo(1);

      StringBuilder sb = new StringBuilder();
      for(int i = 0; i < row.length; i++) {
        sb.append(row[i].toString() + " ");
      }
      System.out.println(sb);
    }
  }

  @Test
  public void nextLeafTest(){
    LeafNode firstLeaf = table.findFirstLeaf();
    final LeafNode next = firstLeaf.getNext();
    IDataType[] row = next.getRows().get(0);
    Int id = (Int) row[0];
    assertThat(id.getValue()).isEqualTo(3);

    StringBuilder sb = new StringBuilder();
    for(int i = 0; i < row.length; i++) {
      sb.append(row[i].toString() + " ");
    }
    System.out.println(sb);
  }

}