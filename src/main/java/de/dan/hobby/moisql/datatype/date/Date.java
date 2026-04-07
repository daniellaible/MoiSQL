package de.dan.hobby.moisql.datatype.date;

import de.dan.hobby.moisql.datatype.DataType;
import de.dan.hobby.moisql.datatype.date.pattern.DatePattern;
import de.dan.hobby.moisql.datatype.text.VarChar;
import java.nio.ByteBuffer;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;


/**
 * @author Daniel Laible
 * @since 0.0.2
 *q
 * This class represents a Date in the database. All dates are stored with a certain
 * timezone. If no timezone is given it will default to UTC.
 * All Date datatypes e.g. Date, Time and DateTime use epoch-millis to store the
 * instant. The only difference is how getDate() / getTime() / getDateTime method
 * format the underlying data. If you want to retrieve the long value then you
 * need to use the getValue() method.
 */
public class Date extends DateDataType {

  public Date(long value) {
    super(value, DataType.DATE);
  }

  public Date(long value, ZoneId zone) {
    super(value, DataType.DATE, zone);
  }

  @Override
  public Long getValue() {
    return value;
  }

  public String getDate(DatePattern pattern){
    Instant instant = Instant.ofEpochMilli(value);
    ZonedDateTime zonedDateTime = instant.atZone(ZoneId.of(zoneId.getId()));
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern.toString());
    return zonedDateTime.format(formatter);
  }


  @Override
  public ZoneId getTimeZone() {
    return zoneId;
  }

  /**
   *
   * @return {@link de.dan.hobby.moisql.datatype.DataType}
   */
  @Override
  public DataType getDataType() {
    return DataType.DATE;
  }


  @Override
  public byte[] toByteArray() {
    ByteBuffer buffer = ByteBuffer.allocate(Long.BYTES);
    buffer.putLong((long)getValue());
    return buffer.array();
  }

  @Override
  public Long fromByteArray(byte[] bytes) {
    ByteBuffer buffer = ByteBuffer.allocate(Long.BYTES);
    buffer.put(bytes);
    buffer.rewind();
    return buffer.getLong();
  }

  public String toString(){
    return String.valueOf(value.longValue());
  }
}
