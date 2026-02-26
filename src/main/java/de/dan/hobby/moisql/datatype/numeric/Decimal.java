package de.dan.hobby.moisql.datatype.numeric;

import de.dan.hobby.moisql.datatype.DataType;

public class Decimal extends NumericDataType {

  public Decimal(Number value) {
    super(value, DataType.DECIMAL);
  }

  public Number getValue() {
    return super.getValue();
  }

  @Override
  public String getName() {
    return "DECIMAL";
  }
}
