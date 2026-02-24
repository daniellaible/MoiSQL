package de.dan.hobby.moisql.tool.parser;

import java.util.Optional;

public class TryParseSmallInt {

  public static Optional<Short> parse(Number number){
    try {
      return Optional.of(number.shortValue());
    }catch(NumberFormatException e){
      return Optional.empty();
    }
  }

  public static Optional<Short> parse(String number){
    try {
      return Optional.of(Short.valueOf(number));
    }catch(NumberFormatException e){
      return Optional.empty();
    }
  }

}
