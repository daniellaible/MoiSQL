package de.dan.hobby.moisql.datatype.date;


import de.dan.hobby.moisql.datatype.DataType;
import de.dan.hobby.moisql.datatype.date.pattern.TimePattern;
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
 * This class represents a timestamp in the database. All timesstamps are stored with a certain
 * timezone. If no timezone is given it will default to UTC.
 * All Date datatypes e.g. Date, Time and DateTime use epoch-millis to store the
 * instant. The only difference is how getDate() / getTime() / getDateTime method
 * format the underlying data. If you want to retrieve the epoch value then you
 * need to use the getValue() method.
 */
public class Time extends DateDataType {

  public Time(long value) {
    super(value, DataType.TIME);
  }

  public Time(long value, ZoneId zone) {
    super(value, DataType.TIME, zone);
  }

  public String getTime(TimePattern pattern){
    Instant instant = Instant.ofEpochMilli(value);
    ZonedDateTime zonedDateTime = instant.atZone(ZoneId.of(zoneId.getId()));
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern.toString());
    return zonedDateTime.format(formatter);
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

  @Override
  public Long getValue() {
    return value;
  }

  /**
   *
   * @return {@link de.dan.hobby.moisql.datatype.DataType}
   */
  @Override
  public DataType getDataType() {
    return DataType.TIME;  }

  @Override
  public ZoneId getTimeZone() {
    return zoneId;
  }

  @Override
  public String toString(){
    return String.valueOf(value.longValue());
  }

}

