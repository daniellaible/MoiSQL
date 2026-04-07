package de.dan.hobby.moisql.server.DbmFile;

import java.util.Objects;

/**
 * @author Daniel Laible
 * @since 0.1.6
 * <p>
 * POJO class that stores all the locations of previous created tables
  */
public class DbmTable {

  private String tableName = "";

  private String uuid = "";

  public String getTableName() {
    return tableName;
  }

  public void setTableName(String tableName) {
    this.tableName = tableName;
  }

  public String getUuid() {
    return uuid;
  }

  public void setUuid(String uuid) {
    this.uuid = uuid;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    DbmTable dbmTable = (DbmTable) o;
    return Objects.equals(tableName, dbmTable.tableName) && Objects.equals(uuid, dbmTable.uuid);
  }

  @Override
  public int hashCode() {
    int result = Objects.hashCode(tableName);
    result = 31 * result + Objects.hashCode(uuid);
    return result;
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("DbmTable{");
    sb.append("tableName='").append(tableName).append('\'');
    sb.append(", uuid='").append(uuid).append('\'');
    sb.append('}');
    return sb.toString();
  }
}
