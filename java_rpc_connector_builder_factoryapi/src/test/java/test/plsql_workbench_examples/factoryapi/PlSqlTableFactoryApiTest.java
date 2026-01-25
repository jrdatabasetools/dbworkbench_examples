
package test.plsql_workbench_examples.factoryapi;

import org.junit.jupiter.api.Test;

import factory.ExamplesRPCFactory;
import service.PlsqlTableService;
import transferobject.PlsqlTableTO;

public class PlSqlTableFactoryApiTest {
  @Test
  public void runDemo() throws Exception
  {
    // get the service
    PlsqlTableService service = ExamplesRPCFactory.getPlsqlTableService();

    // creating some test data
    Integer[] numberTable = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
    String[] varchar2Table = { "A1", "A2", "A3", "A4", "A5", "A6", "A7", "A8", "A9", "A10", "A11", "A12" };

    // call the stored procedure
    PlsqlTableTO.DoitTO result = service.doit(numberTable, varchar2Table);

    // print the sum off all numbers and the length of the string-array
    System.out.println("sum:" + result.oNumberTableSum);
    System.out.println("count lines:" + result.oVarchar2TableLength);
  }
}
