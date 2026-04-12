package de.dan.hobby.moisql.server.comm.commandFactory;

import de.dan.hobby.moisql.server.Server;
import java.io.PrintWriter;

public interface ICommand {

  void execute(Server server, PrintWriter out, String line);



}
