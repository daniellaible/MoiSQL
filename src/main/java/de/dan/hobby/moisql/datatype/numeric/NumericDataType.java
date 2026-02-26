package de.dan.hobby.moisql.datatype.numeric;

import de.dan.hobby.moisql.datatype.DataType;
import de.dan.hobby.moisql.datatype.IDataType;
import de.dan.hobby.moisql.tool.parser.TryParseBigInt;
import de.dan.hobby.moisql.tool.parser.TryParseDecimal;
import de.dan.hobby.moisql.tool.parser.TryParseFloat;
import de.dan.hobby.moisql.tool.parser.TryParseInt;
import de.dan.hobby.moisql.tool.parser.TryParseSmallInt;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Daniel Laible
 * @since 0.0.1
 * <p>
 * An abstract class that represents all different datatypes of this application
 */
public abstract class NumericDataType implements IDataType {

  private static final Logger logger = LoggerFactory.getLogger(NumericDataType.class);

  private Number value;

  private DataType datatype;

  public NumericDataType(Number value, DataType dataType) {
    this.value = 0;
    this.datatype = dataType;
    if (dataType.equals(DataType.BIGINT)) {
      final Optional<Long> parsed = TryParseBigInt.parse(value);
      if (parsed.isEmpty()) {
        logger.warn("trying to save a BigInt that's not a BigInt: {} ", value);
      }
      this.value = parsed.get();

    } else if (dataType.equals(DataType.INT)) {
      final Optional<Integer> parsed = TryParseInt.parse(value);
      if (parsed.isEmpty()) {
        logger.warn("trying to save a Int that's not a Int: {} ", value);
      }
      this.value = parsed.get();

    } else if (dataType.equals(DataType.SMALLINT)) {
      final Optional<Short> parsed = TryParseSmallInt.parse(value);
      if (parsed.isEmpty()) {
        logger.warn("trying to save a SmallInt that's not a SmallInt: {} ", value);
      }
      this.value = parsed.get();

    } else if (dataType.equals(DataType.DECIMAL)) {
      final Optional<java.lang.Float> parsed = TryParseDecimal.parse(value);
      if (parsed.isEmpty()) {
        logger.warn("trying to save a Decimal that's not a Decimal: {} ", value);
      }
      this.value = parsed.get();

    } else if (dataType.equals(DataType.FLOAT)) {
      final Optional<Double> parsed = TryParseFloat.parse(value);
      if (parsed.isEmpty()) {
        logger.warn("trying to save a Sql Float that's not a Sql Float: {} ", value);
      }
      this.value = parsed.get();
    }
  }


  public Number getValue() {
    if (datatype.equals(DataType.BIGINT)) {
      return value.longValue();
    } else if (datatype.equals(DataType.INT)) {
      return value.intValue();
    } else if (datatype.equals(DataType.SMALLINT)) {
      return value.shortValue();
    } else if (datatype.equals(DataType.DECIMAL)) {
      return value.floatValue();
    } else if (datatype.equals(DataType.FLOAT)) {
      return value.doubleValue();
    }
    return 0;
  }

  public abstract String getName();

}
