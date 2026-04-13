package de.dan.hobby.moisql.server.comm.commandFactory.commands.dml;

import de.dan.hobby.moisql.server.IServer;
import de.dan.hobby.moisql.server.Server;
import de.dan.hobby.moisql.server.comm.commandFactory.ICommand;
import de.dan.hobby.moisql.server.comm.commandFactory.commands.ddl.TruncateCommand;
import java.io.PrintWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DeleteCommand implements ICommand {

  private static final Logger logger = LoggerFactory.getLogger(DeleteCommand.class);

  @Override
  public void execute(IServer server, PrintWriter out, String line) {
    logger.warn("Not yet implemented");
  }
}
