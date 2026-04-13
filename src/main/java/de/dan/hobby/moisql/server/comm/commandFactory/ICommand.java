package de.dan.hobby.moisql.server.comm.commandFactory;

import de.dan.hobby.moisql.server.IServer;
import de.dan.hobby.moisql.server.Server;
import java.io.PrintWriter;

/**
 * @author Daniel Laible
 * @since 0.1.7
 * <p>
 * Factory Interface for the commands
 */
public interface ICommand {

  void execute(IServer server, PrintWriter out, String line);



}
