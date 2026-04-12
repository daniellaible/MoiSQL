package de.dan.hobby.moisql.server.comm.commandFactory.commands;

import de.dan.hobby.moisql.server.Server;
import de.dan.hobby.moisql.server.comm.commandFactory.ICommand;
import java.io.PrintWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UnknownCommand implements ICommand {

  private static final Logger logger = LoggerFactory.getLogger(UnknownCommand.class);
  private static final String UNKNOWN_COMMAND = "Unknown command: ";

  @Override
  public void execute(Server server, PrintWriter out, String line) {
    out.println(UNKNOWN_COMMAND + line);
    logger.warn(UNKNOWN_COMMAND + line);
  }
}
