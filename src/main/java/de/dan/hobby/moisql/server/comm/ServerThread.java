package de.dan.hobby.moisql.server.comm;

import de.dan.hobby.moisql.server.Server;
import de.dan.hobby.moisql.server.comm.commandFactory.CommandFactory;
import de.dan.hobby.moisql.server.comm.commandFactory.ICommand;
import de.dan.hobby.moisql.server.comm.commandFactory.commands.CloseConnectionCommand;
import de.dan.hobby.moisql.server.comm.commandFactory.commands.ServerShutDownCommand;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Daniel Laible
 * @since 0.1.7
 * <p>
 * Communication thread for the outside world
 */
public class ServerThread extends Thread{

  private static final Logger logger = LoggerFactory.getLogger(ServerThread.class);

  private static final String OUTPUT_WELCOME_TO_MOI_SQL_SERVER = "Welcome to MoiSQL Server";
  private static final String OUTPUT_WE_ARE_ALL_PROGRAMMED_TO_RECEIVE = "We are all programmed to receive";
  private static final String LOGGER_SHUTTING_DOWN_THE_SERVER = "Shutting down the server";
  private static final String OUTPUT_RECEIVED = "Received: ";
  private static final String LOGGER_SOMETHING_WRONG_IN_PROCESS_MESSAGE = "Something wrong in process message";

  private final Socket socket;
  private final Server server;

  public ServerThread(final Socket socket, Server server) {
    this.socket = socket;
    this.server = server;
  }

  public void run() {
    try {
      processMessage();
    } catch (IOException e) {
      logger.error(LOGGER_SOMETHING_WRONG_IN_PROCESS_MESSAGE, e);
      throw new RuntimeException(e);
    }
  }

  private void processMessage() throws IOException {
    PrintWriter out = null;
    BufferedReader in = null;

    try {
      out = new PrintWriter(this.socket.getOutputStream(), true);
      in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

      welcomeMessage(out);

      String line = "";
      while ((line = in.readLine()) != null) {
        if(!line.isBlank()){
          line = line.trim();
          out.println(OUTPUT_RECEIVED + line);

          CommandFactory commandFactory = new CommandFactory();
          final ICommand command = commandFactory.getCommand(line);

          if(command instanceof CloseConnectionCommand){
            break;
          } else if (command instanceof ServerShutDownCommand) {
            logger.warn(LOGGER_SHUTTING_DOWN_THE_SERVER);
            server.stop();
            break;
          }
          command.execute(server, out, line);
        }
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    }finally{
      if(in != null){
        in.close();
      }
      if(out != null){
        out.close();
      }
      socket.close();
    }
  }

  private void welcomeMessage(final PrintWriter out) {
    out.println(OUTPUT_WELCOME_TO_MOI_SQL_SERVER);
    out.println(OUTPUT_WE_ARE_ALL_PROGRAMMED_TO_RECEIVE);
  }
}
