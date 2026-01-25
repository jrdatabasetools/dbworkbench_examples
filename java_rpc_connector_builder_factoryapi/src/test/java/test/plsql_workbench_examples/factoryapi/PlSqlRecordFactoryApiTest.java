
package test.plsql_workbench_examples.factoryapi;

import java.sql.Date;
import java.sql.Timestamp;

import org.junit.jupiter.api.Test;

import factory.ExamplesRPCFactory;
import service.PlsqlRecordService;
import transferobject.PlsqlRecordTO;

public class PlSqlRecordFactoryApiTest {
  @Test
  public void runDemo() throws Exception
  {
    // get the service
    PlsqlRecordService service = ExamplesRPCFactory.getPlsqlRecordService();

    // creating some test data
    PlsqlRecordTO.DemoRecord record = new PlsqlRecordTO.DemoRecord();
    record.d = new Date(System.currentTimeMillis());
    record.f = 3.14;
    record.n = 4711;
    record.s = "some kind of string";
    record.ts = new Timestamp(System.currentTimeMillis());

    // call the stored procedure
    record = service.modifyRecord(record);

    // print record field values
    System.out.format("d:%1$tD   f:%2$f   n:%3$d   s:%4$s   ts:%5$tD %5$tT",
                      record.d,
                      record.f,
                      record.n,
                      record.s,
                      record.ts);
  }
}
