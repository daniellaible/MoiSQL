package de.dan.hobby.moisql.datatype.numeric;

import de.dan.hobby.moisql.datatype.DataType;
import de.dan.hobby.moisql.datatype.text.VarChar;
import java.nio.ByteBuffer;

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
  public DataType getDataType() {
    return DataType.SMALLINT;
  }


  @Override
  public byte[] toByteArray() {
    ByteBuffer buffer = ByteBuffer.allocate(Short.BYTES);
    buffer.putShort(0, getValue());
    return buffer.array();
  }

  @Override
  public Short fromByteArray(byte[] bytes) {
    ByteBuffer buffer = ByteBuffer.allocate(Short.BYTES);
    buffer.put(bytes);
    buffer.rewind();
    return buffer.getShort();
  }


  @Override
  public String toString(){
    return String.valueOf(value.shortValue());
  }
}
