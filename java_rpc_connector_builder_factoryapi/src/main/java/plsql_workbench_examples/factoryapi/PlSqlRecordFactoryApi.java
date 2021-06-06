
package plsql_workbench_examples.factoryapi;

import java.sql.Date;
import java.sql.Timestamp;

import factory.ExamplesRPCFactory;
import service.PlsqlRecordService;
import transferobject.PlsqlRecordTO;

public class PlSqlRecordFactoryApi {
  public static void main(String[] args)
  {
    try {
      // set database credentials and configuration parameters
      System.setProperty("dbw_examples.url", "jdbc:oracle:thin:@192.168.0.102:1521/orcl");
      System.setProperty("dbw_examples.username", "dbw_examples");
      System.setProperty("dbw_examples.password", "dbw_examples");
      System.setProperty("dbw_examples.poolsize.min", Integer.toString(3));
      System.setProperty("dbw_examples.poolsize.max", Integer.toString(10));

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
    catch (Exception e) {
      e.printStackTrace();
    }
  }
}
