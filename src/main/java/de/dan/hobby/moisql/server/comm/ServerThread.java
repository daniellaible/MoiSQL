package de.dan.hobby.moisql.server.comm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServerThread extends Thread{

  private static final Logger logger = LoggerFactory.getLogger(ServerThread.class);
  private final Socket socket;

  public ServerThread(final Socket socket) {
    this.socket = socket;
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
      in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
      welcomeMessage(out);

      String line;
      while ((line = in.readLine()) != null) {
        if(!line.isBlank()){
          line = line.trim();
          out.println(line);

          if(line.equalsIgnoreCase("bye") || line.equalsIgnoreCase("quit")){
            break;
          }
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
    out.println("We are all programmed to receive");
  }


}
