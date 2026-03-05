package de.dan.hobby.moisql.datatype.numeric;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class FloatTest {

  @Test
  void testFloat() {
    Float number = new Float(100.5d);
    assertEquals(Double.class, number.getValue().getClass());
  }

  @Test
  void testName() {
    Float number = new Float(100);
    assertEquals("FLOAT", number.getDataType());
  }

}