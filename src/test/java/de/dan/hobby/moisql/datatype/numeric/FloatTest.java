package de.dan.hobby.moisql.datatype.numeric;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
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

  @Test
  void testToByteArray(){
    Float number = new Float(13.1415d);
    byte[] bytes = number.toByteArray();
    System.out.println(Arrays.toString(bytes));
    final Double result = number.fromByteArray(bytes);
    System.out.println(result);
    assertEquals(13.1415d, result);
  }

}