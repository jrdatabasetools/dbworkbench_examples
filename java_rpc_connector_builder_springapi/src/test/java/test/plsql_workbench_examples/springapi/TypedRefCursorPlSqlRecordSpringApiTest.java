
package test.plsql_workbench_examples.springapi;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import plsql_workbench_examples.configuration.BaseSpringConfig;
import service.TypedRefCursorPlsqlRecordService;
import transferobject.TypedRefCursorPlsqlRecordTO;

@ContextConfiguration(classes = { BaseSpringConfig.class })
@ExtendWith(SpringExtension.class)
public class TypedRefCursorPlSqlRecordSpringApiTest {
  @Autowired
  private TypedRefCursorPlsqlRecordService typedRefCursorPlsqlRecordService;

  @Test
  public void runDemo() throws Exception
  {
    // call the stored procedure and receive a list of strings
    List<String> productList = typedRefCursorPlsqlRecordService.getProduct(0);

    // print information
    System.out.println("Product list:");
    for (String product : productList) {
      System.out.format("%s%n", product);
    }

    // call the stored procedure and receive a list of transfer objects
    List<TypedRefCursorPlsqlRecordTO.RecProductVersionStatus> infoList;
    infoList = typedRefCursorPlsqlRecordService.getProductVersionStatus(0);

    // print information
    System.out.println("\nInstalled products, version and status:");
    for (TypedRefCursorPlsqlRecordTO.RecProductVersionStatus info : infoList) {
      System.out.format("product[%s]  version[%s]  status[%s]%n",
                        info.getProduct(),
                        info.getVersion(),
                        info.getStatus());
    }
  }
}
