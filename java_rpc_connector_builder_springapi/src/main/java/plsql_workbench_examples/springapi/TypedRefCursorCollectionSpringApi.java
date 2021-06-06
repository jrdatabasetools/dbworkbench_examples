
package plsql_workbench_examples.springapi;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.stereotype.Component;

import service.TypedRefCursorCollectionService;
import transferobject.RefcurObject;
import transferobject.TypedRefCursorCollectionTO;
import transferobject.TypedRefCursorCollectionTO.RecRefcurCollectionMixed;

@Component
public class TypedRefCursorCollectionSpringApi {
  @Autowired
  private TypedRefCursorCollectionService typedRefCursorCollectionService;

  public static void main(String[] args)
  {
    // set database credentials and configuration parameters
    System.setProperty("dbw_examples.url", "jdbc:oracle:thin:@192.168.0.102:1521/orcl");
    System.setProperty("dbw_examples.username", "dbw_examples");
    System.setProperty("dbw_examples.password", "dbw_examples");
    System.setProperty("dbw_examples.poolsize.min", Integer.toString(3));
    System.setProperty("dbw_examples.poolsize.max", Integer.toString(10));

    // Register Spring Beans, Spring Context and call demo method 
    try (GenericApplicationContext ctx = BaseSpringConfig.getCtx(TypedRefCursorCollectionSpringApi.class)) {
      ctx.getBean(TypedRefCursorCollectionSpringApi.class).runDemo();
    }
  }

  private void runDemo()
  {
    try {
      // call the stored procedure and receive a list of list as result
      List<List<RefcurObject>> listOfList;
      listOfList = typedRefCursorCollectionService.getRefcurCollection(ThreadLocalRandom.current().nextInt(2, 8), 0);

      // print information
      int row = 1;
      for (List<RefcurObject> list : listOfList) {
        System.out.println("row:" + (row++));
        for (RefcurObject obj : list) {
          System.out.format("element RefcurObject[n:%s, v:'%s']%n", obj.getN(), obj.getV());
        }
      }

      // call the stored procedure and receive a list of PL/SQL record/transfer objects containing a collection/list
      List<TypedRefCursorCollectionTO.RecRefcurCollectionMixed> mixedList;
      mixedList = typedRefCursorCollectionService.getRefcurCollectionMixed(ThreadLocalRandom.current().nextInt(2, 8),
                                                                           0);

      // print information
      for (RecRefcurCollectionMixed mixedElement : mixedList) {
        System.out.format("row element [id:%s, ts:%s]%n", mixedElement.getRowNumberId(), mixedElement.getTs());
        for (RefcurObject obj : mixedElement.getRefcurList()) {
          System.out.format("\tlist element RefcurObject[n:%s, v:'%s']%n", obj.getN(), obj.getV());
        }
      }
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }
}
