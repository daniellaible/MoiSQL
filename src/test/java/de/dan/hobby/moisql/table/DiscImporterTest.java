package de.dan.hobby.moisql.table;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.io.File;
import java.io.IOException;
import java.util.UUID;
import org.junit.jupiter.api.Test;

class DiscImporterTest extends BaseTest {

  @Test
  void testLoadTable() throws IOException {
    File dir = new File("C:\\temp\\moi");
    UUID uuid = UUID.fromString("0e6bce68-99fa-3841-b790-24afbdf7db1d");
    DiscImporter loader = new DiscImporter(dir, uuid);
    Table table = loader.loadTable();

    assertThat(table.getTableName()).isEqualTo("cities");
  }

}