
package test.plsql_workbench_examples.springapi;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import plsql_workbench_examples.configuration.BaseSpringConfig;
import service.PlsqlTableService;
import transferobject.PlsqlTableTO;

@ContextConfiguration(classes = { BaseSpringConfig.class })
@ExtendWith(SpringExtension.class)
public class PlSqlTableSpringApiTest {
  @Autowired
  private PlsqlTableService plsqlTableService;

  @Test
  public void runDemo() throws Exception
  {
    // creating some test data
    Integer[] numberTable = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
    String[] varchar2Table = { "A1", "A2", "A3", "A4", "A5", "A6", "A7", "A8", "A9", "A10", "A11", "A12" };

    // call the stored procedure
    PlsqlTableTO.DoitTO result = plsqlTableService.doit(numberTable, varchar2Table);

    // print the sum off all numbers and the length of the string-array
    System.out.println("sum:" + result.getONumberTableSum());
    System.out.println("count lines:" + result.getOVarchar2TableLength());
  }
}
