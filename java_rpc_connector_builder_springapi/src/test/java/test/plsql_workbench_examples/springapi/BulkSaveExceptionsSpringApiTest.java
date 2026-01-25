
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
import service.BulkSaveExceptionsService;
import transferobject.BulkObject;

@ContextConfiguration(classes = { BaseSpringConfig.class })
@ExtendWith(SpringExtension.class)
public class BulkSaveExceptionsSpringApiTest {
  private final static int          ELEMENTS = 200;

  @Autowired
  private BulkSaveExceptionsService bulkSaveExceptionsService;

  @Test
  public void runDemo() throws Exception
  {
    // getting the service
    // generating 500 elements to transfer to the stored procedure 
    List<BulkObject> objectList = new ArrayList<BulkObject>();
    for (int i = 0; i < ELEMENTS; i++) {
      BulkObject o = new BulkObject();
      o.setD(new Date(System.currentTimeMillis() + (long) (Math.random() * Integer.MAX_VALUE)));

      if (i % 10 == 0) {
        // produce failure : null value not allowed for column
        o.setS(null);
      }
      else {
        o.setS(UUID.randomUUID().toString());
      }
      o.setN((int) (Math.random() * Integer.MAX_VALUE));
      objectList.add(o);
    }

    // calling the stored procedure
    List<String> results = bulkSaveExceptionsService.call(objectList);

    // print out the error messages
    for (String errorMsg : results) {
      System.out.println(errorMsg);
    }
  }
}
