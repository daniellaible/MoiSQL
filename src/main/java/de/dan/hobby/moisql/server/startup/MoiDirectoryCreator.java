package de.dan.hobby.moisql.server.startup;

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

  @Override
  public void commence(StartupContext context) {
    logger.info("MoiDirectoryCreator needs to be implemented");
  }
}
