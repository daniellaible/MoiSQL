package de.dan.hobby.moisql.datatype.text;

import de.dan.hobby.moisql.datatype.DataType;
import de.dan.hobby.moisql.datatype.IDataType;

/**
 * @author Daniel Laible
 * @since 0.0.2
 * <p>
 * VarChar is a wrapper class for a char[255].
 * VarChar always uses 255 characters.
 */
public class VarChar implements IDataType {

  //TODO: add logger for value.length > 255;

  private char[] value;

  private DataType datatype = DataType.VARCHAR;

  public VarChar(String value) {
    if (value.length() > 255) {
      return;
    }

    this.value = new char[255];
    char[] chars = value.toCharArray();

    for (int i = 0; i < chars.length; i++) {
      if (chars[i] > 127) {
        chars[i] = '?';
      }
      this.value[i] = chars[i];
    }
  }

  public String getValue() {
    String returnValue =  String.valueOf(value);
    return returnValue.trim();
  }

  public String getDataType() {
    return "VARCHAR";
  }

  @Override
  public String toString(){
    return getValue();
  }
}
