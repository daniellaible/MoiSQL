package de.dan.hobby.moisql.server.startup;

import de.dan.hobby.moisql.server.DbmFile;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Daniel Laible
 * @since 0.1.6
 * <p>
 * Part of the StartupSequence Strategy
 * Reads the .dbm file for already existing moi databases on disc
 */
public class DbmImporter implements IStartupSequence {

  private static final Logger logger = LoggerFactory.getLogger(DbmImporter.class);

  private static final String DBM_PATH_WINDOWS = "C:\\moidb\\moi.dbm";
  private static final String DBM_PATH_LINUX = "//bin//moidb//moi.dbm";


  @Override
  public void commence(StartupContext context) {
    Optional<DbmFile> dbmFile = Optional.empty();
    switch (context.osType) {
      case WINDOWS:
        dbmFile = loadDbmFile(DBM_PATH_WINDOWS);
        break;
      case LINUX:
        dbmFile = loadDbmFile(DBM_PATH_LINUX);
      default:
        logger.warn("Unsupported os type");
    }
  }

  private Optional<DbmFile> loadDbmFile(String path) {
    return Optional.empty();
  }
}
