
package plsql_workbench_examples.factoryapi;

import factory.ExamplesRPCFactory;
import service.RaiseExceptionService;

public class RaiseApplicationErrorFactoryApi {
  public static void main(String[] args)
  {
    try {
      // getting the service
      RaiseExceptionService service = ExamplesRPCFactory.getRaiseExceptionService();

      // calling the stored procedure
      service.call();

      System.err.println("NO - call has to throw a SQLException");
    }
    catch (Exception e) {
      System.out.println(">>> Message :\n" + e.getMessage());
      System.out.println(">>> Stacktrace :");
      e.printStackTrace(System.out);
    }
  }
}
