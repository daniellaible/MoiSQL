package de.dan.hobby.moisql.datatype.numeric;

import de.dan.hobby.moisql.datatype.DataType;

public class Float extends NumericDataType {

  public Float(Number value) {
    super(value, DataType.FLOAT);
  }

  public Number getValue() {
    return super.getValue();
  }

  @Override
  public String getName() {
    return "FLOAT";
  }
}
