package de.dan.hobby.moisql.datatype;

public class Int extends NumericDataTypes{

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
