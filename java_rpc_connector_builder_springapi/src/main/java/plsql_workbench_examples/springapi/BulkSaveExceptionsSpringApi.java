
package plsql_workbench_examples.springapi;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.stereotype.Component;

import service.BulkSaveExceptionsService;
import transferobject.BulkObject;

@Component
public class BulkSaveExceptionsSpringApi {
  private final static int          ELEMENTS = 200;

  @Autowired
  private BulkSaveExceptionsService bulkSaveExceptionsService;

  public static void main(String[] args)
  {
    // set database credentials and configuration parameters
    System.setProperty("dbw_examples.url", "jdbc:oracle:thin:@192.168.0.102:1521/orcl");
    System.setProperty("dbw_examples.username", "dbw_examples");
    System.setProperty("dbw_examples.password", "dbw_examples");

    // Register Spring Beans, Spring Context and call demo method 
    try (GenericApplicationContext ctx = BaseSpringConfig.getCtx(BulkSaveExceptionsSpringApi.class)) {
      ctx.getBean(BulkSaveExceptionsSpringApi.class).runDemo();
    }
  }

  private void runDemo()
  {
    try {
      // getting the service
      // generating 500 elements to transfer to the stored procedure 
      List<BulkObject> objectList = new ArrayList<>();
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
    catch (Exception e) {
      e.printStackTrace();
    }
  }
}
