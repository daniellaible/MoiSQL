package de.dan.hobby.moisql.datatype;

import java.time.ZoneId;
import java.util.Set;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class Playground {

  @Test
  @Disabled
  void testZimeZoneIds(){
    final Set<String> availableZoneIds = ZoneId.getAvailableZoneIds();
    for(String zoneId : availableZoneIds){
      System.out.println(zoneId);
    }
  }

}
