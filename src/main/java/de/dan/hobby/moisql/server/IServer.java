package de.dan.hobby.moisql.server;

import de.dan.hobby.moisql.database.Database;
import de.dan.hobby.moisql.server.dbmfile.DbmFile;
import java.io.IOException;

public interface IServer {

  public void useDatabase(String dbName);

  public DbmFile getDbmFile();

  public void stop() throws IOException;

  public Database getDatabase();
}
