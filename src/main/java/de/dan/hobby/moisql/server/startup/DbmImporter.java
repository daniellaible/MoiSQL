package de.dan.hobby.moisql.server.startup;

import de.dan.hobby.moisql.server.DbmFile.DbmFile;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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

  private static final String DBM_PATH_WINDOWS = "C:\\moidb\\moi.dbm";
  private static final String DBM_PATH_LINUX = "//bin//moidb//moi.dbm";


  @Override
  public void commence(StartupContext context) {
    Optional<DbmFile> dbmFile = Optional.empty();
    switch (context.osType) {
      case WINDOWS:
        dbmFile = loadDbmFile(DBM_PATH_WINDOWS);
        break;
      case LINUX:
        dbmFile = loadDbmFile(DBM_PATH_LINUX);
      default:
        logger.warn("Unsupported os type");
    }
  }

  private String removeTags(String line, String tag1, String tag2) {
    String data = line.replace(tag1, "");
    data = line.replace(tag2, "");
    return data.trim();
  }

  private Optional<DbmFile> loadDbmFile(String path) {
    File dbmFile = new File(path);

    try (BufferedReader br = new BufferedReader(new FileReader(path))) {

      TempDbStorage storage = null;
      TempTableStorage tableStorage = null;
      String line;
      while ((line = br.readLine()) != null) {
        if (line.startsWith("<db>")) {
          storage = new TempDbStorage();
        } else if (line.startsWith("<name>")) {
          storage.dbName = removeTags(line, "<name>", "</name>");
        } else if (line.startsWith("<path>")) {
          storage.dbPath = removeTags(line, "<path>", "</path>");
        } else if (line.startsWith("<table>")) {
          tableStorage = new TempTableStorage();
        }else if (line.startsWith("<tablename>")) {
          tableStorage.tableName = removeTags(line, "<tablename>", "</tablename>");
        }else if (line.startsWith("<uuid>")) {
          tableStorage.uuid = removeTags(line, "<uuid>", "</uuid>");
        }else if (line.startsWith("</table>")) {
          storage.tables.add(tableStorage);
        }
      }

      return Optional.empty();
    } catch (FileNotFoundException e) {
      throw new RuntimeException(e);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  class TempTableStorage {

    String tableName = "";
    String uuid = "";
  }

  class TempDbStorage {

    String dbName = "";
    String dbPath = "";
    List<TempTableStorage> tables = new ArrayList<>();
  }
}


