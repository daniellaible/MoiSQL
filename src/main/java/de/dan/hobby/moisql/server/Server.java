package de.dan.hobby.moisql.server;

import de.dan.hobby.moisql.database.Database;
import de.dan.hobby.moisql.datatype.text.VarChar;
import de.dan.hobby.moisql.server.DbmFile.DbmDatabase;
import de.dan.hobby.moisql.server.DbmFile.DbmFile;
import de.dan.hobby.moisql.server.comm.ServerThread;
import de.dan.hobby.moisql.server.startup.DbmImporter;
import de.dan.hobby.moisql.server.startup.DbmPathConfigurator;
import de.dan.hobby.moisql.server.startup.IStartupSequence;
import de.dan.hobby.moisql.server.startup.MemoryCheck;
import de.dan.hobby.moisql.server.startup.MoiDirectoryCreator;
import de.dan.hobby.moisql.server.startup.OSDetector;
import de.dan.hobby.moisql.server.startup.StartupContext;
import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.SocketException;
import java.util.Arrays;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Daniel Laible
 * @since 0.1.6
 * <p>
 * The startup class for the DB Server
 */
public class Server {

  private static final Logger logger = LoggerFactory.getLogger(Server.class);
  private static final String NO_DATABASE_SELECTED = "No database selected";
  private static final String LOGGER_STARTUP_COMPLETED = "Startup completed";
  private static final String LOGGER_SOCKET_EXCEPTION = "Socket exception";
  private static final String LOGGER_IO_EXCEPTION = "IOException";

  private ServerSocket socket;
  private Database databaseInUse = null;
  private boolean isListening = true;

  public static StartupContext context;
  public static final int PORT = 7878;

  private List<IStartupSequence> startupSequences = Arrays.asList(
      new OSDetector(),
      new MemoryCheck(),
      new DbmPathConfigurator(),
      new MoiDirectoryCreator(),
      new DbmImporter()
  );

  public static void main(String[] args) {
    new Server();
  }

  public Server() {
    runStartupSequence();
    logger.info(LOGGER_STARTUP_COMPLETED);
    start();
  }

  /**
   * Stopps the ServerSocket from receiving any new commands
   * @throws IOException
   */
  public void stop() throws IOException {
    isListening = false;
    this.socket.close();
    System.exit(0);
  }

  /**
   * returns the content of the DbmFile read on startup
   * @return
   */
  public DbmFile getDbmFile() {
    return context.dbmFile;
  }

  /**
   *
   * @param dbName name of the database which will be loaded into memory
   */
  public void useDatabase(String dbName){
    dbName = dbName.toUpperCase();
    final List<DbmDatabase> dbnames = context.dbmFile.getDbnames();
    for(DbmDatabase dbmDb : dbnames){
      String dbmName = dbmDb.getDbName().toUpperCase();
      if(dbmName.equals(dbName)){
        logger.info("Loading database {}", dbmName);

        Database db = new Database(new VarChar(dbName), new File(dbmDb.getDbPath()));
        try {
          db.loadDatabase(dbmDb.getTables());
        } catch (IOException e) {
          logger.error("Unable to import database {}", dbmName, e);
        }
        databaseInUse = db;
        logger.info("database in use {}", databaseInUse.getDbName());
      }
    }
  }

  public String getDatabase(String dbName){
    if(databaseInUse == null){
      return NO_DATABASE_SELECTED;
    }
    return databaseInUse.getDbName();
  }

  private void start()  {
    try {
      while (isListening) {
        new ServerThread(socket.accept(), this).start();
      }
    }catch(SocketException ex){
      logger.error(LOGGER_SOCKET_EXCEPTION, ex);
    } catch (IOException e) {
      logger.error(LOGGER_IO_EXCEPTION, e);
    }
  }

  private void runStartupSequence() {
    context = new StartupContext();
    for (IStartupSequence sequence : startupSequences) {
      sequence.commence(context);
    }

    try {
      socket = new ServerSocket(PORT);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
