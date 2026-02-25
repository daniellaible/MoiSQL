package de.dan.hobby.moisql.datatype;

public enum DatePattern {
  YYYYMMDD("YYYYMMDD"),
  YYMMDD("YYMMDD"),
  YYYY_MM_DD("YYYY-MM-DD"),
  YY_MM_DD("YY_MM_DD"),
  YYYY_MM_DD_SLASH("YYYY/MM/DD"),
  YY_MM_DD_SLASH("YY/MM/DD"),
  YYYY_MM_DD_PERIOD("YYYY.MM.DD"),
  YY_MM_DD_PERIOD("YY.MM.DD"),
  YYYY_MM_DD_COLON("YYYY:MM:DD"),
  YY_MM_DD_COLON("YY:MM:DD"),
  DDMMYYYY("DDMMYYYY"),
  DDMMYY("DDMMYY"),
  DD_MM_YYYY("DD-MM-YYYY"),
  DD_MM_YY("DD-MM-YY"),
  DD_MM_YYYY_SLASH("DD/MM/YYYY"),
  DD_MM_YY_SLASH("DD/MM/YY"),
  DD_MM_YYYY_PERIOD("DD:MM:YYYY"),
  DD_MM_YY_PERIOD("DD:MM:YY"),
  MMDDYYYY("MMDDYYYY"),
  MMDDYY("MMDDYY"),
  MM_DD_YYYY("MM_DD_YYYY"),
  MM_DD_YY("MM_DD_YY"),
  MM_DD_YYYY_SLASH("MM/DD/YYYY"),
  MM_DD_YY_SLASH("MM/DD/YY"),
  MM_DD_YYYY_DOT("MM.DD.YYYY"),
  MM_DD_YY_DOT("MM.DD.YY"),
  MM_DD_YYYY_COLON("MM:DD:YYYY"),
  MM_DD_YY_COLON("MM:DD:YY");

  public final String label;

  private DatePattern(String label){
    this.label = label;
  }
}
