package de.dan.hobby.moisql.datatype.text;

import de.dan.hobby.moisql.datatype.DataType;
import de.dan.hobby.moisql.datatype.IDataType;


public class VarChar implements IDataType {

  private char[] value;

  private DataType datatype;

  public VarChar(String value) {
    if(value.length() >255){
      return;
    }
    this.value = new char[255];
    char[] chars = value.toCharArray();

    for(int i = 0; i < 256; i++){
      this.value[i] = '0';
    }

    for (int i = 0; i < chars.length; i++) {
      if (chars[i] > 127) {
        chars[i] = '?';
      }
      this.value[i] = chars[i];
    }
  }

  public String getName() {
    return "VARCHAR";
  }
}
