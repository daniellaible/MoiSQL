package de.dan.hobby.moisql.datatype.numeric;

import static org.junit.jupiter.api.Assertions.*;

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
    assertEquals("DECIMAL", number.getName());
  }

}