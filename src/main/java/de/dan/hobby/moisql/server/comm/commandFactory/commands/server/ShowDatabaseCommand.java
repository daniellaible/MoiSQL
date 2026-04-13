package de.dan.hobby.moisql.server.comm.commandFactory.commands.server;

import de.dan.hobby.moisql.server.dbmfile.DbmDatabase;
import de.dan.hobby.moisql.server.Server;
import de.dan.hobby.moisql.server.comm.commandFactory.ICommand;
import java.io.PrintWriter;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Daniel Laible
 * @since 0.1.7
 * <p>
 * Factory class implementation for the 'show databases' command
 */
public class ShowDatabaseCommand implements ICommand {

  private static final Logger logger = LoggerFactory.getLogger(ShowDatabaseCommand.class);
  private static final String LOGGER_EXECUTING_SHOW_DATABASE_COMMAND = "Executing ShowDatabaseCommand";

  @Override
  public void execute(Server server, PrintWriter out, String line) {
    logger.info(LOGGER_EXECUTING_SHOW_DATABASE_COMMAND);

    final List<DbmDatabase> dbnames = server.getDbmFile().getDbnames();
    for (DbmDatabase db : dbnames) {
      out.println(" * " + db.getDbName());
    }
  }
}
