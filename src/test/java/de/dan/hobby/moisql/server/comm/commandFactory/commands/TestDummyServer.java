package de.dan.hobby.moisql.server.comm.commandFactory.commands;

import de.dan.hobby.moisql.database.Database;
import de.dan.hobby.moisql.server.IServer;
import de.dan.hobby.moisql.server.dbmfile.DbmFile;
import java.io.IOException;

public class TestDummyServer implements IServer {

  @Override
  public void useDatabase(String dbName) {

  }

  @Override
  public DbmFile getDbmFile() {
    return null;
  }

  @Override
  public void stop() throws IOException {

  }

  @Override
  public Database getDatabase() {
    return null;
  }
}
