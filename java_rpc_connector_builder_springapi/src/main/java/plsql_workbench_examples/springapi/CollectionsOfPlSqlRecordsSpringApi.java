
package plsql_workbench_examples.springapi;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.stereotype.Component;

import service.CollectionOfPlsqlRecordsService;
import transferobject.CollectionOfPlsqlRecordsTO;
import transferobject.CollectionOfPlsqlRecordsTO.PlsqlRecord;

@Component
public class CollectionsOfPlSqlRecordsSpringApi {
  @Autowired
  private CollectionOfPlsqlRecordsService collectionOfPlsqlRecordsService;

  public static void main(String[] args)
  {
    // set database credentials and configuration parameters
    System.setProperty("dbw_examples.url", "jdbc:oracle:thin:@192.168.0.102:1521/orcl");
    System.setProperty("dbw_examples.username", "dbw_examples");
    System.setProperty("dbw_examples.password", "dbw_examples");
    System.setProperty("dbw_examples.poolsize.min", Integer.toString(3));
    System.setProperty("dbw_examples.poolsize.max", Integer.toString(10));

    // Register Spring Beans, Spring Context and call demo method 
    try (GenericApplicationContext ctx = BaseSpringConfig.getCtx(CollectionsOfPlSqlRecordsSpringApi.class)) {
      ctx.getBean(CollectionsOfPlSqlRecordsSpringApi.class).runDemo();
    }
  }

  public void runDemo()
  {
    try {
      // create some test data
      List<CollectionOfPlsqlRecordsTO.PlsqlRecord> list = new ArrayList<>();
      for (int i = 0; i < 3; i++) {
        CollectionOfPlsqlRecordsTO.PlsqlRecord o = new CollectionOfPlsqlRecordsTO.PlsqlRecord();
        o.setD(new Date(System.currentTimeMillis()));
        o.setS("init value");
        o.setTs(new Timestamp(System.currentTimeMillis()));
        list.add(o);
      }

      // call the service and receive result list of transfer objects
      List<CollectionOfPlsqlRecordsTO.PlsqlRecord> resultList = collectionOfPlsqlRecordsService.doit(3, 7, list);

      // printing the result list
      for (PlsqlRecord record : resultList) {
        System.out.format("d[%s] ts[%s] s[%s]%n", record.getD(), record.getTs(), record.getS());
      }
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }
}
