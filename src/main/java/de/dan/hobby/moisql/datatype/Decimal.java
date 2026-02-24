package de.dan.hobby.moisql.datatype;

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
