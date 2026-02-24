package de.dan.hobby.moisql.datatype;

import de.dan.hobby.moisql.tool.TryParseBigInt;
import de.dan.hobby.moisql.tool.TryParseInt;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Daniel Laible
 * @since 0.0.1
 *
 * An abstract class that represents all different datatypes of this application
 *
 */
public abstract class NumericDataTypes {

  private static final Logger logger = LoggerFactory.getLogger(NumericDataTypes.class);

  private Number value;

  public NumericDataTypes(Number value, DataType dt){
    this.value = 0;
    if(dt.equals(DataType.BIGINT)){
      final Optional<Long> parsed = TryParseBigInt.parse(value);
      if(parsed.isEmpty()) {
        logger.warn("trying to save a BigInt that's not a BigInt: {} ", value);
      }
      value = parsed.get();

    }else if(dt.equals(DataType.INT)){
      final Optional<Integer> parsed = TryParseInt.parse(value);
      if(parsed.isEmpty()) {
        logger.warn("trying to save a Int that's not a Int: {} ", value);
      }
      value = parsed.get();
    }

  }

  public Number getValue(){
    return value;
  }

  public abstract String getName();

}
