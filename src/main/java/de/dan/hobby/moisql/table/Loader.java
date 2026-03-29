package de.dan.hobby.moisql.table;

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
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Daniel Laible
 * @since 0.1.5
 * <p>
 * This class is used to load a table from the filesystem.
 */
public class Loader {

  private static final Logger logger = LoggerFactory.getLogger(Loader.class);

  private List<VarChar> columnNames = new ArrayList<>();
  private List<DataType> columnTypes = new ArrayList<>();
  private String tablename;
  private File directory;
  private UUID uuid;

  /**
   * Instanciates the Loader. The directory in which the database is stored
   * on the filesystem needs to be provided. The uuid of the table also needs
   * to be provided to identify which file needs to be accessed.
   *
   * @param directory in which the table is stored
   * @param uuid      the uuid of the table
   * @throws IOException
   */
  public Loader(File directory, UUID uuid) {
    this.directory = directory;
    this.uuid = uuid;
  }

  /**
   * To load a {@link de.dan.hobby.moisql.table.Table} from disc use this method. It loads the table
   * form disc and created a new B+Tree that the database can use.
   *
   * @return The table with all the data
   * @throws IOException
   */
  public Table loadTable() throws IOException {
    Table table = null;
    String fileName = uuid + ".moi";
    String path = directory.getAbsolutePath() + File.separator + fileName;
    RandomAccessFile in = null;
    try {
      in = new RandomAccessFile(path, "r");

      extractTableName(in);
      extracteColumnNames(in);
      extractColumnDefinitions(in);
      table = new Table(createTypeRows(), createColumnNames(), tablename);

      System.out.println(tablename);
      System.out.println(Arrays.toString(columnNames.toArray()));
      System.out.println(Arrays.toString(columnTypes.toArray()));

      in.close();
    } catch (Exception e) {
      logger.warn("Something went wrong loading the table {}.moi from disc", uuid);
      e.printStackTrace();
    } finally {
      if (in != null) {
        in.close();
      }
    }
    return table;
  }

  private VarChar[] createColumnNames() {
    VarChar[] columns = new VarChar[columnNames.size()];
    for (int i = 0; i < columnNames.size(); i++) {
      columns[i] = columnNames.get(i);
    }
    return columns;
  }

  private IDataType[] createTypeRows() {
    IDataType[] dataTypes = new IDataType[columnTypes.size()];
    for (int i = 0; i < dataTypes.length; i++) {
      final DataType dataType = columnTypes.get(i);

      if (dataType.equals(DataType.BIGINT)) {
        dataTypes[i] = new BigInt(0l);
      } else if (dataType.equals(DataType.DECIMAL)) {
        dataTypes[i] = new Decimal(0f);
      } else if (dataType.equals(DataType.FLOAT)) {
        dataTypes[i] = new Float(0d);
      } else if (dataType.equals(DataType.INT)) {
        dataTypes[i] = new Int(0);
      } else if (dataType.equals(DataType.SMALLINT)) {
        dataTypes[i] = new SmallInt(0);
      } else if (dataType.equals(DataType.VARCHAR)) {
        dataTypes[i] = new VarChar("");
      } else if (dataType.equals(DataType.TEXT)) {
        dataTypes[i] = new Text("");
      } else if (dataType.equals(DataType.BOOL)) {
        dataTypes[i] = new Bool(false);
      } else if (dataType.equals(DataType.DATE)) {
        dataTypes[i] = new Date(0l);
      } else if (dataType.equals(DataType.TIME)) {
        dataTypes[i] = new Time(0l);
      } else if (dataType.equals(DataType.DATETIME)) {
        dataTypes[i] = new DateTime(0l);
      } else {
        logger.warn("Unable to parse datatype {}", dataType);
      }
    }
    return dataTypes;
  }

  private void extractTableName(RandomAccessFile in) throws IOException {
    byte[] byteName = new byte[255];
    in.read(byteName, 0, 255);
    tablename = new String(byteName).trim();
  }

  private void extractColumnDefinitions(RandomAccessFile in) throws IOException {
    for (int i = 0; i < 64; i++) {
      byte[] byteColumnType = new byte[255];
      in.read(byteColumnType, 0, 255);
      String type = new String(byteColumnType);
      type = type.trim();
      if (!type.isEmpty()) {
        columnTypes.add(DataType.valueOf(type));
      }
    }
  }

  private void extracteColumnNames(RandomAccessFile in) throws IOException {
    for (int i = 0; i < 64; i++) {
      byte[] byteColumnName = new byte[255];
      in.read(byteColumnName, 0, 255);
      String columnName = new String(byteColumnName);
      columnName = columnName.trim();
      if (!columnName.isEmpty()) {
        VarChar vcColumnName = new VarChar(columnName);
        columnNames.add(vcColumnName);
      }
    }
  }
}
