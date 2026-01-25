
package test.plsql_workbench_examples.springapi;

import java.sql.Date;
import java.sql.Timestamp;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import plsql_workbench_examples.configuration.BaseSpringConfig;
import service.PlsqlRecordService;
import transferobject.PlsqlRecordTO;

@ContextConfiguration(classes = { BaseSpringConfig.class })
@ExtendWith(SpringExtension.class)
public class PlSqlRecordSpringApiTest {
  @Autowired
  private PlsqlRecordService plsqlRecordService;

  @Test
  public void runDemo() throws Exception
  {
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
}
