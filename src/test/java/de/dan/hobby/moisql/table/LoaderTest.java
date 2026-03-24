package de.dan.hobby.moisql.table;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.util.UUID;
import org.junit.jupiter.api.Test;

class LoaderTest extends BaseTest{

  @Test
  void testLoadTable() throws IOException {
    File dir = new File("C:\\temp\\moi");
    UUID uuid = UUID.fromString("0e6bce68-99fa-3841-b790-24afbdf7db1d");
    Loader loader = new Loader(dir, uuid);


  }

}