
package test.plsql_workbench_examples.factoryapi;

import org.junit.jupiter.api.Test;

import factory.ExamplesRPCFactory;
import service.RaiseExceptionService;

public class RaiseApplicationErrorFactoryApiTest {
  @Test
  public void runDemo() throws Exception
  {
    try {
      // getting the service
      RaiseExceptionService service = ExamplesRPCFactory.getRaiseExceptionService();

      // calling the stored procedure
      service.call();

      System.err.println("NO - call has to throw a SQLException");
    }
    catch (Exception ex) {
      System.out.println(">>> Message :\n" + ex.getMessage());
      System.out.println(">>> Stacktrace :");
      ex.printStackTrace(System.out);
    }
  }
}
