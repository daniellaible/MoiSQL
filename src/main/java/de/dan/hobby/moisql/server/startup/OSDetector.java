package de.dan.hobby.moisql.server.startup;

import de.dan.hobby.moisql.server.OSType;
import de.dan.hobby.moisql.server.Server;
import java.util.Locale;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Daniel Laible
 * @since 0.1.6
 *
 * Part of the StartupSequence Strategy
 * Determines the operating system the server is working with
 */
public class OSDetector implements IStartupSequence{

  private static final Logger logger = LoggerFactory.getLogger(OSDetector.class);

  public void commence(StartupContext context) {
    String os = System.getProperty("os.name", "generic").toLowerCase(Locale.ENGLISH);
    if (os.contains("win")) {
      context.osType = OSType.WINDOWS;
    } else if (os.contains("nix") || os.contains("nux") || os.contains("aix")) {
      context.osType = OSType.LINUX;
    } else if (os.contains("mac")) {
      context.osType = OSType.MAC;
    } else if (os.contains("sunos")) {
      context.osType = OSType.SOLARIS;
    } else {
      context.osType = OSType.OTHER;
    }
    logger.info("Detected OS is: {}", context.osType);
  }



/*  private Optional<DbmFile> checkDbFileMac() {
    logger.warn("MacOS is not yet supported");
    return Optional.empty();
  }

  //TODO implement DbFile for Solaris
  private Optional<DbmFile> checkDbFileSolaris() {
    logger.warn("Solaris is not yet supported");
    return Optional.empty();
  }

  //TODO test it on linux
  private Optional<DbmFile> checkDbFileLinux() {
    File file = new File("//bin//moidb//dbs.dbm");
    DbmFile dbmFile = null;
    if (file.exists()) {
      dbmFile = importDbmFile(file);
    }

    if (dbmFile == null) {
      return Optional.empty();
    } else {
      return Optional.of(dbmFile);
    }
  }


*/

}
