package de.dan.hobby.moisql.tool.parser;

import java.util.Optional;

public class TryParseFloat {

  public static Optional<Double> parse(Number value){
    try {
      return Optional.of(value.doubleValue());
    }catch(NumberFormatException e){
      return Optional.empty();
    }
  }

  public static Optional<Double> parse(String value){
    try{
      return Optional.of(Double.parseDouble(value));
    }catch(NumberFormatException e){
      return Optional.empty();
    }
  }

}
