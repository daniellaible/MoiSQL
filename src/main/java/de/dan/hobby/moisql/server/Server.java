package de.dan.hobby.moisql.server;

import de.dan.hobby.moisql.server.startup.DbmImporter;
import de.dan.hobby.moisql.server.startup.IStartupSequence;
import de.dan.hobby.moisql.server.startup.OSDetector;
import de.dan.hobby.moisql.server.startup.StartupContext;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Daniel Laible
 * @since 0.1.6
 *
 * The startup class for the DB Server
 */
public class Server {

  private static final Logger logger = LoggerFactory.getLogger(Server.class);

  public static StartupContext context;

  List<IStartupSequence> startupSequences = Arrays.asList(
      new OSDetector(),
      new DbmImporter()
      );


  public static void main(String[] args) {
    Server server = new Server();
  }

  public Server() {
    runStartupSequence();
  }

  private void runStartupSequence() {
    context = new StartupContext();
    for(IStartupSequence sequence : startupSequences) {
      sequence.commence(context);
    }
  }
}
