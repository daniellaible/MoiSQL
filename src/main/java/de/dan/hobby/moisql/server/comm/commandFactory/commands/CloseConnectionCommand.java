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
 * Factory class implementation for the 'close connection' command
 */
public class CloseConnectionCommand implements ICommand {

  private static final Logger logger = LoggerFactory.getLogger(CloseConnectionCommand.class);

  @Override
  public void execute(Server server, PrintWriter out, String line) {
    logger.warn("Closing connection");
  }
}
