package de.dan.hobby.moisql.table;

import de.dan.hobby.moisql.datatype.IDataType;
import de.dan.hobby.moisql.datatype.numeric.Decimal;
import de.dan.hobby.moisql.datatype.numeric.Int;
import de.dan.hobby.moisql.datatype.text.VarChar;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class BaseTest {

  static List<City> cities = new ArrayList<>();

  static Table table;

  @BeforeAll
  static void readCitiesCsv() {
    File citiesFile = new File("C:\\temp\\simplemaps\\worldcities_short.csv");
    try {
      FileInputStream fiStream = new FileInputStream(citiesFile);
      DataInputStream in = new DataInputStream(fiStream);
      BufferedReader br = new BufferedReader(new InputStreamReader(in));

      String line;
      int counter = 0; //need this to ignore first line
      while ((line = br.readLine()) != null) {
        if (counter > 0 && !line.contains("Korea")
            && !line.contains("City of")
            && !line.contains("Misto")
            && !line.contains("Ciudad Autónoma de")
            && !line.contains(" Grad")
            && !line.contains("The")) {
          String[] splits = line.split(",");

          for(int j = 0; j < splits.length; j++) {
            if(j == 1 || j == 2 || j == 3 || j == 4 || j ==9) {
              splits[j] = splits[j].replace('"', ' ').trim();
            }
          }
          cities.add(new City(splits[1], Float.parseFloat(splits[2]), Float.parseFloat(splits[3]), splits[4], Integer.parseInt(splits[9])));
        }
        counter++;
      }
      in.close();

      IDataType[] typeRow = new IDataType[] {new Int(0),new VarChar(""),new Decimal(0f), new Decimal(0f),new VarChar(""), new Int(0)};
      VarChar[] columns = new VarChar[]{new VarChar("id"), new VarChar("name"), new VarChar("lat"), new VarChar("lng"), new VarChar(
          "country"), new VarChar(
          "population")};
      table = new Table("cities", typeRow, columns);

      int idCounter = 1;
      for(City city : cities) {
        IDataType[] row = new IDataType[]{new Int(idCounter), new VarChar(city.name()),new Decimal(city.lat()), new Decimal(city.lng()),
            new VarChar(city.country()), new Int(city.population())};
        table.insert(row);
        idCounter++;
      }

    } catch (FileNotFoundException e) {
      throw new RuntimeException(e);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}

record City(String name, float lat, float lng, String country, int population) {}
