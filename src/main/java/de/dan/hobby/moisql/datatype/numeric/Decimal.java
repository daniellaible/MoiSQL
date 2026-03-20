package de.dan.hobby.moisql.datatype.numeric;

import de.dan.hobby.moisql.datatype.DataType;
import java.nio.ByteBuffer;

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
  public byte[] toByteArray() {
    ByteBuffer buffer = ByteBuffer.allocate(java.lang.Float.BYTES);
    buffer.putFloat(value.floatValue());
    return buffer.array();
  }


  @Override
  public java.lang.Float fromByteArray(byte[] bytes) {
    ByteBuffer buffer = ByteBuffer.allocate(java.lang.Float.BYTES);
    buffer.put(bytes);
    buffer.rewind();
    return buffer.getFloat();
  }


  @Override
  public String toString(){
    return String.valueOf(value.floatValue());
  }
}
