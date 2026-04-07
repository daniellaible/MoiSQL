package de.dan.hobby.moisql.server.startup;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Daniel Laible
 * @since 0.1.6
 * <p>
 * Part of the StartupSequence Strategy
 * Finds out how much memory is available to the server
 */
public class MemoryCheck implements IStartupSequence{

  private static final Logger logger = LoggerFactory.getLogger(MemoryCheck.class);

  private static final long MEGABYTES = 1024 * 1024;
  private static final String LOGGER_MEMORY_OUTPUT = "max: {}MB free: {}MB total: {}MB";

  @Override
  public void commence(StartupContext context) {
    Runtime runtime = Runtime.getRuntime();

    final long maxMemory = runtime.maxMemory();
    final long freeMemory = runtime.freeMemory();
    final long totalMemory = runtime.totalMemory();

    logger.info(LOGGER_MEMORY_OUTPUT, maxMemory / MEGABYTES, freeMemory / MEGABYTES, totalMemory / MEGABYTES);

  }
}
