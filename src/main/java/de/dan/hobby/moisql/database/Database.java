package de.dan.hobby.moisql.database;

import de.dan.hobby.moisql.datatype.text.VarChar;
import de.dan.hobby.moisql.server.dbmfile.DbmFile;
import de.dan.hobby.moisql.server.dbmfile.DbmTable;
import de.dan.hobby.moisql.table.DiscImporter;
import de.dan.hobby.moisql.table.Table;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
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

  private File tableDir;

  /**
   * creates a new database
   *
   * @param dbName the name of the database
   */
  public Database(VarChar dbName) {
    this.tables = new ArrayList<>();
    this.dbName = dbName;
  }

  public Database(VarChar dbName, File dir){
    this.tables = new ArrayList<>();
    this.dbName = dbName;
    this.tableDir = dir;
  }

  private boolean checkIfDbNameExists(DbmFile dbmFile) {
    return false;
  }

  public String getDbName() {
    return dbName.toString();
  }

  public Table getTable(String tableName) {
    for (Table table : tables) {
      if(tableName.equalsIgnoreCase(table.getTableName())){
        return table;
      }
    }
    return null;
  }

  /**
   * Loads the database and all its tables from disc
   */
  public void loadDatabase(List<DbmTable> dbmTables) throws IOException {
    if(tableDir == null || !tableDir.exists()) {
      logger.warn("Directory in which the tables are stored is either null or unavailable");
      return;
    }
    for(DbmTable dbmTable : dbmTables) {
      final String tableUuid = dbmTable.getUuid();
      DiscImporter importer = new DiscImporter(tableDir, UUID.fromString(tableUuid));
      Table importedTable = importer.loadTable();
      tables.add(importedTable);
    }
  }

  public void addTable(Table table) {
    tables.add(table);
  }

  public List<Table> getTables() {
    return tables;
  }

  public void saveDatabase() {
  }
}
