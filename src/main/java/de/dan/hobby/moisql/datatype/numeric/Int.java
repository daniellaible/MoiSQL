package de.dan.hobby.moisql.datatype.numeric;

import de.dan.hobby.moisql.datatype.DataType;

/**
 * @author Daniel Laible
 * @since 0.0.2
 *
 * Int is the wrapper class of a integer value.
 * Be careful of widening primitive conversions
 * the Java compiler does.
 */
public class Int extends NumericDataType {

  public Int(Number value) {
    super(value, DataType.INT);
  }

  public Integer getValue() {
    return value.intValue();
  }

  @Override
  public String getName() {
    return "INT";
  }


}
