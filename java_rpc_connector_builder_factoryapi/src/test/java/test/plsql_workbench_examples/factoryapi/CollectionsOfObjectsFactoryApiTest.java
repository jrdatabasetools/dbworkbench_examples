
package test.plsql_workbench_examples.factoryapi;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import factory.ExamplesRPCFactory;
import service.CollectionsOfObjectsService;
import transferobject.ColObject;

public class CollectionsOfObjectsFactoryApiTest {
  @Test
  public void runDemo() throws Exception
  {
    // get the service
    CollectionsOfObjectsService service = ExamplesRPCFactory.getCollectionsOfObjectsService();

    // create some test data
    List<ColObject> objectList = new ArrayList<ColObject>();
    for (int i = 0; i < 3; i++) {
      ColObject o = new ColObject();
      o.d = new Date(System.currentTimeMillis());
      o.s = "init value";
      o.ts = new Timestamp(System.currentTimeMillis());
      objectList.add(o);
    }

    // call the service and receive result list of transfer objects
    List<ColObject> resultList = service.call(3, 7, objectList);

    // printing the result list
    for (ColObject o : resultList) {
      System.out.format("d[%s] ts[%s] s[%s]%n", o.d, o.ts, o.s);
    }
  }
}
