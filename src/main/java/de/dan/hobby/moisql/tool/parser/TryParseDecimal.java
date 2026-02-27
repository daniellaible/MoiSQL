package de.dan.hobby.moisql.tool.parser;

import java.util.Optional;

public class TryParseDecimal {

  public static Optional<Float> parse(Number input) {
    try{
      return Optional.of(input.floatValue());
    }catch(NumberFormatException e){
      return Optional.empty();
    }
  }

  public static Optional<Float> parse(String input) {
    try{
      return Optional.of(Float.parseFloat(input));
    }catch(NumberFormatException e){
      return Optional.empty();
    }
  }

}
