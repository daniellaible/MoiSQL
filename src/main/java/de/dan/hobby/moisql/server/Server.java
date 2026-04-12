package de.dan.hobby.moisql.server;

import de.dan.hobby.moisql.server.DbmFile.DbmFile;
import de.dan.hobby.moisql.server.comm.ServerThread;
import de.dan.hobby.moisql.server.startup.DbmImporter;
import de.dan.hobby.moisql.server.startup.DbmPathConfigurator;
import de.dan.hobby.moisql.server.startup.IStartupSequence;
import de.dan.hobby.moisql.server.startup.MemoryCheck;
import de.dan.hobby.moisql.server.startup.MoiDirectoryCreator;
import de.dan.hobby.moisql.server.startup.OSDetector;
import de.dan.hobby.moisql.server.startup.StartupContext;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.SocketException;
import java.util.Arrays;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Daniel Laible
 * @since 0.1.6
 * <p>
 * The startup class for the DB Server
 */
public class Server {

  private static final Logger logger = LoggerFactory.getLogger(Server.class);
  private ServerSocket socket;
  private boolean isListening = true;

  public static StartupContext context;
  public static final int PORT = 7878;

  private List<IStartupSequence> startupSequences = Arrays.asList(
      new OSDetector(),
      new MemoryCheck(),
      new DbmPathConfigurator(),
      new MoiDirectoryCreator(),
      new DbmImporter()
  );

  public static void main(String[] args) {
    Server server = new Server();
  }

  public Server() {
    runStartupSequence();
    logger.info("Startup completed");
    start();
  }

  public void start()  {
    try {
      while (isListening) {
        new ServerThread(socket.accept(), this).start();
      }
    }catch(SocketException ex){
      logger.error("Socket exception", ex);
    } catch (IOException e) {
      logger.error("IOException", e);
    }
  }

  public void stop() throws IOException {
    this.socket.close();
  }

  public DbmFile getDbmFile() {
    return context.dbmFile;
  }

  private void runStartupSequence() {
    context = new StartupContext();
    for (IStartupSequence sequence : startupSequences) {
      sequence.commence(context);
    }

    try {
      socket = new ServerSocket(PORT);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }


}
