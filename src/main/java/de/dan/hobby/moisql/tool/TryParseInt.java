package de.dan.hobby.moisql.tool;

import java.util.Optional;

public class TryParseInt {

  public static Optional<Integer> parse(Number number){
    try {
      return Optional.of(number.intValue());
    }catch(NumberFormatException e){
      return Optional.empty();
    }
  }

  public static Optional<Integer> parse(String value){
    try{
      return Optional.of(Integer.parseInt(value));
    }catch(NumberFormatException e){
      return Optional.empty();
    }
  }

}
