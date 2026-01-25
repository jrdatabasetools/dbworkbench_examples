
package test.plsql_workbench_examples.factoryapi;

import org.junit.jupiter.api.Test;

import factory.ExamplesRPCFactory;
import service.BinaryTypesService;
import transferobject.BinaryTypesTO;

public class BinaryTypesFactoryApiTest {
  @Test
  public void runDemo() throws Exception
  {
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
}
