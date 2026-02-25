package de.dan.hobby.moisql.datatype;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

//TODO needs testing
/**
 * @author Daniel Laible
 * @since 0.0.2
 *q
 * This class represents a Date in the database. All dates are stored with certain
 * timezone. If no timezone is given it will default to UTC.
 * All Date datatypes e.g. Date, Time and DateTime use epoch-millis to store the
 * instant. The only difference is how getDate() or getTime() method
 * format the underlying data. If you want to retrieve the long value then you
 * need to use the getValue() method.
 */
public class Date extends DateDataType{

  public Date(long value) {
    super(value, DataType.DATE);
  }

  public Date(long value, ZoneId zone) {
    super(value, DataType.DATE, zone);
  }

  @Override
  public long getValue() {
    return value;
  }

  public String getDate(DatePattern pattern){
    Instant instant = Instant.ofEpochMilli(value);
    ZonedDateTime zonedDateTime = instant.atZone(ZoneId.of(zoneId.getId()));
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern.toString());
    return zonedDateTime.format(formatter);
  }

  public ZoneId getZone() {
    return zoneId;
  }

  @Override
  public DataType getDataType() {
    return datatype;
  }

  @Override
  public ZoneId getTimeZone() {
    return zoneId;
  }

  @Override
  public String getName() {
    return "DATE";
  }
}
