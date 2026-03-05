package de.dan.hobby.moisql.datatype.numeric;

import de.dan.hobby.moisql.datatype.DataType;

/**
 * @author Daniel Laible
 * @since 0.0.2
 *
 * Decimal is the wrapper class of a float value.
 * Be careful of widening primitive conversions
 * the Java compiler does.
 */
public class Decimal extends NumericDataType {

  public Decimal(Number value) {
    super(value, DataType.DECIMAL);
  }

  public java.lang.Float getValue() {
    return value.floatValue();
  }

  @Override
  public String getDataType() {
    return "DECIMAL";
  }

  @Override
  public String toString(){
    return String.valueOf(value.floatValue());
  }
}
