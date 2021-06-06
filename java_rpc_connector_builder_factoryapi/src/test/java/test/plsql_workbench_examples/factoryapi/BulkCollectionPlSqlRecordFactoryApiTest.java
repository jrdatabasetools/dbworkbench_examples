
package test.plsql_workbench_examples.factoryapi;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Test;

import factory.ExamplesRPCFactory;
import plsql_workbench_examples.timer.TimerCounter;
import service.BulkPlsqlRecordService;
import transferobject.BulkPlsqlRecordTO;

public class BulkCollectionPlSqlRecordFactoryApiTest {
  private final static int ELEMENTS = 50000;

  @Test
  public void runDemo() throws Exception
  {
    // getting the service
    BulkPlsqlRecordService service = ExamplesRPCFactory.getBulkPlsqlRecordService();

    // generating 50000 elements to transfer to the stored procedure 
    List<BulkPlsqlRecordTO.PlsqlRecord> list = new ArrayList<>();
    for (int i = 0; i < ELEMENTS; i++) {
      BulkPlsqlRecordTO.PlsqlRecord o = new BulkPlsqlRecordTO.PlsqlRecord();
      o.d = new Date(System.currentTimeMillis() + (long) (Math.random() * Integer.MAX_VALUE));
      o.s = UUID.randomUUID().toString();
      o.n = (int) (Math.random() * Integer.MAX_VALUE);
      list.add(o);
    }

    // timer to check the throughput
    TimerCounter tc = new TimerCounter();

    // calling the stored procedure
    service.doit(list);

    // print out throughput
    System.out.println(tc.perSecond("collection of pl/sql record element bulk performance (first call)", ELEMENTS));

    // calling the stored procedure
    service.doit(list);

    // print out throughput
    System.out.println(tc.perSecond("collection of pl/sql record element bulk performance (second call)", ELEMENTS));
  }
}
