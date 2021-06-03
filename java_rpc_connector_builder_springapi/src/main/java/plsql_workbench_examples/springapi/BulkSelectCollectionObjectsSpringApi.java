
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