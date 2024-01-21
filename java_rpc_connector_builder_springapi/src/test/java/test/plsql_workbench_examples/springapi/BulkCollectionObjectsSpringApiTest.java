
package test.plsql_workbench_examples.springapi;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import plsql_workbench_examples.configuration.BaseSpringConfig;
import plsql_workbench_examples.timer.TimerCounter;
import service.BulkCollectionObjectService;
import transferobject.BulkObject;

@ContextConfiguration(classes = { BaseSpringConfig.class })
@ExtendWith(SpringExtension.class)
public class BulkCollectionObjectsSpringApiTest {
  private final static int            ELEMENTS = 50000;

  @Autowired
  private BulkCollectionObjectService bulkCollectionObjectService;

  @Test
  public void runDemo() throws Exception
  {
    // generating 50000 elements to transfer to the stored procedure 
    List<BulkObject> objectList = new ArrayList<BulkObject>();
    for (int i = 0; i < ELEMENTS; i++) {
      BulkObject o = new BulkObject();
      o.setD(new Date(System.currentTimeMillis() + (long) (Math.random() * Integer.MAX_VALUE)));
      o.setS(UUID.randomUUID().toString());
      o.setN((int) (Math.random() * Integer.MAX_VALUE));
      objectList.add(o);
    }

    // timer to check the throughput
    TimerCounter tc = new TimerCounter();

    // calling the stored procedure
    bulkCollectionObjectService.call(objectList);

    // print out throughput
    System.out.println(tc.perSecond("objects bulk performance (first call)", ELEMENTS));

    // calling the stored procedure
    bulkCollectionObjectService.call(objectList);

    // print out throughput
    System.out.println(tc.perSecond("objects bulk performance (second call)", ELEMENTS));
  }
}
