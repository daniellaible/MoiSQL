package de.dan.hobby.moisql.table;

import java.io.File;
import java.io.IOException;
import org.junit.jupiter.api.Test;

class SaverTest extends BaseTest{

  @Test
  public void writeToFile(){
    File dir = new File("C:\\temp\\moi");
    try {
      table.save(dir);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

}