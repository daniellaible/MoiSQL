package de.dan.hobby.moisql.datatype.numeric;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import org.junit.jupiter.api.Test;

class IntTest {

  @Test
  void testCreateInt() {
    Int number = new Int(100);
    assertEquals(Integer.class, number.getValue().getClass());
  }

  @Test
  void testName() {
    Int number = new Int(100);
    assertEquals("INT", number.getDataType());
  }

  @Test
  void testToByteArray(){
    Int number = new Int(42000);
    byte[] bytes = number.toByteArray();
    System.out.println(Arrays.toString(bytes));
    final Integer result = number.fromByteArray(bytes);
    System.out.println(result);
    assertEquals(42000, result);
  }
}