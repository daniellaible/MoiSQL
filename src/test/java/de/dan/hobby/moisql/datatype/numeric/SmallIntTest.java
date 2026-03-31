package de.dan.hobby.moisql.datatype.numeric;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
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
    assertEquals("SMALLINT", number.getDataType());
  }

  @Test
  void testToByteArray(){
    SmallInt number = new SmallInt(42);
    byte[] bytes = number.toByteArray();
    System.out.println(Arrays.toString(bytes));
    final Short result = number.fromByteArray(bytes);
    System.out.println(result);
    assertEquals((short)42, result);
  }


}