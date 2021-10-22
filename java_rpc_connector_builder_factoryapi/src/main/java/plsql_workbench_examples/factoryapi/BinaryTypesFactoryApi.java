
package plsql_workbench_examples.factoryapi;

import factory.ExamplesRPCFactory;
import service.BinaryTypesService;
import transferobject.BinaryTypesTO;

public class BinaryTypesFactoryApi {
  public static void main(String[] args)
  {
    try {
      // set database credentials and configuration parameters
      System.setProperty("dbw_examples.url", "jdbc:oracle:thin:@192.168.0.102:1521/orcl");
      System.setProperty("dbw_examples.username", "dbw_examples");
      System.setProperty("dbw_examples.password", "dbw_examples");

      // getting the service
      BinaryTypesService service = ExamplesRPCFactory.getBinaryTypesService();

      // calling the stored procedure
      BinaryTypesTO binaryTypesTO = service.call(1f, 1d, 1);

      // print values
      System.out.format("%.12f   %.12f   %d%n",
                        binaryTypesTO.oBinFloat,
                        binaryTypesTO.oBinDouble,
                        binaryTypesTO.returnValue);
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }
}
