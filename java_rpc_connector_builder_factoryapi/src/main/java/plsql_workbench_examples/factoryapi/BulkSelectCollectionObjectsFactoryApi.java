
package plsql_workbench_examples.factoryapi;

import java.util.List;

import factory.ExamplesRPCFactory;
import plsql_workbench_examples.timer.TimerCounter;
import service.BulkSelectCollectionObjectService;
import transferobject.BulkObject;

public class BulkSelectCollectionObjectsFactoryApi {
  private final static int ELEMENTS = 50000;

  public static void main(String[] args)
  {
    try {
      // set database credentials and configuration parameters
      System.setProperty("dbw_examples.url", "jdbc:oracle:thin:@192.168.0.109:1521/orcl");
      System.setProperty("dbw_examples.username", "dbw_examples");
      System.setProperty("dbw_examples.password", "dbw_examples");

      // getting the service
      BulkSelectCollectionObjectService service = ExamplesRPCFactory.getBulkSelectCollectionObjectService();

      // timer to check the throughput
      TimerCounter tc = new TimerCounter();

      // calling the stored procedure
      List<BulkObject> result = service.call(ELEMENTS);

      // print out throughput
      System.out.println(tc.perSecond("objects fetch object bulk performance", result.size()));
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }
}