
package plsql_workbench_examples.factoryapi;

import factory.ExamplesRPCFactory;
import service.ScalarTypesService;

public class ScalarTypesFactoryApi {
  public static void main(String[] args)
  {
    try {
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
