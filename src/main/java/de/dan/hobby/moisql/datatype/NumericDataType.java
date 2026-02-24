package de.dan.hobby.moisql.datatype;

import de.dan.hobby.moisql.tool.TryParseBigInt;
import de.dan.hobby.moisql.tool.TryParseDecimal;
import de.dan.hobby.moisql.tool.TryParseInt;
import de.dan.hobby.moisql.tool.TryParseSmallInt;
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
public abstract class NumericDataType implements IDataType{

  private static final Logger logger = LoggerFactory.getLogger(NumericDataType.class);

  private Number value;

  public NumericDataType(Number value, DataType dt){
    this.value = 0;
    if(dt.equals(DataType.BIGINT)){
      final Optional<Long> parsed = TryParseBigInt.parse(value);
      if(parsed.isEmpty()) {
        logger.warn("trying to save a BigInt that's not a BigInt: {} ", value);
      }
      this.value = parsed.get();

    }else if(dt.equals(DataType.INT)){
      final Optional<Integer> parsed = TryParseInt.parse(value);
      if(parsed.isEmpty()) {
        logger.warn("trying to save a Int that's not a Int: {} ", value);
      }
      this.value = parsed.get();

    }else if(dt.equals(DataType.SMALLINT)){
      final Optional<Short> parsed = TryParseSmallInt.parse(value);
      if(parsed.isEmpty()) {
        logger.warn("trying to save a SmallInt that's not a SmallInt: {} ", value);
      }
      this.value = parsed.get();

    }else if(dt.equals(DataType.DECIMAL)) {
      final Optional<Float> parsed = TryParseDecimal.parse(value);
      if (parsed.isEmpty()) {
        logger.warn("trying to save a Decimal that's not a Decimal: {} ", value);
      }
      this.value = parsed.get();
    }

  }

  public Number getValue(){
    return value;
  }

  public abstract String getName();

}
