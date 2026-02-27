package de.dan.hobby.moisql.datatype.date;

import de.dan.hobby.moisql.datatype.DataType;
import de.dan.hobby.moisql.datatype.IDataType;
import java.time.ZoneId;

public abstract class DateDataType implements IDataType {

  Long value;
  DataType datatype;
  ZoneId zoneId;

  public DateDataType(Long value, DataType datatype, ZoneId zoneId) {
    this.value = value;
    this.datatype = datatype;
    this.zoneId = zoneId;
  }

  public DateDataType(Long value, DataType datatype) {
    this(value, datatype, ZoneId.of("UTC"));
    this.value = value;
    this.datatype = datatype;
  }

  public abstract long getValue();

  public abstract DataType getDataType();

  public abstract ZoneId getTimeZone();

  public abstract String getName();
}
