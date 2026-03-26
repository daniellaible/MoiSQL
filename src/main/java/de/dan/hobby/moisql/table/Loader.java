package de.dan.hobby.moisql.table;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.UUID;

public class Loader {

  public Loader(File directory, UUID uuid) throws IOException {

    String fileName = uuid + ".moi";
    String path = directory.getAbsolutePath() + File.separator + fileName;

    RandomAccessFile in = new RandomAccessFile(path, "r");

    try {
      byte[] byteName = new byte[255];
      in.read(byteName, 0, 255);
      String tableName = new String(byteName);
      System.out.println(tableName.trim());

      for (int i = 1; i < 65; i++) {
        byte[] byteColumnName = new byte[255];
        in.read(byteColumnName, 0, 255);
        String columnName = new String(byteColumnName);
        if(!columnName.trim().isEmpty()) {
          System.out.println(columnName);
        }
      }

      for (int i = 1; i < 65; i++) {
        byte[] byteColumnType = new byte[255];
        in.read(byteColumnType, 0, 255);
        String type = new String(byteColumnType);
        if(!type.isEmpty()) {
          System.out.println(type);
        }
      }

      in.close();
    }catch(Exception e) {
      System.out.println(e.getMessage());
      e.printStackTrace();
    }

  }
}
