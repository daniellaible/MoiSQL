package de.dan.hobby.moisql.server.comm.commandFactory.commands.ddl;

import de.dan.hobby.moisql.server.IServer;
import de.dan.hobby.moisql.server.Server;
import de.dan.hobby.moisql.server.comm.commandFactory.ICommand;
import de.dan.hobby.moisql.server.comm.commandFactory.commands.server.CloseConnectionCommand;
import java.io.PrintWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AlterCommand implements ICommand {

  private static final Logger logger = LoggerFactory.getLogger(AlterCommand.class);

  @Override
  public void execute(IServer server, PrintWriter out, String line) {
    logger.warn("Not yet implemented");
  }
}
