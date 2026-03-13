package de.dan.hobby.moisql.datatype.bool;

import de.dan.hobby.moisql.datatype.IDataType;

/**
 * @author Daniel Laible
 * @since 0.1.4
 *
 * Int is the wrapper class of a boolean value.
 */
public class Bool implements IDataType {

  private boolean value;

  public Bool(boolean value) {
    this.value = value;
  }

  public boolean getValue() {
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
}
