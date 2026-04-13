package de.dan.hobby.moisql.server.comm.commandFactory.commands.server;

import de.dan.hobby.moisql.server.IServer;
import de.dan.hobby.moisql.server.Server;
import de.dan.hobby.moisql.server.comm.commandFactory.ICommand;
import java.io.PrintWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Daniel Laible
 * @since 0.1.7
 * <p>
 * Factory class implementation for an unknown command
 */
public class UnknownCommand implements ICommand {

  private static final Logger logger = LoggerFactory.getLogger(UnknownCommand.class);
  private static final String UNKNOWN_COMMAND = "Unknown command: ";

  @Override
  public void execute(IServer server, PrintWriter out, String line) {
    out.println(UNKNOWN_COMMAND + line);
    logger.warn(UNKNOWN_COMMAND + line);
    new HelpCommand().execute(server, out, line);

  }
}
