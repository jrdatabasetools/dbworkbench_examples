
package plsql_workbench_examples.factoryapi;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import factory.ExamplesRPCFactory;
import service.CollectionsOfObjectsService;
import transferobject.ColObject;

public class CollectionsOfObjectsFactoryApi {
  public static void main(String[] args)
  {
    try {
      // set database credentials and configuration parameters
      System.setProperty("dbw_examples.url", "jdbc:oracle:thin:@192.168.0.109:1521/orcl");
      System.setProperty("dbw_examples.username", "dbw_examples");
      System.setProperty("dbw_examples.password", "dbw_examples");

      // get the service
      CollectionsOfObjectsService service = ExamplesRPCFactory.getCollectionsOfObjectsService();

      // create some test data
      List<ColObject> objectList = new ArrayList<>();
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
    catch (Exception e) {
      e.printStackTrace();
    }
  }
}
