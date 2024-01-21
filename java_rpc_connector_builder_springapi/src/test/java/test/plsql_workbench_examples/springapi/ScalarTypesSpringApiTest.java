
package test.plsql_workbench_examples.springapi;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import plsql_workbench_examples.configuration.BaseSpringConfig;
import service.ScalarTypesService;

@ContextConfiguration(classes = { BaseSpringConfig.class })
@ExtendWith(SpringExtension.class)
public class ScalarTypesSpringApiTest {
  @Autowired
  private ScalarTypesService scalarTypesService;

  @Test
  public void runDemo() throws Exception
  {
    // call the stored procedure and prints the result
    int sum = scalarTypesService.addNum(1, 2);
    System.out.println("sum:" + sum);

    // call the stored procedure and prints the result
    String concat = scalarTypesService.concatChar("A", "B");
    System.out.println("concat:" + concat);
  }
}
