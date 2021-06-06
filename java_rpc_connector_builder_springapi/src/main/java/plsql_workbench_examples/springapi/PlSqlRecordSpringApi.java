
package plsql_workbench_examples.springapi;

import java.sql.Date;
import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.stereotype.Component;

import service.PlsqlRecordService;
import transferobject.PlsqlRecordTO;

@Component
public class PlSqlRecordSpringApi {
  @Autowired
  private PlsqlRecordService plsqlRecordService;

  public static void main(String[] args)
  {
    // set database credentials and configuration parameters
    System.setProperty("dbw_examples.url", "jdbc:oracle:thin:@192.168.0.102:1521/orcl");
    System.setProperty("dbw_examples.username", "dbw_examples");
    System.setProperty("dbw_examples.password", "dbw_examples");
    System.setProperty("dbw_examples.poolsize.min", Integer.toString(3));
    System.setProperty("dbw_examples.poolsize.max", Integer.toString(10));

    // Register Spring Beans, Spring Context and call demo method 
    try (GenericApplicationContext ctx = BaseSpringConfig.getCtx(PlSqlRecordSpringApi.class)) {
      ctx.getBean(PlSqlRecordSpringApi.class).runDemo();
    }
  }

  private void runDemo()
  {
    try {
      // creating some test data
      PlsqlRecordTO.DemoRecord record = new PlsqlRecordTO.DemoRecord();
      record.setD(new Date(System.currentTimeMillis()));
      record.setF(3.14);
      record.setN(4711);
      record.setS("some kind of string");
      record.setTs(new Timestamp(System.currentTimeMillis()));

      // call the stored procedure
      record = plsqlRecordService.modifyRecord(record);

      // print record field values
      System.out.format("d:%1$tD   f:%2$f   n:%3$d   s:%4$s   ts:%5$tD %5$tT",
                        record.getD(),
                        record.getF(),
                        record.getN(),
                        record.getS(),
                        record.getTs());
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }
}
