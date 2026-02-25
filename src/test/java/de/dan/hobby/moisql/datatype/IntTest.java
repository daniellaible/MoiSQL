package de.dan.hobby.moisql.datatype;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.Test;

class IntTest {

  @Test
  void testBigInt() {
    Int intValue = new Int(5);
    assertThat(intValue.getName()).isEqualTo("INT");
    assertThat(intValue.getValue()).isEqualTo(5);
  }

}