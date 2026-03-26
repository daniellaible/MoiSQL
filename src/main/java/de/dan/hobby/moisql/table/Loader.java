package de.dan.hobby.moisql.table;

import de.dan.hobby.moisql.datatype.DataType;
import de.dan.hobby.moisql.datatype.text.VarChar;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * @author Daniel Laible
 * @since 0.1.5
 * <p>
 * This class is used to load a table from the filesystem.
 */
public class Loader {

  public Loader(File directory, UUID uuid) throws IOException {

    String tablename;
    List<VarChar> columnNames = new ArrayList<>();
    List<DataType> columnTypes = new ArrayList<>();

    String fileName = uuid + ".moi";
    String path = directory.getAbsolutePath() + File.separator + fileName;

    RandomAccessFile in = new RandomAccessFile(path, "r");

    try {
      byte[] byteName = new byte[255];
      in.read(byteName, 0, 255);
      tablename = new String(byteName).trim();

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

      for (int i = 0; i < 64; i++) {
        byte[] byteColumnType = new byte[255];
        in.read(byteColumnType, 0, 255);
        String type = new String(byteColumnType);
        type = type.trim();
        if (!type.isEmpty()) {
          columnTypes.add(DataType.valueOf(type));
        }
      }

      System.out.println(tablename);
      System.out.println(Arrays.toString(columnNames.toArray()));
      System.out.println(Arrays.toString(columnTypes.toArray()));

      in.close();
    } catch (Exception e) {
      System.out.println(e.getMessage());
      e.printStackTrace();
    }

  }
}
