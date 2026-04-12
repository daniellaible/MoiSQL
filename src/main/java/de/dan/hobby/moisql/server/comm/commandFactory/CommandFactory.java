package de.dan.hobby.moisql.server.comm.commandFactory;

import de.dan.hobby.moisql.server.comm.commandFactory.commands.CloseConnectionCommand;
import de.dan.hobby.moisql.server.comm.commandFactory.commands.ServerShutDownCommand;
import de.dan.hobby.moisql.server.comm.commandFactory.commands.ShowDatabaseCommand;
import de.dan.hobby.moisql.server.comm.commandFactory.commands.UnknownCommand;

/**
 * @author Daniel Laible
 * @since 0.1.7
 * <p>
 * Factory class for the commands
 */
public class CommandFactory {

  private static final String SHOW_DATABASE = "show databases";
  private static final String BYE = "bye";
  private static final String QUIT = "quit";
  private static final String SHUTDOWN = "shutdown";
  private static final String HELP = "help";

  public ICommand getCommand(String line) {
    line = line.trim();
    if (line.equalsIgnoreCase(SHOW_DATABASE)) {
      return new ShowDatabaseCommand();

    }else if (line.equalsIgnoreCase(BYE) || line.equalsIgnoreCase(QUIT)) {
      return new CloseConnectionCommand();

    }else if (line.equalsIgnoreCase(SHUTDOWN)) {
      return new ServerShutDownCommand();

    }else if (line.equalsIgnoreCase(HELP)) {
      return new ServerShutDownCommand();
    }

    return new UnknownCommand();
  }

}
