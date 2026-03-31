package de.dan.hobby.moisql.datatype.bool;

import de.dan.hobby.moisql.datatype.DataType;
import de.dan.hobby.moisql.datatype.IDataType;
import de.dan.hobby.moisql.datatype.text.VarChar;

/**
 * @author Daniel Laible
 * @since 0.1.4
 *
 * Bool is the wrapper class of a boolean value.
 */
public class Bool implements IDataType {

  private boolean value;


  public Bool(boolean value) {
    this.value = value;
  }


  public Boolean getValue() {
    return value;
  }


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

  //TODO need implementation
  @Override
  public Object fromByteArray(byte[] bytes) {
    return null;
  }
}
