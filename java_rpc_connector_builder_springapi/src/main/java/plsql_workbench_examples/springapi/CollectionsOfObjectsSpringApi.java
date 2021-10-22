
package plsql_workbench_examples.springapi;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.stereotype.Component;

import service.CollectionsOfObjectsService;
import transferobject.ColObject;

@Component
public class CollectionsOfObjectsSpringApi {
  @Autowired
  private CollectionsOfObjectsService collectionsOfObjectsService;

  public static void main(String[] args)
  {
    // set database credentials and configuration parameters
    System.setProperty("dbw_examples.url", "jdbc:oracle:thin:@192.168.0.102:1521/orcl");
    System.setProperty("dbw_examples.username", "dbw_examples");
    System.setProperty("dbw_examples.password", "dbw_examples");

    // Register Spring Beans, Spring Context and call demo method 
    try (GenericApplicationContext ctx = BaseSpringConfig.getCtx(CollectionsOfObjectsSpringApi.class)) {
      ctx.getBean(CollectionsOfObjectsSpringApi.class).runDemo();
    }
  }

  private void runDemo()
  {
    try {
      // create some test data
      List<ColObject> objectList = new ArrayList<ColObject>();
      for (int i = 0; i < 3; i++) {
        ColObject o = new ColObject();
        o.setD(new Date(System.currentTimeMillis()));
        o.setS("init value");
        o.setTs(new Timestamp(System.currentTimeMillis()));
        objectList.add(o);
      }

      // call the service and receive result list of transfer objects
      List<ColObject> resultList = collectionsOfObjectsService.call(3, 7, objectList);

      // printing the result list
      for (ColObject o : resultList) {
        System.out.format("d[%s] ts[%s] s[%s]%n", o.getD(), o.getTs(), o.getS());
      }
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }
}
