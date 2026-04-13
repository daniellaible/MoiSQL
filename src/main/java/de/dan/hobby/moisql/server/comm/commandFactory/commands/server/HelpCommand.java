package de.dan.hobby.moisql.server.comm.commandFactory.commands.server;

import de.dan.hobby.moisql.server.IServer;
import de.dan.hobby.moisql.server.Server;
import de.dan.hobby.moisql.server.comm.commandFactory.ICommand;
import java.io.PrintWriter;

/**
 * @author Daniel Laible
 * @since 0.1.7
 * <p>
 * Factory class implementation for the 'help' command
 */
public class HelpCommand implements ICommand {

  @Override
  public void execute(IServer server, PrintWriter out, String line) {
      out.println("*************************************");
      out.println("                 HELP");
      out.println("   available commands are:");
      out.println("");
      out.println("[1] quit | bye");
      out.println("             shuts down your current connection with the server.");
      out.println("[2] shutdown");
      out.println("             shuts the database server down");
      out.println("[2] show databases");
      out.println("             lists all available databases.");
      out.println("[4] use database <database_name>");
      out.println("             makes this database the one you work with");
      out.println("[5] show tables");
      out.println("             lists all available tables for the selected database");
  }
}
