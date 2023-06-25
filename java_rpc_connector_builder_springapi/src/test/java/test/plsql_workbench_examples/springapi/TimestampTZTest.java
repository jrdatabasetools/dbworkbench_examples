
package test.plsql_workbench_examples.springapi;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import plsql_workbench_examples.springapi.BaseSpringConfig;
import service.TimestampTzPkgService;

@ContextConfiguration(classes = { BaseSpringConfig.class })
@ExtendWith(SpringExtension.class)
public class TimestampTZTest {
  @Autowired
  private TimestampTzPkgService timestampTzPkgService;

  @Test
  public void runDemo() throws Exception {
    assertNull(timestampTzPkgService.fncDateNull());
    assertNull(timestampTzPkgService.fncTimestampNull());
    assertNull(timestampTzPkgService.fncTimestampWltzNull());
    assertNull(timestampTzPkgService.fncTimestampWtzNull());

    ZoneId defaultZoneId = ZoneId.systemDefault();

    assertEquals(ZonedDateTime.of(LocalDateTime.of(2015, 8, 5, 1, 0, 0), ZoneId.of("US/Pacific")),
                 timestampTzPkgService.fncTimestampWtz("8/5/2015 1:00 AM US/Pacific", "MM/DD/YYYY HH:mi AM TZR"));
    assertEquals(ZonedDateTime.of(2015, 8, 5, 1, 0, 0, 0, ZoneId.of("US/Pacific")).withZoneSameInstant(defaultZoneId),
                 timestampTzPkgService.fncTimestampWltz("8/5/2015 1:00 AM US/Pacific", "MM/DD/YYYY HH:mi AM TZR"));

    ZoneId zurich = ZoneId.of("Europe/Zurich");
    ZoneId pacific = ZoneId.of("US/Pacific");

    LocalDateTime now = LocalDateTime.now();

    ZonedDateTime zurichDateTime = ZonedDateTime.of(now, zurich);
    ZonedDateTime pacificDateTime = zurichDateTime.withZoneSameInstant(pacific);
    Duration duration = diffBetweenZones(zurichDateTime.getZone(), pacificDateTime.getZone());

    assertEquals(zurichDateTime.toLocalDateTime(),
                 pacificDateTime.toLocalDateTime().plusSeconds(duration.get(ChronoUnit.SECONDS)));

    ZonedDateTime oraPacificZonedDateTime = timestampTzPkgService.fncTimestampWtz("09/1/2022 2:33 AM US/Pacific",
                                                                                  "MM/DD/YYYY HH:mi AM TZR");
    ZonedDateTime oraPacificLocalZonedDateTime = timestampTzPkgService.fncTimestampWltz("09/1/2022 2:33 AM US/Pacific",
                                                                                        "MM/DD/YYYY HH:mi AM TZR");
    assertEquals(oraPacificZonedDateTime.toLocalDateTime(),
                 oraPacificLocalZonedDateTime.toLocalDateTime().minusSeconds(duration.get(ChronoUnit.SECONDS)));

    ZonedDateTime oraZurichLocalZonedDateTime = timestampTzPkgService.fncTimestampWltz("09/01/2022 11:33 AM Europe/Zurich",
                                                                                       "MM/DD/YYYY HH:mi AM TZR");
    ZonedDateTime oraZurichZonedDateTime = timestampTzPkgService.fncTimestampWtz("09/01/2022 11:33 AM Europe/Zurich",
                                                                                 "MM/DD/YYYY HH:mi AM TZR");
    assertEquals(oraZurichLocalZonedDateTime, oraZurichZonedDateTime.withZoneSameInstant(defaultZoneId));
  }

  private Duration diffBetweenZones(ZoneId z1, ZoneId z2) {
    LocalDate today = LocalDate.now();
    return Duration.between(today.atStartOfDay(z1), today.atStartOfDay(z2));
  }
}