package de.dan.hobby.moisql.datatype;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.Test;

public class SmallIntTest {

  @Test
  void testSmallInt(){
    SmallInt shorty = new SmallInt(5);
    assertThat(shorty.getName()).isEqualTo("SMALLINT");
    assertThat(shorty.getValue()).isEqualTo((short)5);
  }

}
