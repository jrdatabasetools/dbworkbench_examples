
package test.plsql_workbench_examples.springapi;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import plsql_workbench_examples.configuration.BaseSpringConfig;
import plsql_workbench_examples.timer.TimerCounter;
import service.BulkSelectCollectionObjectService;
import transferobject.BulkObject;

@ContextConfiguration(classes = { BaseSpringConfig.class })
@ExtendWith(SpringExtension.class)
public class BulkSelectCollectionObjectsSpringApiTest {
  private final static int                  ELEMENTS = 50000;

  @Autowired
  private BulkSelectCollectionObjectService bulkSelectCollectionObjectService;

  @Test
  public void runDemo() throws Exception
  {
    // timer to check the throughput
    TimerCounter tc = new TimerCounter();

    // calling the stored procedure
    List<BulkObject> result = bulkSelectCollectionObjectService.call(ELEMENTS);

    // print out throughput
    System.out.println(tc.perSecond("objects select bulk performance", result.size()));
  }
}