package de.dan.hobby.moisql.datatype.bool;

import de.dan.hobby.moisql.datatype.IDataType;

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


  @Override
  public String getDataType() {
    return "BOOL";
  }

  //TODO need implementation
  @Override
  public byte[] toByteArray() {
    return new byte[0];
  }

  //TODO need implementation
  @Override
  public Object fromByteArray(byte[] bytes) {
    return null;
  }
}
