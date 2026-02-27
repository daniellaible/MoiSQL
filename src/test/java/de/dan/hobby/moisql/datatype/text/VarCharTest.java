package de.dan.hobby.moisql.datatype.text;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class VarCharTest {

  @Test
  void testNormal(){
    VarChar varchar = new VarChar("Hallo Bob");
    assertThat(varchar.getName()).isEqualTo("VARCHAR");
    assertThat(varchar.getValue().trim()).isEqualTo("Hallo Bob");
  }

}