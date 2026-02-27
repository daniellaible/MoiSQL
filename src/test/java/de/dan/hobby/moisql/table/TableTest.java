package de.dan.hobby.moisql.table;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import de.dan.hobby.moisql.datatype.IDataType;
import de.dan.hobby.moisql.datatype.numeric.BigInt;
import de.dan.hobby.moisql.datatype.text.VarChar;
import org.junit.jupiter.api.Test;

class TableTest {

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

}