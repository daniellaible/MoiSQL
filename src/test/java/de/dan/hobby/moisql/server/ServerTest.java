package de.dan.hobby.moisql.server;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import org.junit.jupiter.api.Test;

class ServerTest extends DbmCreatorTest{

  @Test
  void testDbmFileAvailable() {
    File file = new File("C:\\moidb\\moidb.dbm");
    assertThat(file.exists()).isTrue();
  }



}