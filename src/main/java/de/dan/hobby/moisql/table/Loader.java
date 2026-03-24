package de.dan.hobby.moisql.table;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.UUID;

public class Loader {

  public Loader(File directory, UUID uuid) throws IOException {

    String uuidAsString = uuid.toString().replace("-", "");
    String fileName = uuid + ".moi";
    String path = directory.getAbsolutePath() + File.separator + fileName;
    FileInputStream in = null;
    try{
      in = new FileInputStream(path);
      byte[] buffer = new byte[8192];
      int bytesRead;

      while ((bytesRead = in.read(buffer)) != -1) {
        System.out.print(new String(buffer, 0, bytesRead));
      }
    }catch(IOException e){
      System.out.println("Doof");
    }finally{
      if(in != null){
        in.close();
      }
    }
  }
}
