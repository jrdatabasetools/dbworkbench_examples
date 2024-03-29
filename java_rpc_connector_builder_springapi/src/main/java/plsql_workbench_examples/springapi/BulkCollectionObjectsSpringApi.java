
package plsql_workbench_examples.springapi;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.stereotype.Component;

import plsql_workbench_examples.configuration.BaseSpringConfig;
import plsql_workbench_examples.timer.TimerCounter;
import service.BulkCollectionObjectService;
import transferobject.BulkObject;

@Component
public class BulkCollectionObjectsSpringApi {
  private final static int            ELEMENTS = 50000;

  @Autowired
  private BulkCollectionObjectService bulkCollectionObjectService;

  public static void main(String[] args)
  {
    // set database credentials and configuration parameters
    System.setProperty("dbw_examples.url", "jdbc:oracle:thin:@192.168.0.109:1521/orcl");
    System.setProperty("dbw_examples.username", "dbw_examples");
    System.setProperty("dbw_examples.password", "dbw_examples");

    // Register Spring Beans, Spring Context and call demo method 
    try (GenericApplicationContext ctx = BaseSpringConfig.getCtx(BulkCollectionObjectsSpringApi.class)) {
      ctx.getBean(BulkCollectionObjectsSpringApi.class).runDemo();
    }
  }

  private void runDemo()
  {
    try {
      // generating 50000 elements to transfer to the stored procedure 
      List<BulkObject> objectList = new ArrayList<>(ELEMENTS);
      for (int i = 0; i < ELEMENTS; i++) {
        BulkObject o = new BulkObject();
        o.setD(new Date(System.currentTimeMillis() + (long) (Math.random() * Integer.MAX_VALUE)));
        o.setS(UUID.randomUUID().toString());
        o.setN((int) (Math.random() * Integer.MAX_VALUE));
        objectList.add(o);
      }

      // timer to check the throughput
      TimerCounter tc = new TimerCounter();

      // calling the stored procedure
      bulkCollectionObjectService.call(objectList);

      // print out throughput
      System.out.println(tc.perSecond("objects bulk performance (first call)", ELEMENTS));

      // calling the stored procedure
      bulkCollectionObjectService.call(objectList);

      // print out throughput
      System.out.println(tc.perSecond("objects bulk performance (second call)", ELEMENTS));
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }
}
