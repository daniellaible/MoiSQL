package de.dan.hobby.moisql.server.comm.commandFactory.commands.dml;

import de.dan.hobby.moisql.database.Database;
import de.dan.hobby.moisql.server.IServer;
import de.dan.hobby.moisql.server.comm.commandFactory.ICommand;
import de.dan.hobby.moisql.server.comm.commandFactory.commands.query.WhereClause;
import de.dan.hobby.moisql.server.comm.commandFactory.commands.query.WhereClauseTokens;
import de.dan.hobby.moisql.table.Table;
import java.io.PrintWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InsertCommand implements ICommand {

  private static final Logger logger = LoggerFactory.getLogger(InsertCommand.class);

  private String tableName = null;
  private String[] columnNames = null;
  private String[] data = null;
  private WhereClauseTokens whereClauseTokens = null;

  @Override
  public void execute(IServer server, PrintWriter out, String line) {
    logger.info("Insert command running");
    tokenize(line);
    //checkIfLegit();
    final Database database = server.getDatabase();
    Table table = database.getTable(tableName);
    long[] idsToChange = findEntriesToChange(table, whereClauseTokens);

    //get row for each id
    //for length of columnNames
    //find position of column
    //          enter value from data
  }

  private long[] findEntriesToChange(Table table, WhereClauseTokens whereClauseTokens) {
    return null;
  }

  private void tokenize(String line) {
    int firstOpenBracket = findIndexOf(line, "(", 1);
    int firstClosingBracket = findIndexOf(line, ")", 1);
    int secondOpenBracket = findIndexOf(line, "(", 2);
    int secondClosingBracket = findIndexOf(line, ")", 2);

    columnNames = cutOut(line, firstOpenBracket, firstClosingBracket);
    data = cutOut(line, secondOpenBracket, secondClosingBracket);
    tableName = findTableName(line);
    WhereClause clause = new WhereClause();
    whereClauseTokens = clause.analyze(line);
  }

  private String findTableName(String line) {
    String[] splits = line.split(" ");
    return splits[2].trim();
  }

  private String[] cutOut(String line, int pos1, int pos2) {
    String result = line.substring(pos1 + 1, pos2);
    return result.split(",");
  }

  private int findIndexOf(String line, String subString, int nth) {
    if (nth == 1) {
      return line.indexOf(subString);
    } else {
      return line.indexOf(subString, findIndexOf(line, subString, nth - 1) + subString.length());
    }
  }
}
