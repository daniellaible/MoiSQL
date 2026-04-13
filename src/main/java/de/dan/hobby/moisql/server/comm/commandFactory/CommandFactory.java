package de.dan.hobby.moisql.server.comm.commandFactory;

import de.dan.hobby.moisql.server.comm.commandFactory.commands.CloseConnectionCommand;
import de.dan.hobby.moisql.server.comm.commandFactory.commands.HelpCommand;
import de.dan.hobby.moisql.server.comm.commandFactory.commands.ServerShutDownCommand;
import de.dan.hobby.moisql.server.comm.commandFactory.commands.ShowDatabaseCommand;
import de.dan.hobby.moisql.server.comm.commandFactory.commands.UnknownCommand;
import de.dan.hobby.moisql.server.comm.commandFactory.commands.UseDatabaseCommand;

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
  private static final String USE_DATABASE = "use database";

  public ICommand getCommand(String line) {
    line = line.trim();
    line = line.toLowerCase();

    if (line.equals(SHOW_DATABASE)) {
      return new ShowDatabaseCommand();

    }else if (line.equals(BYE) || line.equalsIgnoreCase(QUIT)) {
      return new CloseConnectionCommand();

    }else if (line.equals(SHUTDOWN)) {
      return new ServerShutDownCommand();

    }else if (line.equals(HELP)) {
      return new HelpCommand();

    }else if(line.startsWith(USE_DATABASE)){
        return new UseDatabaseCommand();
    }

    return new UnknownCommand();
  }

}
