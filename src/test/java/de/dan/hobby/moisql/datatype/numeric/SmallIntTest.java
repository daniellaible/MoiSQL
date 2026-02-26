package de.dan.hobby.moisql.datatype.numeric;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class SmallIntTest {

  @Test
  void testCreateSmallInt() {
    SmallInt number = new SmallInt(100);
    assertEquals(Short.class,number.getValue().getClass());
  }

  @Test
  void testName() {
    SmallInt number = new SmallInt(100);
    assertEquals("SMALLINT", number.getName());
  }


}