package de.dan.hobby.moisql.datatype.text;

import de.dan.hobby.moisql.datatype.DataType;
import de.dan.hobby.moisql.datatype.IDataType;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * @author Daniel Laible
 * @since 0.1.4
 *
 * Text is the wrapper class for String values.
 */
public class Text implements IDataType {

  private String value;


  public Text(String value) {
    byte[] bytes = value.getBytes();
    this.value = new String(bytes, StandardCharsets.UTF_8);
  }


  public String getText() {
    return value;
  }


  @Override
  public String toString(){
    return value;
  }

  /**
   *
   * @return {@link de.dan.hobby.moisql.datatype.DataType}
   */
  @Override
  public DataType getDataType() {
    return DataType.TEXT;
  }


  @Override
  public byte[] toByteArray() {
    Charset charset = StandardCharsets.UTF_8;
    return value.getBytes(charset);
  }

  @Override
  public String fromByteArray(byte[] bytes) {
    return new String(bytes, StandardCharsets.UTF_8);
  }

  public Number getValue() {
    return value.hashCode();
  }


}
