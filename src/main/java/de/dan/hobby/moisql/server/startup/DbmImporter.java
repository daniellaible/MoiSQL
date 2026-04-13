package de.dan.hobby.moisql.server.startup;

import de.dan.hobby.moisql.server.dbmfile.DbmDatabase;
import de.dan.hobby.moisql.server.dbmfile.DbmFile;
import de.dan.hobby.moisql.server.dbmfile.DbmTable;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Daniel Laible
 * @since 0.1.6
 * <p>
 * Part of the StartupSequence Strategy
 * Reads the .dbm file for already existing moi databases on disc
 */
public class DbmImporter implements IStartupSequence {

  private static final Logger logger = LoggerFactory.getLogger(DbmImporter.class);

  private static final String DB_START = "<db>";
  private static final String NAME_START = "<name>";
  private static final String NAME_END = "</name>";
  private static final String PATH_START = "<path>";
  private static final String PATH_END = "</path>";
  private static final String TABLE_START = "<table>";
  private static final String TABLENAME_START = "<tablename>";
  private static final String TABLENAME_END = "</tablename>";
  private static final String UUID_START = "<uuid>";
  private static final String UUID_END = "</uuid>";
  private static final String TABLE_END = "</table>";
  private static final String DB_END = "</db>";

  private static final String LOGGER_UNABLE_TO_READ_DBM_FILE = "Unable to read dbm file";
  private static final String LOGGER_LOADED_DBM_FILE = "Loaded dbm file {}";


  @Override
  public void commence(StartupContext context) {
    Optional<DbmFile> dbmFile = loadDbmFile(context.dbmPath);

    if(dbmFile.isPresent()) {
      context.dbmFile = dbmFile.get();
    }else{
      logger.warn(LOGGER_UNABLE_TO_READ_DBM_FILE);
    }
  }

  private String removeTags(String line, String tag1, String tag2) {
    String data = line.replace(tag1, "");
    data = data.replace(tag2, "");
    return data.trim();
  }

  private Optional<DbmFile> loadDbmFile(File path) {
    DbmFile dbmFile = new DbmFile();
    try (BufferedReader br = new BufferedReader(new FileReader(path))) {

      DbmDatabase storage = null;
      DbmTable tableStorage = null;
      String line;
      while ((line = br.readLine()) != null) {
        if (line.startsWith(DB_START)) {
          storage = new DbmDatabase();
        } else if (line.startsWith(NAME_START)) {
          storage.setDbName(removeTags(line, NAME_START, NAME_END));
        } else if (line.startsWith(PATH_START)) {
          storage.setDbPath(removeTags(line, PATH_START, PATH_END));
        } else if (line.startsWith(TABLE_START)) {
          tableStorage = new DbmTable();
        }else if (line.startsWith(TABLENAME_START)) {
          tableStorage.setTableName(removeTags(line, TABLENAME_START, TABLENAME_END));
        }else if (line.startsWith(UUID_START)) {
          tableStorage.setUuid(removeTags(line, UUID_START, UUID_END));
        }else if (line.startsWith(TABLE_END)) {
          storage.getTables().add(tableStorage);
        }else if (line.startsWith(DB_END)) {
          dbmFile.getDbnames().add(storage);
        }
      }
      logger.info(LOGGER_LOADED_DBM_FILE, dbmFile);
      return Optional.of(dbmFile);
    } catch (FileNotFoundException e) {
      throw new RuntimeException(e);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}


