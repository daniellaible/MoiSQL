package de.dan.hobby.moisql.tool;

import java.util.Optional;

public class TryParseBigInt {

  public static Optional<Long> parse(Number input) {
    try{
      return Optional.of(input.longValue());
    }catch(NumberFormatException e){
      return Optional.empty();
    }
  }

  public static Optional<Long> parse(String input) {
    try{
      return Optional.of(Long.parseLong(input));
    }catch(NumberFormatException e){
      return Optional.empty();
    }
  }

}
