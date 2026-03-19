package de.dan.hobby.moisql.tool.parser;

import java.util.Optional;

/**
 * @author Daniel Laible
 * @since 0.0.2
 *
 * This is a helper class that implements a parser
 *
 * You will get an Optional that contains a double
 * if the input is actually a double. If the class
 * is unable to parse the double and a NumberFormatException is thrown
 * and empty Optional is returned.
 */
public class TryParseFloat {

  /**
   * This parses a double wrapped in a Number and returns an optional
   *
   * @param Number that contains a double
   * @return an Optional that contains the Double if no NumberFormatException is thrown
   */
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
