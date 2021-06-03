
package test.plsql_workbench_examples.factoryapi;

import java.util.List;

import org.junit.jupiter.api.Test;

import factory.ExamplesRPCFactory;
import plsql_workbench_examples.timer.TimerCounter;
import service.BulkSelectCollectionObjectService;
import transferobject.BulkObject;

public class BulkSelectCollectionObjectsFactoryApiTest {
  private final static int ELEMENTS = 50000;

  @Test
  public void runDemo() throws Exception
  {
    // getting the service
    BulkSelectCollectionObjectService service = ExamplesRPCFactory.getBulkSelectCollectionObjectService();

    // timer to check the throughput
    TimerCounter tc = new TimerCounter();

    // calling the stored procedure
    List<BulkObject> result = service.call(ELEMENTS);

    // print out throughput
    System.out.println(tc.perSecond("objects select bulk performance", result.size()));
  }
}