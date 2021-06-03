
package plsql_workbench_examples.springapi;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.stereotype.Component;

import plsql_workbench_examples.timer.TimerCounter;
import service.BulkCollectionScalarService;

@Component
public class BulkCollectionScalarSpringApi {
  private final static int            ELEMENTS = 50000;

  @Autowired
  private BulkCollectionScalarService bulkCollectionScalarService;

  public static void main(String[] args)
  {
    // Register Spring Beans, Spring Context and call demo method 
    try (GenericApplicationContext ctx = BaseSpringConfig.getCtx(BulkCollectionScalarSpringApi.class)) {
      ctx.getBean(BulkCollectionScalarSpringApi.class).runDemo();
    }
  }

  private void runDemo()
  {
    try {
      // generating 50000 elements to transfer to the stored procedure 
      List<Integer> numberList = new ArrayList<>();
      List<Date> dateList = new ArrayList<>();
      List<String> stringList = new ArrayList<>();
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
      System.out.println(tc.perSecond("scalar bulk performance", ELEMENTS));
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }
}
