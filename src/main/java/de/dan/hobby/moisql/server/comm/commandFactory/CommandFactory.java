package de.dan.hobby.moisql.server.comm.commandFactory;

import de.dan.hobby.moisql.server.comm.commandFactory.commands.dml.InsertCommand;
import de.dan.hobby.moisql.server.comm.commandFactory.commands.server.CloseConnectionCommand;
import de.dan.hobby.moisql.server.comm.commandFactory.commands.server.HelpCommand;
import de.dan.hobby.moisql.server.comm.commandFactory.commands.server.ServerShutDownCommand;
import de.dan.hobby.moisql.server.comm.commandFactory.commands.server.ShowDatabaseCommand;
import de.dan.hobby.moisql.server.comm.commandFactory.commands.server.UnknownCommand;
import de.dan.hobby.moisql.server.comm.commandFactory.commands.server.UseDatabaseCommand;

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
  private static final String INSERT_INTO = "insert into";

  public ICommand getCommand(String line) {
    String input = line.trim();
    input = input.toLowerCase();
    if (input.equals(SHOW_DATABASE)) {
      return new ShowDatabaseCommand();

    }else if (input.equals(BYE) || input.equalsIgnoreCase(QUIT)) {
      return new CloseConnectionCommand();

    }else if (input.equals(SHUTDOWN)) {
      return new ServerShutDownCommand();

    }else if (input.equals(HELP)) {
      return new HelpCommand();

    }else if(input.startsWith(USE_DATABASE)){
        return new UseDatabaseCommand();

    }else if(input.startsWith(INSERT_INTO)){
      return new InsertCommand();
    }

    return new UnknownCommand();
  }

}
