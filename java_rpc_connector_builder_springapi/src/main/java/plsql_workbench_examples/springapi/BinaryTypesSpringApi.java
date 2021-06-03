
package plsql_workbench_examples.springapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.stereotype.Component;

import service.BinaryTypesService;
import transferobject.BinaryTypesTO;

@Component
public class BinaryTypesSpringApi {
  @Autowired
  private BinaryTypesService binaryTypesService;

  public static void main(String[] args)
  {
    // Register Spring Beans, Spring Context and call demo method 
    try (GenericApplicationContext ctx = BaseSpringConfig.getCtx(BinaryTypesSpringApi.class)) {
      ctx.getBean(BinaryTypesSpringApi.class).runDemo();
    }
  }

  private void runDemo()
  {
    try {
      // calling the stored procedure
      BinaryTypesTO binaryTypesTO = binaryTypesService.call(1f, 1d, 1);

      // print values
      System.out.format("%.12f   %.12f   %d%n",
                        binaryTypesTO.getOBinFloat(),
                        binaryTypesTO.getOBinDouble(),
                        binaryTypesTO.getReturnValue());
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }
}
