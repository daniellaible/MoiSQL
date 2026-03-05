package de.dan.hobby.moisql.datatype.numeric;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class BigIntTest {

  @Test
  void testCreateBigInt() {
    BigInt number = new BigInt(100L);
    assertEquals(Long.class, number.getValue().getClass());
  }

  @Test
  void testName() {
    BigInt number = new BigInt(100L);
    assertEquals("BIGINT", number.getDataType());
  }
}
