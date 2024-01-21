
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
import service.CollectionOfPlsqlRecordsService;
import transferobject.CollectionOfPlsqlRecordsTO;
import transferobject.CollectionOfPlsqlRecordsTO.PlsqlRecord;

@ContextConfiguration(classes = { BaseSpringConfig.class })
@ExtendWith(SpringExtension.class)
public class CollectionsOfPlSqlRecordsSpringApiTest {
  @Autowired
  private CollectionOfPlsqlRecordsService collectionOfPlsqlRecordsService;

  @Test
  public void runDemo() throws Exception
  {
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
}
