
package test.plsql_workbench_examples.springapi;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import plsql_workbench_examples.configuration.BaseSpringConfig;
import plsql_workbench_examples.timer.TimerCounter;
import service.BulkCollectionScalarService;

@ContextConfiguration(classes = { BaseSpringConfig.class })
@ExtendWith(SpringExtension.class)
public class BulkCollectionScalarSpringApiTest {
  private final static int            ELEMENTS = 50000;

  @Autowired
  private BulkCollectionScalarService bulkCollectionScalarService;

  @Test
  public void runDemo() throws Exception
  {
    // generating 50000 elements to transfer to the stored procedure 
    List<Integer> numberList = new ArrayList<Integer>();
    List<Date> dateList = new ArrayList<Date>();
    List<String> stringList = new ArrayList<String>();
    for (int i = 0; i < ELEMENTS; i++) {
      numberList.add((int) (Math.random() * Integer.MAX_VALUE));
      dateList.add(new Date(System.currentTimeMillis() + (long) (Math.random() * Integer.MAX_VALUE)));
      stringList.add(UUID.randomUUID().toString());
    }

    // timer to check the throughput
    TimerCounter tc = new TimerCounter();

    // calling the stored procedure
    bulkCollectionScalarService.call(numberList, dateList, stringList);

    // print out throughput
    System.out.println(tc.perSecond("scalar bulk performance (first call)", ELEMENTS));

    // calling the stored procedure
    bulkCollectionScalarService.call(numberList, dateList, stringList);

    // print out throughput
    System.out.println(tc.perSecond("scalar bulk performance (second call)", ELEMENTS));
  }
}
