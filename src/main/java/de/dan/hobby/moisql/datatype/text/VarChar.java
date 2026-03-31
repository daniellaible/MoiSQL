package de.dan.hobby.moisql.datatype.text;

import de.dan.hobby.moisql.datatype.DataType;
import de.dan.hobby.moisql.datatype.IDataType;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.StandardCharsets;

/**
 * @author Daniel Laible
 * @since 0.0.2
 * <p>
 * VarChar is a wrapper class for a char[255].
 * VarChar always uses 255 characters.
 */
public class VarChar implements IDataType {

  private char[] value;

  private DataType datatype = DataType.VARCHAR;

  /**
   * Basic constructor of a VarChar variable. Make sure only ascii characters are used
   * and that the text is not longer than 255 characters
   * @param value the text that will be stored in ascii
   */
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

  @Override
  public byte[] toByteArray() {
    return new String(value).getBytes(StandardCharsets.US_ASCII);
  }

  @Override
  public char[] fromByteArray(byte[] data) {
    ByteBuffer byteBuffer = ByteBuffer.wrap(data);
    CharBuffer charBuffer = StandardCharsets.US_ASCII.decode(byteBuffer);
    char[] charArray = new char[charBuffer.remaining()];
    charBuffer.get(charArray);
    return charArray;
  }


  /**
   *
   * @return {@link de.dan.hobby.moisql.datatype.DataType}
   */
  @Override
  public DataType getDataType() {
    return DataType.VARCHAR;
  }

  @Override
  public String toString(){
    return getValue();
  }
}
