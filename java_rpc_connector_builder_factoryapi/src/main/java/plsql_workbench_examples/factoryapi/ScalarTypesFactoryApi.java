
package plsql_workbench_examples.factoryapi;

import factory.ExamplesRPCFactory;
import service.ScalarTypesService;

public class ScalarTypesFactoryApi {
  public static void main(String[] args)
  {
    try {
      // set database credentials and configuration parameters
      System.setProperty("dbw_examples.url", "jdbc:oracle:thin:@192.168.0.109:1521/orcl");
      System.setProperty("dbw_examples.username", "dbw_examples");
      System.setProperty("dbw_examples.password", "dbw_examples");

      // get the service
      ScalarTypesService service = ExamplesRPCFactory.getScalarTypesService();

      // call the stored procedure and prints the result
      int sum = service.addNum(1, 2);
      System.out.println("sum:" + sum);

      // call the stored procedure and prints the result
      String concat = service.concatChar("A", "B");
      System.out.println("concat:" + concat);
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }
}
