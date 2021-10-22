
package plsql_workbench_examples.springapi;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.stereotype.Component;

import service.TypedRefCursorPlsqlRecordService;
import transferobject.TypedRefCursorPlsqlRecordTO;

@Component
public class TypedRefCursorPlSqlRecordSpringApi {
  @Autowired
  private TypedRefCursorPlsqlRecordService typedRefCursorPlsqlRecordService;

  public static void main(String[] args)
  {
    // set database credentials and configuration parameters
    System.setProperty("dbw_examples.url", "jdbc:oracle:thin:@192.168.0.102:1521/orcl");
    System.setProperty("dbw_examples.username", "dbw_examples");
    System.setProperty("dbw_examples.password", "dbw_examples");

    // Register Spring Beans, Spring Context and call demo method 
    try (GenericApplicationContext ctx = BaseSpringConfig.getCtx(TypedRefCursorPlSqlRecordSpringApi.class)) {
      ctx.getBean(TypedRefCursorPlSqlRecordSpringApi.class).runDemo();
    }
  }

  private void runDemo()
  {
    try {
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
    catch (Exception e) {
      e.printStackTrace();
    }
  }
}
