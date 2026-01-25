
package test.plsql_workbench_examples.springapi;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import plsql_workbench_examples.configuration.BaseSpringConfig;
import service.BinaryTypesService;
import transferobject.BinaryTypesTO;

@ContextConfiguration(classes = { BaseSpringConfig.class })
@ExtendWith(SpringExtension.class)
public class BinaryTypesSpringApiTest {
  @Autowired
  private BinaryTypesService binaryTypesService;

  @Test
  public void runDemo() throws Exception
  {
    // calling the stored procedure
    BinaryTypesTO binaryTypesTO = binaryTypesService.call(1f, 1d, 1);

    // print values
    System.out.format("%.12f   %.12f   %d%n",
                      binaryTypesTO.getOBinFloat(),
                      binaryTypesTO.getOBinDouble(),
                      binaryTypesTO.getReturnValue());
  }
}
