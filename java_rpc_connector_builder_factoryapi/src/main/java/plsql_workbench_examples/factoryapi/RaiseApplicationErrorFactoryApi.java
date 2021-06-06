
package plsql_workbench_examples.factoryapi;

import factory.ExamplesRPCFactory;
import service.RaiseExceptionService;

public class RaiseApplicationErrorFactoryApi {
  public static void main(String[] args)
  {
    try {
      // set database credentials and configuration parameters
      System.setProperty("dbw_examples.url", "jdbc:oracle:thin:@192.168.0.102:1521/orcl");
      System.setProperty("dbw_examples.username", "dbw_examples");
      System.setProperty("dbw_examples.password", "dbw_examples");
      System.setProperty("dbw_examples.poolsize.min", Integer.toString(3));
      System.setProperty("dbw_examples.poolsize.max", Integer.toString(10));

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
