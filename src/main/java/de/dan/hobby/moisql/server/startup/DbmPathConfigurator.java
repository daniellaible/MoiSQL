package de.dan.hobby.moisql.server.startup;

import java.io.File;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DbmPathConfigurator implements IStartupSequence{

  private static final Logger logger = LoggerFactory.getLogger(DbmPathConfigurator.class);
  private static final String WINDOWS_DIR = "C:\\moidb";
  private static final String WINDOWS_PATH = "C:\\moidb\\moi.dbm";
  private static final String LINUX_DIR = "//bin//moidb";
  private static final String LINUX_PATH = "//bin//moidb//moi.dbm";

  private static final String LOGGER_UNSUPPORTED_OS_TYPE = "Unsupported osType: {}";
  private static final String LOGGER_PATH_IN_HAS_BEEN_SET = "Path in {} has been set";


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
        logger.warn(LOGGER_UNSUPPORTED_OS_TYPE, context.osType);
    }
    logger.info(LOGGER_PATH_IN_HAS_BEEN_SET, context.osType);
  }
}
