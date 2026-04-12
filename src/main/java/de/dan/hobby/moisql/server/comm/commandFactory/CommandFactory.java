package de.dan.hobby.moisql.server.comm.commandFactory;

import de.dan.hobby.moisql.server.comm.commandFactory.commands.ShowDatabaseCommand;
import de.dan.hobby.moisql.server.comm.commandFactory.commands.UnknownCommand;

public class CommandFactory {

  private static final String SHOW_DATABASE = "show database";

  public ICommand getCommand(String line) {
    line = line.trim();
    if (line.equalsIgnoreCase(SHOW_DATABASE)) {
      return new ShowDatabaseCommand();
    }

    return new UnknownCommand();
  }

}
