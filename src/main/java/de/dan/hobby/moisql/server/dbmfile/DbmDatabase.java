package de.dan.hobby.moisql.server.dbmfile;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Daniel Laible
 * @since 0.1.6
 * <p>
 * POJO class that stores all the locations of previous created databases
 * as well as their table locaction
 */
public class DbmDatabase {

  private String dbName = "";

  private String dbPath = "";

  private List<DbmTable> tables = new ArrayList<>();

  public String getDbName() {
    return dbName;
  }

  public void setDbName(String dbName) {
    this.dbName = dbName;
  }

  public String getDbPath() {
    return dbPath;
  }

  public void setDbPath(String dbPath) {
    this.dbPath = dbPath;
  }

  public List<DbmTable> getTables() {
    return tables;
  }

  public void setTables(List<DbmTable> tables) {
    this.tables = tables;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    DbmDatabase that = (DbmDatabase) o;
    return Objects.equals(dbName, that.dbName) && Objects.equals(dbPath, that.dbPath) && Objects.equals(tables,
        that.tables);
  }

  @Override
  public int hashCode() {
    int result = Objects.hashCode(dbName);
    result = 31 * result + Objects.hashCode(dbPath);
    result = 31 * result + Objects.hashCode(tables);
    return result;
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("DbmDatabase{");
    sb.append("dbName='").append(dbName).append('\'');
    sb.append(", dbPath='").append(dbPath).append('\'');
    sb.append(", tables=").append(tables);
    sb.append('}');
    return sb.toString();
  }
}
