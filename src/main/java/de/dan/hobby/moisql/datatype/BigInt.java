package de.dan.hobby.moisql.datatype;

public class BigInt extends NumericDataType {

  public BigInt(Long value) {
    super(value, DataType.BIGINT);
  }

  public Number getValue() {
    return super.getValue();
  }

  public String getName() {
    return "BIGINT";
  }
}
