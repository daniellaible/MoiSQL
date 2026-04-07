package de.dan.hobby.moisql.server.startup;

import de.dan.hobby.moisql.server.DbmFile.DbmFile;
import java.io.File;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DbmPathConfigurator implements IStartupSequence{

  private static final Logger logger = LoggerFactory.getLogger(DbmPathConfigurator.class);
  private static final String WINDOWS_DIR = "C:\\moidb";
  private static final String WINDOWS_PATH = "C:\\moidb\\moi.dbm";
  private static final String LINUX_DIR = "//bin//moidb";
  private static final String LINUX_PATH = "//bin//moidb//moi.dbm";


  @Override
  public void commence(StartupContext context) {

    switch (context.osType) {
      case WINDOWS:
        context.dbmDir = new File(WINDOWS_DIR);
        context.dbmPath = new File(WINDOWS_PATH);
        break;
      case LINUX:
        context.dbmDir = new File(LINUX_DIR);
        context.dbmPath = new File(LINUX_PATH);
      default:
        logger.warn("Unsupported osType: " + context.osType);
    }
    logger.info("Path in {} has been set", context.osType);
  }
}
