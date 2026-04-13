package de.dan.hobby.moisql.server.dbmfile;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Daniel Laible
 * @since 0.1.6
 * <p>
 * POJO class that stores all previous created databases
 */
public class DbmFile {

  private List<DbmDatabase> dbnames = new ArrayList<>();

  public List<DbmDatabase> getDbnames() {
    return dbnames;
  }

  public void setDbnames(List<DbmDatabase> dbnames) {
    this.dbnames = dbnames;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    DbmFile dbmFile = (DbmFile) o;
    return Objects.equals(dbnames, dbmFile.dbnames);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(dbnames);
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("DbmFile{");
    sb.append("dbnames=").append(dbnames);
    sb.append('}');
    return sb.toString();
  }
}

