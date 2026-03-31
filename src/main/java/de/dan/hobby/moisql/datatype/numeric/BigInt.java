package de.dan.hobby.moisql.datatype.numeric;

import de.dan.hobby.moisql.datatype.DataType;
import de.dan.hobby.moisql.datatype.text.VarChar;
import java.nio.ByteBuffer;

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


  /**
   * returns the numerical value as Long
   * @return the value
   */
  public Long getValue() {
    return value.longValue();
  }


  /**
   * returns the value as a byte array
   * @return the value as byte array
   */
  @Override
  public byte[] toByteArray() {
    ByteBuffer buffer = ByteBuffer.allocate(Long.BYTES);
    buffer.putLong((long)getValue());
    return buffer.array();
  }


  /**
   * Reads the byte array as
   * @param bytes that represent a BigInt value (Long in Java)
   * @return the byte[] value as Long
   */
  @Override
  public Long fromByteArray(byte[] bytes) {
    ByteBuffer buffer = ByteBuffer.allocate(Long.BYTES);
    buffer.put(bytes);
    buffer.rewind();
    return buffer.getLong();
  }

  /**
   *
   * @return {@link de.dan.hobby.moisql.datatype.DataType}
   */
  public DataType getDataType() {
    return DataType.BIGINT;
  }

  /**
   * Returns the value as String
   * @return the value as String
   */
  @Override
  public String toString(){
    return String.valueOf(value.longValue());
  }
}
