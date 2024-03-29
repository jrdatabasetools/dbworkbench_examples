
package plsql_workbench_examples.factoryapi;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import factory.ExamplesRPCFactory;
import service.CollectionOfPlsqlRecordsService;
import transferobject.CollectionOfPlsqlRecordsTO;
import transferobject.CollectionOfPlsqlRecordsTO.PlsqlRecord;

public class CollectionsOfPlSqlRecordsFactoryApi {
  public static void main(String[] args)
  {
    try {
      // set database credentials and configuration parameters
      System.setProperty("dbw_examples.url", "jdbc:oracle:thin:@192.168.0.109:1521/orcl");
      System.setProperty("dbw_examples.username", "dbw_examples");
      System.setProperty("dbw_examples.password", "dbw_examples");

      // get the service
      CollectionOfPlsqlRecordsService service = ExamplesRPCFactory.getCollectionOfPlsqlRecordsService();

      // create some test data
      List<CollectionOfPlsqlRecordsTO.PlsqlRecord> list = new ArrayList<>();
      for (int i = 0; i < 3; i++) {
        CollectionOfPlsqlRecordsTO.PlsqlRecord o = new CollectionOfPlsqlRecordsTO.PlsqlRecord();
        o.d = new Date(System.currentTimeMillis());
        o.s = "init value";
        o.ts = new Timestamp(System.currentTimeMillis());
        list.add(o);
      }

      // call the service and receive result list of transfer objects
      List<CollectionOfPlsqlRecordsTO.PlsqlRecord> resultList = service.doit(3, 7, list);

      // printing the result list
      for (PlsqlRecord record : resultList) {
        System.out.format("d[%s] ts[%s] s[%s]%n", record.d, record.ts, record.s);
      }
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }
}
