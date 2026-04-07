package de.dan.hobby.moisql.server.startup;

import de.dan.hobby.moisql.server.DbmFile.DbmFile;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Daniel Laible
 * @since 0.1.6
 * <p>
 * Part of the StartupSequence Strategy
 * Checks if there is a moi directory and if not so, it creates one
 */
public class MoiDirectoryCreator implements IStartupSequence{

  private static final Logger logger = LoggerFactory.getLogger(MoiDirectoryCreator.class);
  private static final String LOGGER_MOI_DIRECTORY_AVAILABLE = "Moi directory available";

  @Override
  public void commence(StartupContext context) {
    if(!context.dbmDir.exists()){
      context.dbmDir.mkdirs();
    }
    logger.info(LOGGER_MOI_DIRECTORY_AVAILABLE);
  }
}
