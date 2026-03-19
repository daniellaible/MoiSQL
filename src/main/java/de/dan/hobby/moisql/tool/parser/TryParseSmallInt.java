package de.dan.hobby.moisql.tool.parser;

import java.util.Optional;

/**
 * @author Daniel Laible
 * @since 0.0.2
 *
 * This is a helper class that implements a parser
 *
 * You will get an Optional that contains a short
 * if the input is actually a short. If the class
 * is unable to parse the short and a NumberFormatException is thrown
 * an empty Optional is returned.
 */
public class TryParseSmallInt {

  /**
   * This parses a short wrapped in a Number and returns an optional
   *
   * @param Number that contains a short
   * @return an Optional that contains the Short if no NumberFormatException is thrown
   */
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
