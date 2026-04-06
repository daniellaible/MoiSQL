package de.dan.hobby.moisql.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Daniel Laible
 * @since 0.1.6
 *
 * The startup class for the DB Server
 */
public class Server {

  private static final Logger logger = LoggerFactory.getLogger(Server.class);

  public static OSType detectedOS;

  public static void main(String[] args) {
    Server server = new Server();
  }

  public Server() {
    detectedOS = OSDetector.detectOS();
    logger.info("Detected OS: " + detectedOS);

    DbmImporter dbmImporter = new DbmImporter();
  }


/*  private Optional<DbmFile> loadDatabaseFile() {
    detectOperatingSystem();
    Optional<DbmFile> dbmFile = Optional.empty();

    switch (detectedOS) {
      case WINDOWS:
        dbmFile = checkDbFileWin();
        break;
      case LINUX:
        dbmFile = checkDbFileLinux();
        break;
      case SOLARIS:
        dbmFile = checkDbFileSolaris();
        break;
      case MAC:
        dbmFile = checkDbFileMac();
        break;
      default:
        logger.warn("Unable to detect operating system: " + detectedOS);
    }

    return dbmFile;
  }*/

}
