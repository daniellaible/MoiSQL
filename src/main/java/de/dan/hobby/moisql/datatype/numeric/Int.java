package de.dan.hobby.moisql.datatype.numeric;

import de.dan.hobby.moisql.datatype.DataType;
import java.nio.ByteBuffer;

///**
// * @author Daniel Laible
// * @since 0.0.2
// *
// * Int is the wrapper class of a integer value.
// * Be careful of widening primitive conversions
// * the Java compiler does.
// */
public class Int extends NumericDataType {

  public Int(Number value) {
    super(value, DataType.INT);
  }


  public Integer getValue() {
    return value.intValue();
  }


  @Override
  public String getDataType() {
    return "INT";
  }


  @Override
  public byte[] toByteArray() {
    ByteBuffer buffer = ByteBuffer.allocate(Integer.BYTES);
    buffer.putInt(0, getValue());
    return buffer.array();
  }


  @Override
  public Integer fromByteArray(byte[] bytes) {
    ByteBuffer buffer = ByteBuffer.allocate(Integer.BYTES);
    buffer.put(bytes);
    buffer.rewind();
    return buffer.getInt();
  }


  @Override
  public String toString() {
    return String.valueOf(value.intValue());
  }
}
