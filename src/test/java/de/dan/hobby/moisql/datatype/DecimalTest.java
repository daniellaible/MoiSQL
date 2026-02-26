package de.dan.hobby.moisql.datatype;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import de.dan.hobby.moisql.datatype.numeric.Decimal;
import org.junit.jupiter.api.Test;

public class DecimalTest {

  @Test
  void testDecimal() {
    Decimal decimal = new Decimal(5.5f);
    assertThat(decimal.getName()).isEqualTo("DECIMAL");
    assertThat(decimal.getValue()).isEqualTo(5.5f);
  }

}
