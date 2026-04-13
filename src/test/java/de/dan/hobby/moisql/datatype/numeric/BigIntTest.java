package de.dan.hobby.moisql.datatype.numeric;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
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

  @Test
  void testToByteArray(){
    BigInt number = new BigInt(12345678987654321L);
    byte[] bytes = number.toByteArray();
    System.out.println(Arrays.toString(bytes));
    final Long result = number.fromByteArray(bytes);
    System.out.println(result);
    assertEquals(12345678987654321L, result);
  }

  @Test
  void testToByteArrayWithZero(){
    BigInt number = new BigInt(0L);
    byte[] bytes = number.toByteArray();
    System.out.println(Arrays.toString(bytes));
    final Long result = number.fromByteArray(bytes);
    System.out.println(result);
    assertEquals(0L, result);
  }

  @Test
  void testToByteArrayWithNegative(){
    BigInt number = new BigInt(-12345678987654321L);
    byte[] bytes = number.toByteArray();
    System.out.println(Arrays.toString(bytes));
    final Long result = number.fromByteArray(bytes);
    System.out.println(result);
    assertEquals(-12345678987654321L, result);
  }
}
