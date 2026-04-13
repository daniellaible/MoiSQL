package de.dan.hobby.moisql.tool.parser;

import de.dan.hobby.moisql.datatype.DataType;
import de.dan.hobby.moisql.datatype.IDataType;
import de.dan.hobby.moisql.datatype.bool.Bool;
import de.dan.hobby.moisql.datatype.date.Date;
import de.dan.hobby.moisql.datatype.date.DateTime;
import de.dan.hobby.moisql.datatype.date.Time;
import de.dan.hobby.moisql.datatype.numeric.BigInt;
import de.dan.hobby.moisql.datatype.numeric.Decimal;
import de.dan.hobby.moisql.datatype.numeric.Float;
import de.dan.hobby.moisql.datatype.numeric.Int;
import de.dan.hobby.moisql.datatype.numeric.SmallInt;
import de.dan.hobby.moisql.datatype.text.Text;
import de.dan.hobby.moisql.datatype.text.VarChar;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Daniel Laible
 * @since 0.1.5
 * <p>
 * This class is used to parse DataType enum types into IDataType objects
 */
public class DataTypeParser {

  private static final Logger logger = LoggerFactory.getLogger(DataTypeParser.class);

  public IDataType parseDataType(DataType dataType) {
    if (dataType.equals(DataType.BIGINT)) {
      return new BigInt(0l);
    } else if (dataType.equals(DataType.DECIMAL)) {
      return new Decimal(0f);
    } else if (dataType.equals(DataType.FLOAT)) {
      return new Float(0d);
    } else if (dataType.equals(DataType.INT)) {
      return new Int(0);
    } else if (dataType.equals(DataType.SMALLINT)) {
      return new SmallInt(0);
    } else if (dataType.equals(DataType.VARCHAR)) {
      return new VarChar("");
    } else if (dataType.equals(DataType.TEXT)) {
      return new Text("");
    } else if (dataType.equals(DataType.BOOL)) {
      return new Bool(false);
    } else if (dataType.equals(DataType.DATE)) {
      return new Date(0l);
    } else if (dataType.equals(DataType.TIME)) {
      return new Time(0l);
    } else if (dataType.equals(DataType.DATETIME)) {
      return new DateTime(0l);
    }
    logger.warn("Unable to parse datatype");
    return null;
  }
}
