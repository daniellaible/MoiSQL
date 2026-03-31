package de.dan.hobby.moisql.datatype.numeric;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import org.junit.jupiter.api.Test;

class DecimalTest {

  @Test
  void testCreateDecimal() {
    Decimal number = new Decimal(100.5);
    assertEquals(java.lang.Float.class, number.getValue().getClass());
  }

  @Test
  void testName() {
    Decimal number = new Decimal(100);
    assertEquals("DECIMAL", number.getDataType());
  }


  @Test
  void testToByteArray(){
    Decimal number = new Decimal(13.1415f);
    byte[] bytes = number.toByteArray();
    System.out.println(Arrays.toString(bytes));
    final java.lang.Float result = number.fromByteArray(bytes);
    System.out.println(result);
    assertEquals(13.1415f, result);
  }

}