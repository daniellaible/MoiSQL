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
import de.dan.hobby.moisql.datatype.text.VarChar;
import de.dan.hobby.moisql.tool.parser.DataTypeParser;
import java.io.EOFException;
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
public class DiscImporter {

  private static final Logger logger = LoggerFactory.getLogger(DiscImporter.class);

  private List<VarChar> columnNames = new ArrayList<>();
  private List<DataType> columnTypes = new ArrayList<>();
  private String tablename;
  private File directory;
  private UUID uuid;
  private float version;
  private short numberofColumns;
  private short part;
  private short partOf;

  /**
   * Instanciates the Loader. The directory in which the database is stored
   * on the filesystem needs to be provided. The uuid of the table also needs
   * to be provided to identify which file needs to be accessed.
   *
   * @param directory in which the table is stored
   * @param uuid      the uuid of the table
   * @throws IOException
   */
  public DiscImporter(File directory, UUID uuid) {
    this.directory = directory;
    this.uuid = uuid;
  }

  /**
   * To load a {@link de.dan.hobby.moisql.table.Table} from disc use this method. It loads the table
   * form disc and created a new B+Tree that the database can use.
   *
   * Right now tables that occupy more than 4GB of disc space might not be supported
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

      byte[] loco = new byte[4];
      in.read(loco, 0, 4);

      version = in.readFloat();
      numberofColumns = in.readShort();
      part = in.readShort();
      partOf = in.readShort();

      //read the next file;
      short nextFileLength = in.readShort();
      byte[] byteNextFile = new byte[nextFileLength];
      in.read(byteNextFile, 0, nextFileLength);
      String nextFile = new String(byteNextFile).trim();

      extractTableName(in);
      extracteColumnNames(in);
      extractColumnDefinitions(in);
      table = new Table(createTypeRows(), createColumnNames(), tablename);

      List<IDataType[]> rows = new ArrayList<>();
      readData(table, in, rows);

      for (IDataType[] row : rows) {
        table.insert(row);
      }

      logger.info("moi-data file version: {}", version);
      logger.info("number of columns: {}", numberofColumns);
      logger.info("part: {} of {}", part, partOf);
      logger.info("nextFile: {} ", nextFile);
      logger.info("Table name: {}", tablename);
      logger.info("Column names: {}", Arrays.toString(columnNames.toArray()));
      logger.info("Column types: {}", Arrays.toString(columnTypes.toArray()));

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

  private void readData(Table table, RandomAccessFile in, List<IDataType[]> rows) throws IOException {
    try {
      while (true) {
        final IDataType[] dts = table.getColumnTypes();

        List<IDataType> tempRow = new ArrayList<>(dts.length);

        for (int i = 0; i < dts.length; i++) {
          if (dts[i].getDataType().equals(DataType.BIGINT)) {
            tempRow.add(new BigInt(in.readLong()));
          } else if (dts[i].getDataType().equals(DataType.DECIMAL)) {
            tempRow.add(new Decimal(in.readFloat()));
          } else if (dts[i].getDataType().equals(DataType.FLOAT)) {
            tempRow.add(new Float(in.readDouble()));
          } else if (dts[i].getDataType().equals(DataType.INT)) {
            tempRow.add(new Int(in.readInt()));
          } else if (dts[i].getDataType().equals(DataType.SMALLINT)) {
            tempRow.add(new SmallInt(in.readShort()));
          } else if (dts[i].getDataType().equals(DataType.TIME)) {
            tempRow.add(new Time(in.readLong()));
          } else if (dts[i].getDataType().equals(DataType.DATE)) {
            tempRow.add(new Date(in.readLong()));
          } else if (dts[i].getDataType().equals(DataType.DATETIME)) {
            tempRow.add(new DateTime(in.readLong()));
          }else if (dts[i].getDataType().equals(DataType.BOOL)) {
            byte[] bool = new byte[1];
            in.read(bool, 0, 1);
            tempRow.add(new Bool(bool[0]));
          } else if (dts[i].getDataType().equals(DataType.VARCHAR)) {
            final short lengthOfVarChar = in.readShort();
            byte[] byteName = new byte[lengthOfVarChar];
            in.read(byteName, 0, lengthOfVarChar);
            tempRow.add(new VarChar(new String(byteName).trim()));
          }
        }
        IDataType[] row = new IDataType[tempRow.size()];
        for (int i = 0; i < tempRow.size(); i++) {
          row[i] = tempRow.get(i);
        }

        for (IDataType cell : row) {
          System.out.print(cell + " ");
        }
        System.out.println();
        rows.add(row);
      }
    } catch (EOFException e) {
      logger.info("file read completely");
    }
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
      DataTypeParser dtParser = new DataTypeParser();
      dataTypes[i] = dtParser.parseDataType(dataType);
    }
    return dataTypes;
  }

  private void extractTableName(RandomAccessFile in) throws IOException {
    short length = in.readShort();
    byte[] byteName = new byte[length];
    in.read(byteName, 0, length);
    tablename = new String(byteName).trim();
  }

  private void extractColumnDefinitions(RandomAccessFile in) throws IOException {
    for (int i = 0; i < numberofColumns; i++) {
      short length = in.readShort();
      byte[] byteColumnType = new byte[length];
      in.read(byteColumnType, 0, length);
      String type = new String(byteColumnType);
      type = type.trim();
      if (!type.isEmpty()) {
        columnTypes.add(DataType.valueOf(type));
      }
    }
  }

  private void extracteColumnNames(RandomAccessFile in) throws IOException {
    for (int i = 0; i < numberofColumns; i++) {
      short length = in.readShort();
      byte[] byteColumnName = new byte[length];
      in.read(byteColumnName, 0, length);
      String columnName = new String(byteColumnName);
      columnName = columnName.trim();
      if (!columnName.isEmpty()) {
        VarChar vcColumnName = new VarChar(columnName);
        columnNames.add(vcColumnName);
      }
    }
  }
}
