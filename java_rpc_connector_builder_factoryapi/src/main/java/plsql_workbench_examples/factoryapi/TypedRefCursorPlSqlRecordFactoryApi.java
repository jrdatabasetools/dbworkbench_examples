
package plsql_workbench_examples.factoryapi;

import java.util.List;

import factory.ExamplesRPCFactory;
import service.TypedRefCursorPlsqlRecordService;
import transferobject.TypedRefCursorPlsqlRecordTO;

public class TypedRefCursorPlSqlRecordFactoryApi {
  public static void main(String[] args)
  {
    try {
      // set database credentials and configuration parameters
      System.setProperty("dbw_examples.url", "jdbc:oracle:thin:@192.168.0.109:1521/orcl");
      System.setProperty("dbw_examples.username", "dbw_examples");
      System.setProperty("dbw_examples.password", "dbw_examples");

      // get the service
      TypedRefCursorPlsqlRecordService service = ExamplesRPCFactory.getTypedRefCursorPlsqlRecordService();

      // call the stored procedure and receive a list of strings
      List<String> productList = service.getProduct(0);

      // print information
      System.out.println("Product list:");
      for (String product : productList) {
        System.out.format("%s%n", product);
      }

      // call the stored procedure and receive a list of transfer objects
      List<TypedRefCursorPlsqlRecordTO.RecProductVersionStatus> infoList = service.getProductVersionStatus(0);

      // print information
      System.out.println("\nInstalled products, version and status:");
      for (TypedRefCursorPlsqlRecordTO.RecProductVersionStatus info : infoList) {
        System.out.format("product[%s]  version[%s]  status[%s]%n", info.product, info.version, info.status);
      }
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }
}
