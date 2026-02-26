package de.dan.hobby.moisql.datatype.date;

import de.dan.hobby.moisql.datatype.DataType;
import de.dan.hobby.moisql.datatype.pattern.DateTimePatterm;
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
  public long getValue() {
    return value;
  }

  @Override
  public DataType getDataType() {
    return DataType.DATETIME;
  }

  @Override
  public ZoneId getTimeZone() {
    return zoneId;
  }

  @Override
  public String getName() {
    return "DATETIME";
  }
}
