
package test.plsql_workbench_examples.factoryapi;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Test;

import factory.ExamplesRPCFactory;
import service.BulkSaveExceptionsService;
import transferobject.BulkObject;

public class BulkSaveExceptionsFactoryApiTest {
  private final static int ELEMENTS = 200;

  @Test
  public void runDemo() throws Exception
  {
    // getting the service
    BulkSaveExceptionsService service = ExamplesRPCFactory.getBulkSaveExceptionsService();

    // generating 500 elements to transfer to the stored procedure 
    List<BulkObject> objectList = new ArrayList<BulkObject>();
    for (int i = 0; i < ELEMENTS; i++) {
      BulkObject o = new BulkObject();
      o.d = new Date(System.currentTimeMillis() + (long) (Math.random() * Integer.MAX_VALUE));

      if (i % 10 == 0) {
        // produce failure : null value not allowed for column
        o.s = null;
      }
      else {
        o.s = UUID.randomUUID().toString();
      }
      o.n = (int) (Math.random() * Integer.MAX_VALUE);
      objectList.add(o);
    }

    // calling the stored procedure
    List<String> results = service.call(objectList);

    // print out the error messages
    for (String errorMsg : results) {
      System.out.println(errorMsg);
    }
  }
}
