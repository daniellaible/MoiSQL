package de.dan.hobby.moisql.server.startup;

import de.dan.hobby.moisql.server.dbmfile.DbmFile;
import de.dan.hobby.moisql.server.OSType;
import java.io.File;

/**
 * @author Daniel Laible
 * @since 0.1.6
 *
 * Context POJO for StartupSequence strategy
 */
public class StartupContext {

  public OSType osType;
  public File dbmDir;
  public File dbmPath;
  public DbmFile dbmFile;


  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("StartupContext{");
    sb.append("osType=").append(osType);
    sb.append(", dbmDir=").append(dbmDir);
    sb.append(", dbmPath=").append(dbmPath);
    sb.append(", dbmFile=").append(dbmFile);
    sb.append('}');
    return sb.toString();
  }
}
