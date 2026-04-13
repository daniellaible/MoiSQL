package de.dan.hobby.moisql.datatype.text;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import de.dan.hobby.moisql.datatype.numeric.Int;
import java.util.Arrays;
import org.junit.jupiter.api.Test;

class VarCharTest {

  @Test
  void testNormal(){
    VarChar varchar = new VarChar("Hallo Bob");
    assertThat(varchar.getDataType()).isEqualTo("VARCHAR");
    assertThat(varchar.getValue().trim()).isEqualTo("Hallo Bob");
  }

  @Test
  void testToByteArray(){
    VarChar number = new VarChar("hallo");
    byte[] bytes = number.toByteArray();
    final char[] result = number.fromByteArray(bytes);
    String resulted = new String(result).trim();
    assertEquals(new String("hallo"),resulted);
  }

}