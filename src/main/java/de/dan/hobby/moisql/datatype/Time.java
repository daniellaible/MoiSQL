package de.dan.hobby.moisql.datatype;


import java.time.ZoneId;

public class Time extends DateDataType{

  public Time(long value) {
    super(value, DataType.TIME);
  }

  public Time(long value, ZoneId zone) {
    super(value, DataType.TIME, zone);
  }

  @Override
  public long getValue() {
    return 0;

  }

  @Override
  public DataType getDataType() {
    return null;
  }

  @Override
  public ZoneId getTimeZone() {
    return null;
  }

  @Override
  public String getName() {
    return "";
  }
}

