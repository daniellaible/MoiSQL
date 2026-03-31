package de.dan.hobby.moisql.tool.parser;

import java.util.Optional;

/**
 * @author Daniel Laible
 * @since 0.0.2
 *
 * This is a helper class that implements a parser
 *
 * You will get an Optional that contains an int
 * if the input is actually an int. If the class
 * is unable to parse the int and a NumberFormatException is thrown
 * an empty Optional is returned.
 */
public class TryParseInt {

  /**
   * This parses an int wrapped in a String and returns an Optional
   *
   * @param Number that contains an int
   * @return an Optional that contains the Integer if no NumberFormatException is thrown
   */
  public static Optional<Integer> parse(Number number){
    try {
      return Optional.of(number.intValue());
    }catch(NumberFormatException e){
      return Optional.empty();
    }
  }

  /**
   * This parses a String representation of an int wrapped in a String and returns an Optional
   *
   * @param String that contains a short
   * @return an Optional that contains the short if no NumberFormatException is thrown
   */
  public static Optional<Integer> parse(String value){
    try{
      return Optional.of(Integer.parseInt(value));
    }catch(NumberFormatException e){
      return Optional.empty();
    }
  }

}
