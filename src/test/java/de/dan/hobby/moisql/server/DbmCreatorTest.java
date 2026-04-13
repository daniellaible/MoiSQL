package de.dan.hobby.moisql.server;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import org.junit.jupiter.api.BeforeAll;

public class DbmCreatorTest {

  @BeforeAll
  static void createDbmFile() throws IOException {
    String data = createData();
    File dir = new File("C:\\moidb");
    if (!dir.exists()) {
      dir.mkdir();
    }
    File dbmFile = new File("C:\\moidb\\moi.dbm");
    byte[] bytesData = data.getBytes();
    Files.write(dbmFile.toPath(), bytesData);
  }


  private static String createData() {
    return """
        <db>
        <name>population</name>
        <path>C:\\temp\\moi</path>
        <table>
        <tablename>cities</tablename>
        <uuid>0e6bce68-99fa-3841-b790-24afbdf7db1d</uuid>
        </table>
        </db>
        """;
  }

}
