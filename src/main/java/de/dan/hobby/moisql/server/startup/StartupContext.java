package de.dan.hobby.moisql.server.startup;

import de.dan.hobby.moisql.server.DbmFile.DbmFile;
import de.dan.hobby.moisql.server.OSType;

/**
 * @author Daniel Laible
 * @since 0.1.6
 *
 * Context POJO for StartupSequence strategy
 */
public class StartupContext {

  public OSType osType;
  public DbmFile dbmFile;

}
