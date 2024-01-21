
package test.plsql_workbench_examples.springapi;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import plsql_workbench_examples.configuration.BaseSpringConfig;
import service.CollectionsOfObjectsService;
import transferobject.ColObject;

@ContextConfiguration(classes = { BaseSpringConfig.class })
@ExtendWith(SpringExtension.class)
public class CollectionsOfObjectsSpringApiTest {
  @Autowired
  private CollectionsOfObjectsService collectionsOfObjectsService;

  @Test
  public void runDemo() throws Exception
  {
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
}
