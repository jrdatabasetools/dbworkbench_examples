
package plsql_workbench_examples.springapi;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.stereotype.Component;

import plsql_workbench_examples.timer.TimerCounter;
import service.BulkSelectCollectionObjectService;
import transferobject.BulkObject;

@Component
public class BulkSelectCollectionObjectsSpringApi {
  private final static int                  ELEMENTS = 50000;

  @Autowired
  private BulkSelectCollectionObjectService bulkSelectCollectionObjectService;

  public static void main(String[] args)
  {
    // set database credentials and configuration parameters
    System.setProperty("dbw_examples.url", "jdbc:oracle:thin:@192.168.0.102:1521/orcl");
    System.setProperty("dbw_examples.username", "dbw_examples");
    System.setProperty("dbw_examples.password", "dbw_examples");
    System.setProperty("dbw_examples.poolsize.min", Integer.toString(3));
    System.setProperty("dbw_examples.poolsize.max", Integer.toString(10));

    // Register Spring Beans, Spring Context and call demo method 
    try (GenericApplicationContext ctx = BaseSpringConfig.getCtx(BulkSelectCollectionObjectsSpringApi.class)) {
      ctx.getBean(BulkSelectCollectionObjectsSpringApi.class).runDemo();
    }
  }

  private void runDemo()
  {
    try {
      // timer to check the throughput
      TimerCounter tc = new TimerCounter();

      // calling the stored procedure
      List<BulkObject> result = bulkSelectCollectionObjectService.call(ELEMENTS);

      // print out throughput
      System.out.println(tc.perSecond("objects select bulk performance", result.size()));
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }
}