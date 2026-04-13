package de.dan.hobby.moisql.tool.parser;

import java.util.Optional;

/**
 * @author Daniel Laible
 * @since 0.0.2
 *
 * This is a helper class that implements a parser
 *
 * You will get an Optional that contains a long
 * if the input is actually a long. If the class
 * is unable to parse the long and a NumberFormatException is thrown
 * and empty Optional is returned.
 */
public class TryParseBigInt {

  /**
   * This parses a long wrapped in a Number and returns an Optional
   *
   * @param Number that contains a long
   * @return an Optional that contains the Long if no NumberFormatException is thrown
   */
  public static Optional<Long> parse(Number input) {
    try{
      return Optional.of(input.longValue());
    }catch(NumberFormatException e){
      return Optional.empty();
    }
  }


  /**
   * This parses a String representation of a long wrapped in a String and returns an Optional
   *
   * @param String that contains a long
   * @return an Optional that contains the Long if no NumberFormatException is thrown
   */
  public static Optional<Long> parse(String input) {
    try{
      return Optional.of(Long.parseLong(input));
    }catch(NumberFormatException e){
      return Optional.empty();
    }
  }

}
