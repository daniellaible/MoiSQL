package de.dan.hobby.moisql.server.comm.commandFactory.commands.dml;

import de.dan.hobby.moisql.server.Server;
import de.dan.hobby.moisql.server.comm.commandFactory.ICommand;
import java.io.PrintWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UpdateCommand implements ICommand {

  private static final Logger logger = LoggerFactory.getLogger(UpdateCommand.class);

  @Override
  public void execute(Server server, PrintWriter out, String line) {

  }
}
