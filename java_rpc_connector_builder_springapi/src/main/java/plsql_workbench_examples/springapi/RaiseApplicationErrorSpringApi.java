
package plsql_workbench_examples.springapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.stereotype.Component;

import service.RaiseExceptionService;

@Component
public class RaiseApplicationErrorSpringApi {
  @Autowired
  private RaiseExceptionService raiseExceptionService;

  public static void main(String[] args)
  {
    // Register Spring Beans, Spring Context and call demo method 
    try (GenericApplicationContext ctx = BaseSpringConfig.getCtx(RaiseApplicationErrorSpringApi.class)) {
      ctx.getBean(RaiseApplicationErrorSpringApi.class).runDemo();
    }
  }

  private void runDemo()
  {
    try {
      // calling the stored procedure
      raiseExceptionService.call();

      System.err.println("NO - call has to throw a SQLException");
    }
    catch (Exception e) {
      System.out.println(">>> Message :\n" + e.getMessage());
      System.out.println(">>> Stacktrace :");
      e.printStackTrace(System.out);
    }
  }
}
