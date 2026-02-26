package de.dan.hobby.moisql.datatype.numeric;

import de.dan.hobby.moisql.datatype.DataType;

public class SmallInt extends NumericDataType {

  public SmallInt(Number value) {
    super(value, DataType.SMALLINT);
  }

  public Number getValue() {
    return super.getValue();
  }

  @Override
  public String getName() {
    return "SMALLINT";
  }
}
