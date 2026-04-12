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
      logger.error("Something wrong in process message", e);
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
          out.println("Received: " + line);

          CommandFactory commandFactory = new CommandFactory();
          final ICommand command = commandFactory.getCommand(line);

          if(command instanceof CloseConnectionCommand){
            break;
          } else if (command instanceof ServerShutDownCommand) {
            logger.warn("Shutting down the server");
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
    out.println("Welcome to MoiSQL Server");
    out.println("We are all programmed to receive");
  }
}
