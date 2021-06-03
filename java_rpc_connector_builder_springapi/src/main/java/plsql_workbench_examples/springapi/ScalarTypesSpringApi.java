
package plsql_workbench_examples.springapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.stereotype.Component;

import service.ScalarTypesService;

@Component
public class ScalarTypesSpringApi {
  @Autowired
  private ScalarTypesService scalarTypesService;

  public static void main(String[] args)
  {
    // Register Spring Beans, Spring Context and call demo method 
    try (GenericApplicationContext ctx = BaseSpringConfig.getCtx(ScalarTypesSpringApi.class)) {
      ctx.getBean(ScalarTypesSpringApi.class).runDemo();
    }
  }

  private void runDemo()
  {
    try {
      // call the stored procedure and prints the result
      int sum = scalarTypesService.addNum(1, 2);
      System.out.println("sum:" + sum);

      // call the stored procedure and prints the result
      String concat = scalarTypesService.concatChar("A", "B");
      System.out.println("concat:" + concat);
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }
}
