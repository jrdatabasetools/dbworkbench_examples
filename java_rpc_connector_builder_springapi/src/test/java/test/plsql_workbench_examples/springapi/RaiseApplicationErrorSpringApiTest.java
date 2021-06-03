
package test.plsql_workbench_examples.springapi;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import plsql_workbench_examples.springapi.BaseSpringConfig;
import service.RaiseExceptionService;

@ContextConfiguration(classes = { BaseSpringConfig.class })
@ExtendWith(SpringExtension.class)
public class RaiseApplicationErrorSpringApiTest {
  @Autowired
  private RaiseExceptionService raiseExceptionService;

  @Test
  public void runDemo() throws Exception
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
