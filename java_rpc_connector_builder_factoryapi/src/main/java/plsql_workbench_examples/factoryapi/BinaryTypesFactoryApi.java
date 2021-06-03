
package plsql_workbench_examples.factoryapi;

import factory.ExamplesRPCFactory;
import service.BinaryTypesService;
import transferobject.BinaryTypesTO;

public class BinaryTypesFactoryApi {
  public static void main(String[] args)
  {
    try {
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
