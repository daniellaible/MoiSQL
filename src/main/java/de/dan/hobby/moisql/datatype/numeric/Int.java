package de.dan.hobby.moisql.datatype.numeric;

import de.dan.hobby.moisql.datatype.DataType;

public class Int extends NumericDataType {

  public Int(Number value) {
    super(value, DataType.INT);
  }

  public Number getValue() {
    return super.getValue();
  }

  @Override
  public String getName() {
    return "INT";
  }


}
