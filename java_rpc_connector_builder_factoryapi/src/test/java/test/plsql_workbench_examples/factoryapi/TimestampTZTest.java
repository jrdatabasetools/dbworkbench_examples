
package test.plsql_workbench_examples.factoryapi;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;

import org.junit.jupiter.api.Test;

import factory.ExamplesRPCFactory;
import service.TimestampTzPkgService;

public class TimestampTZTest {

  @Test
  public void runDemo() throws Exception {
    TimestampTzPkgService service = ExamplesRPCFactory.getTimestampTzPkgService();

    assertNull(service.fncDateNull());
    assertNull(service.fncTimestampNull());
    assertNull(service.fncTimestampWltzNull());
    assertNull(service.fncTimestampWtzNull());

    ZoneId defaultZoneId = ZoneId.systemDefault();

    assertEquals(ZonedDateTime.of(LocalDateTime.of(2015, 8, 5, 1, 0, 0), ZoneId.of("US/Pacific")),
                 service.fncTimestampWtz("8/5/2015 1:00 AM US/Pacific", "MM/DD/YYYY HH:mi AM TZR"));
    
    assertEquals(ZonedDateTime.of(2015, 8, 5, 1, 0, 0, 0, ZoneId.of("US/Pacific")).withZoneSameInstant(defaultZoneId),
                 service.fncTimestampWltz("8/5/2015 1:00 AM US/Pacific", "MM/DD/YYYY HH:mi AM TZR"));

    ZoneId zurich = ZoneId.of("Europe/Zurich");
    ZoneId pacific = ZoneId.of("US/Pacific");

    LocalDateTime somedayInTheSummer = LocalDateTime.of(2023, 7, 1, 1, 2, 3);

    ZonedDateTime zurichDateTime = ZonedDateTime.of(somedayInTheSummer, zurich);
    ZonedDateTime pacificDateTime = zurichDateTime.withZoneSameInstant(pacific);
    Duration duration = diffBetweenZones(zurichDateTime.getZone(), pacificDateTime.getZone());

    assertEquals(zurichDateTime.toLocalDateTime(),
                 pacificDateTime.toLocalDateTime().plusSeconds(duration.get(ChronoUnit.SECONDS)));

    ZonedDateTime oraPacificZonedDateTime = service.fncTimestampWtz("09/1/2022 2:33 AM US/Pacific",
                                                                    "MM/DD/YYYY HH:mi AM TZR");
    ZonedDateTime oraPacificLocalZonedDateTime = service.fncTimestampWltz("09/1/2022 2:33 AM US/Pacific",
                                                                          "MM/DD/YYYY HH:mi AM TZR");
    assertEquals(oraPacificZonedDateTime.toLocalDateTime(),
                 oraPacificLocalZonedDateTime.toLocalDateTime().minusSeconds(duration.get(ChronoUnit.SECONDS)));

    ZonedDateTime oraZurichLocalZonedDateTime = service.fncTimestampWltz("09/01/2022 11:33 AM Europe/Zurich",
                                                                         "MM/DD/YYYY HH:mi AM TZR");
    ZonedDateTime oraZurichZonedDateTime = service.fncTimestampWtz("09/01/2022 11:33 AM Europe/Zurich",
                                                                   "MM/DD/YYYY HH:mi AM TZR");
    assertEquals(oraZurichLocalZonedDateTime, oraZurichZonedDateTime.withZoneSameInstant(defaultZoneId));
  }

  private Duration diffBetweenZones(ZoneId z1, ZoneId z2) {
    LocalDate anyDay = LocalDate.of(2023, 7, 1);
    return Duration.between(anyDay.atStartOfDay(z1), anyDay.atStartOfDay(z2));
  }
}
