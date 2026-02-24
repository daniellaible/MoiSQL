package de.dan.hobby.moisql.datatype;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import org.junit.jupiter.api.Test;

class BigIntTest {

  @Test
  void testBigInt() {
    BigInt biggy = new BigInt(5L);
    assertThat(biggy.getName()).isEqualTo("BIGINT");
    assertThat(biggy.getValue()).isEqualTo(5L);
  }
}