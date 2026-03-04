package de.dan.hobby.moisql.datatype.text;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.Test;

class VarCharTest {

  @Test
  void testNormal(){
    VarChar varchar = new VarChar("Hallo Bob");
    assertThat(varchar.getDataType()).isEqualTo("VARCHAR");
    assertThat(varchar.getValue().trim()).isEqualTo("Hallo Bob");
  }

}