package de.dan.hobby.moisql.datatype.numeric;

import de.dan.hobby.moisql.datatype.DataType;

/**
 * @author Daniel Laible
 * @since 0.0.2
 *
 * Float is the wrapper class of a double value.
 * Be careful of widening primitive conversions
 * the Java compiler does.
 */
public class Float extends NumericDataType {

  public Float(Number value) {
    super(value, DataType.FLOAT);
  }

  public Double getValue() {
    return value.doubleValue();
  }

  @Override
  public String getDataType() {
    return "FLOAT";
  }

  @Override
  public String toString(){
    return String.valueOf(value.doubleValue());
  }
}
