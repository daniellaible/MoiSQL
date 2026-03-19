package de.dan.hobby.moisql.tool.parser;

import java.util.Optional;

/**
 * @author Daniel Laible
 * @since 0.0.2
 *
 * This is a helper class that implements a parser
 *
 * You will get an Optional that contains a float
 * if the input is actually a float. If the class
 * is unable to parse the float and a NumberFormatException is thrown
 * and empty Optional is returned.
 */
public class TryParseDecimal {

  /**
   * This parses a float wrapped in a Number and returns an optional
   *
   * @param Number that contains a float
   * @return an Optional that contains the Float if no NumberFormatException is thrown
   */
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
