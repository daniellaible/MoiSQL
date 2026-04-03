package de.dan.hobby.moisql.datatype.bool;

import de.dan.hobby.moisql.datatype.DataType;
import de.dan.hobby.moisql.datatype.IDataType;
import de.dan.hobby.moisql.datatype.text.VarChar;
import java.nio.ByteBuffer;

/**
 * @author Daniel Laible
 * @since 0.1.4
 *
 * Bool is the wrapper class of a boolean value.
 */
public class Bool implements IDataType {

  private boolean value;


  /**
   * Constructor for a Bool variable
   *
   * @param value
   */
  public Bool(boolean value) {
    this.value = value;
  }


  /**
   *
   * @return Boolean the value of the variable
   */
  public Boolean getValue() {
    return value;
  }

  /**
   * String representation of the boolean value
   *
   * @return String true or false depending on the value
   */
  @Override
  public String toString(){
    return (value ? "true" : "false");
  }


  /**
   *
   * @return {@link de.dan.hobby.moisql.datatype.DataType} DataType.Bool
   */
  @Override
  public DataType getDataType() {
    return DataType.BOOL;
  }

  /**
   * This returns a byte[] with the length of 1. Only one element is in this array.
   * It is implemented in the following manner: (byte)(value? 1: 0)
   * @return array with length=1  containing either 0/1 as byte
   */
  @Override
  public byte[] toByteArray() {
    byte[] returnValue = new byte[1];
    returnValue[0] = (byte)(value? 1: 0);
    return returnValue;
  }

  /**
   * creates a variable of type Bool from a bytes[] array
   * @param bytes bytes[]-array of length 1
   * @return a Bool value containing true or false
   */
  @Override
  public Bool fromByteArray(byte[] bytes) {
    ByteBuffer buffer = ByteBuffer.allocate(1);
    buffer.put(bytes);
    buffer.rewind();
    byte[] returnValue = new byte[1];
    buffer.get(returnValue,0,1);
    boolean result = false;
    if(returnValue[0] == 1){
      result = true;
    }
    return new Bool(result);

  }
}
