package de.dan.hobby.moisql.server.comm.commandFactory.commands;

import de.dan.hobby.moisql.server.Server;
import de.dan.hobby.moisql.server.comm.commandFactory.ICommand;
import java.io.PrintWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Daniel Laible
 * @since 0.1.7
 * <p>
 * Factory class implementation for the 'use database' command
 */
public class UseDatabaseCommand implements ICommand {

  private static final Logger logger = LoggerFactory.getLogger(UseDatabaseCommand.class);
  private static final String LOGGER_USE_DATABASE_COMMAND_TRIGGERED = "Use database command triggered";

  @Override
  public void execute(Server server, PrintWriter out, String line) {
      logger.info(LOGGER_USE_DATABASE_COMMAND_TRIGGERED);
      String[] tokens = tokenizeLine(line);
      server.useDatabase(tokens[3]);
  }

  private String[] tokenizeLine(String line) {
    line = line.trim();
    String[] splits = line.split(" ");
    if(splits.length > 3 || splits.length < 3) {
      logger.warn("Bad input {}", line);
    }
    return splits;
  }
}
