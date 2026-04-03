package de.dan.hobby.moisql.database;

import de.dan.hobby.moisql.datatype.text.VarChar;
import de.dan.hobby.moisql.table.Table;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Daniel Laible
 * @since 0.1.6
 * <p>
 * This class contains all the things needed for a multi-table database
 */
public class Database {

  private static final Logger logger = LoggerFactory.getLogger(Database.class);

  private VarChar dbName;

  private List<Table> tables;

  /**
   * creates a new database
   *
   * @param dbName the name of the database
   */
  public Database(VarChar dbName) {
    this.dbName = dbName;
  }

  public void addTable(Table table) {
    tables.add(table);
  }

  public List<Table> getTables() {
    return tables;
  }

  public void saveDatabase() {
    //TODO
  }

}
