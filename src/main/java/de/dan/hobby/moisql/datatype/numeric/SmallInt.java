package de.dan.hobby.moisql.datatype.numeric;

import de.dan.hobby.moisql.datatype.DataType;

/**
 * @author Daniel Laible
 * @since 0.0.2
 *
 * SmallInt is the wrapper class of a short value.
 * Be careful of widening primitive conversions
 * the Java compiler does.
 */
public class SmallInt extends NumericDataType {

  public SmallInt(Number value) {
    super(value, DataType.SMALLINT);
  }

  public Short getValue()
  {
    return value.shortValue();
  }

  @Override
  public String getDataType() {
    return "SMALLINT";
  }

  @Override
  public String toString(){
    return String.valueOf(value.shortValue());
  }
}
