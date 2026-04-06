package de.dan.hobby.moisql.server;

import java.util.Locale;

public class OSDetector {

  public static OSType detectOS() {
    String os = System.getProperty("os.name", "generic").toLowerCase(Locale.ENGLISH);
    if (os.contains("win")) {
      return OSType.WINDOWS;
    } else if (os.contains("nix") || os.contains("nux") || os.contains("aix")) {
      return OSType.LINUX;
    } else if (os.contains("mac")) {
      return OSType.MAC;
    } else if (os.contains("sunos")) {
      return OSType.SOLARIS;
    } else {
      return OSType.OTHER;
    }
  }

/*  private Optional<DbmFile> checkDbFileMac() {
    logger.warn("MacOS is not yet supported");
    return Optional.empty();
  }

  //TODO implement DbFile for Solaris
  private Optional<DbmFile> checkDbFileSolaris() {
    logger.warn("Solaris is not yet supported");
    return Optional.empty();
  }

  //TODO test it on linux
  private Optional<DbmFile> checkDbFileLinux() {
    File file = new File("//bin//moidb//dbs.dbm");
    DbmFile dbmFile = null;
    if (file.exists()) {
      dbmFile = importDbmFile(file);
    }

    if (dbmFile == null) {
      return Optional.empty();
    } else {
      return Optional.of(dbmFile);
    }
  }


  private Optional<DbmFile> checkDbFileWin() {
    File file = new File("C:\\moidb\\dbs.dbm");
    DbmFile dbmFile = null;
    if (file.exists()) {
      dbmFile = importDbmFile(file);
    }

    if (dbmFile == null) {
      return Optional.empty();
    } else {
      return Optional.of(dbmFile);
    }
  }*/

}
