package de.dan.hobby.moisql.datatype;

public class SmallInt extends NumericDataTypes{

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
