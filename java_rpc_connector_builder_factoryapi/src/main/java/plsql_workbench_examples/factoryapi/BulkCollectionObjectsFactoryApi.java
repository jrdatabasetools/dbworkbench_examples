
package plsql_workbench_examples.factoryapi;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import factory.ExamplesRPCFactory;
import plsql_workbench_examples.timer.TimerCounter;
import service.BulkCollectionObjectService;
import transferobject.BulkObject;

public class BulkCollectionObjectsFactoryApi {
  private final static int ELEMENTS = 50000;

  public static void main(String[] args)
  {
    try {
      // getting the service
      BulkCollectionObjectService service = ExamplesRPCFactory.getBulkCollectionObjectService();

      // generating 50000 elements to transfer to the stored procedure 
      List<BulkObject> objectList = new ArrayList<>();
      for (int i = 0; i < ELEMENTS; i++) {
        BulkObject o = new BulkObject();
        o.d = new Date(System.currentTimeMillis() + (long) (Math.random() * Integer.MAX_VALUE));
        o.s = UUID.randomUUID().toString();
        o.n = (int) (Math.random() * Integer.MAX_VALUE);
        objectList.add(o);
      }

      // timer to check the throughput
      TimerCounter tc = new TimerCounter();

      // calling the stored procedure
      service.call(objectList);

      // print out throughput
      System.out.println(tc.perSecond("objects bulk performance", ELEMENTS));
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }
}
