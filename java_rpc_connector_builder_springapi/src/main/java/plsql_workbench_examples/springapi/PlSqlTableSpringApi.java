
package plsql_workbench_examples.springapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.stereotype.Component;

import service.PlsqlTableService;
import transferobject.PlsqlTableTO;

@Component
public class PlSqlTableSpringApi {
  @Autowired
  private PlsqlTableService plsqlTableService;

  public static void main(String[] args)
  {
    // set database credentials and configuration parameters
    System.setProperty("dbw_examples.url", "jdbc:oracle:thin:@192.168.0.102:1521/orcl");
    System.setProperty("dbw_examples.username", "dbw_examples");
    System.setProperty("dbw_examples.password", "dbw_examples");
    System.setProperty("dbw_examples.poolsize.min", Integer.toString(3));
    System.setProperty("dbw_examples.poolsize.max", Integer.toString(10));

    // Register Spring Beans, Spring Context and call demo method 
    try (GenericApplicationContext ctx = BaseSpringConfig.getCtx(PlSqlTableSpringApi.class)) {
      ctx.getBean(PlSqlTableSpringApi.class).runDemo();
    }
  }

  private void runDemo()
  {
    try {
      // creating some test data
      Integer[] numberTable = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
      String[] varchar2Table = { "A1", "A2", "A3", "A4", "A5", "A6", "A7", "A8", "A9", "A10", "A11", "A12" };

      // call the stored procedure
      PlsqlTableTO.DoitTO result = plsqlTableService.doit(numberTable, varchar2Table);

      // print the sum off all numbers and the length of the string-array
      System.out.println("sum:" + result.getONumberTableSum());
      System.out.println("count lines:" + result.getOVarchar2TableLength());
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }
}
