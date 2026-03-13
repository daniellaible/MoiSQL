package de.dan.hobby.moisql.datatype.text;

import de.dan.hobby.moisql.datatype.IDataType;
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

  public String getValue() {
    return value;
  }

  @Override
  public String toString(){
    return value;
  }

  @Override
  public String getDataType() {
    return "TEXT";
  }
}
