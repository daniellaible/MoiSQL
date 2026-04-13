package de.dan.hobby.moisql.server.comm.commandFactory.commands.server;

import de.dan.hobby.moisql.server.IServer;
import de.dan.hobby.moisql.server.Server;
import de.dan.hobby.moisql.server.comm.commandFactory.ICommand;
import java.io.IOException;
import java.io.PrintWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Daniel Laible
 * @since 0.1.7
 * <p>
 * Factory class implementation for the 'shut down server' command
 */
public class ServerShutDownCommand implements ICommand {

  private static final Logger logger = LoggerFactory.getLogger(ServerShutDownCommand.class);
  private static final String LOGGER_SHUTTING_DOWN_SERVER = "Shutting down server";

  @Override
  public void execute(IServer server, PrintWriter out, String line) {
    logger.warn(LOGGER_SHUTTING_DOWN_SERVER);
    try {
      server.stop();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
