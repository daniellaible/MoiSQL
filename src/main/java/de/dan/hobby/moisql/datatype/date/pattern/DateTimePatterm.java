package de.dan.hobby.moisql.datatype.date.pattern;

public enum DateTimePatterm {
  YYYYMMDDTHH_MM_SS_SSS("YYYYMMDDThh:mm:ss:SSS"),
  DDMMYYYYTHH_MM_SS_SSS("DDMMYYYYThh:mm:ss:SSS"),
  DD_MM_YYYYTHH_MM_SS_SSS("DD.MM.YYYYThh:mm:ss:SSS");

  public final String label;

  private DateTimePatterm(String label){
    this.label = label;
  }

}
