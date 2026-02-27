package de.dan.hobby.moisql.datatype.numeric;

import static org.junit.jupiter.api.Assertions.*;

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
    assertEquals("INT", number.getName());
  }
}