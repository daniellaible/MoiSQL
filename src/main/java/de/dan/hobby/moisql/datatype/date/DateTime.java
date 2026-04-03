package de.dan.hobby.moisql.datatype.date;

import de.dan.hobby.moisql.datatype.DataType;
import de.dan.hobby.moisql.datatype.date.pattern.DateTimePatterm;
import de.dan.hobby.moisql.datatype.text.VarChar;
import java.nio.ByteBuffer;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class DateTime extends DateDataType{

  public DateTime(long value) {
    super(value, DataType.DATE);
  }

  public DateTime(long value, ZoneId zone) {
    super(value, DataType.DATE, zone);
  }

  public String getDateTime(DateTimePatterm pattern){
    Instant instant = Instant.ofEpochMilli(value);
    ZonedDateTime zonedDateTime = instant.atZone(ZoneId.of(zoneId.getId()));
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern.toString());
    return zonedDateTime.format(formatter);
  }

  @Override
  public Long getValue() {
    return value;
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
    return DataType.DATETIME;
  }


  @Override
  public byte[] toByteArray() {
    ByteBuffer buffer = ByteBuffer.allocate(Long.BYTES);
    buffer.putLong((long)getValue());
    return buffer.array();
  }

  //TODO need implementation
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
