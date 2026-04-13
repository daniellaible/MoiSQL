package de.dan.hobby.moisql.server.comm.commandFactory.commands.dml;

import static org.junit.jupiter.api.Assertions.*;

import de.dan.hobby.moisql.server.IServer;
import de.dan.hobby.moisql.server.Server;
import de.dan.hobby.moisql.server.comm.commandFactory.commands.TestDummyServer;
import java.io.PrintWriter;
import org.junit.jupiter.api.Test;

class InsertCommandTest {

  @Test
  void testInsertCommand(){
    String insert = "INSERT INTO population (lng, lat) VALUES (0.1, 0.2) WHERE id=1;";
    InsertCommand insertCommand = new InsertCommand();
    IServer server = new TestDummyServer();
    PrintWriter out = new PrintWriter(System.out);
    insertCommand.execute(server, out, insert);
  }

}