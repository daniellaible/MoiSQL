package de.dan.hobby.moisql.datatype.numeric;

import de.dan.hobby.moisql.datatype.DataType;

/**
 * @author Daniel Laible
 * @since 0.0.2
 *
 * BigInt is the wrapper class of a long value.
 *
 */
public class BigInt extends NumericDataType {

  public BigInt(Long value) {
    super(value, DataType.BIGINT);
  }

  public Long getValue() {
    return value.longValue();
  }

  public String getDataType() {
    return "BIGINT";
  }
}
