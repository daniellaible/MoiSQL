package de.dan.hobby.moisql.datatype.date.pattern;

public enum TimePattern {
  hh_mm("hh:mm"),
  hh_mm_ss("hh:mm:ss"),
  hh_mm_ss_sss("hh:mm:ss:SSS");

  public final String label;

  private TimePattern(String label){
    this.label = label;
  }
}
