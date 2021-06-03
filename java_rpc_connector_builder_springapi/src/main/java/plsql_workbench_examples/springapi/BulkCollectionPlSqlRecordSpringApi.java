
package plsql_workbench_examples.springapi;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.stereotype.Component;

import plsql_workbench_examples.timer.TimerCounter;
import service.BulkPlsqlRecordService;
import transferobject.BulkPlsqlRecordTO;

@Component
public class BulkCollectionPlSqlRecordSpringApi {
  private final static int       ELEMENTS = 50000;

  @Autowired
  private BulkPlsqlRecordService bulkPlsqlRecordService;

  public static void main(String[] args)
  {
    // Register Spring Beans, Spring Context and call demo method 
    try (GenericApplicationContext ctx = BaseSpringConfig.getCtx(BulkCollectionPlSqlRecordSpringApi.class)) {
      ctx.getBean(BulkCollectionPlSqlRecordSpringApi.class).runDemo();
    }
  }

  private void runDemo()
  {
    try {
      // generating 50000 elements to transfer to the stored procedure 
      List<BulkPlsqlRecordTO.PlsqlRecord> list = new ArrayList<>();
      for (int i = 0; i < ELEMENTS; i++) {
        BulkPlsqlRecordTO.PlsqlRecord o = new BulkPlsqlRecordTO.PlsqlRecord();
        o.setD(new Date(System.currentTimeMillis() + (long) (Math.random() * Integer.MAX_VALUE)));
        o.setS(UUID.randomUUID().toString());
        o.setN((int) (Math.random() * Integer.MAX_VALUE));
        list.add(o);
      }

      // timer to check the throughput
      TimerCounter tc = new TimerCounter();

      // calling the stored procedure
      bulkPlsqlRecordService.doit(list);

      // print out throughput
      System.out.println(tc.perSecond("collection of pl/sql record element bulk performance", ELEMENTS));
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }
}
