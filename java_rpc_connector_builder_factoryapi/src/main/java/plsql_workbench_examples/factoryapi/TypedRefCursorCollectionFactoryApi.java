
package plsql_workbench_examples.factoryapi;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import factory.ExamplesRPCFactory;
import service.TypedRefCursorCollectionService;
import transferobject.RefcurObject;
import transferobject.TypedRefCursorCollectionTO;
import transferobject.TypedRefCursorCollectionTO.RecRefcurCollectionMixed;

public class TypedRefCursorCollectionFactoryApi {
  public static void main(String[] args)
  {
    try {
      // set database credentials and configuration parameters
      System.setProperty("dbw_examples.url", "jdbc:oracle:thin:@192.168.0.109:1521/orcl");
      System.setProperty("dbw_examples.username", "dbw_examples");
      System.setProperty("dbw_examples.password", "dbw_examples");

      // get the service
      TypedRefCursorCollectionService service = ExamplesRPCFactory.getTypedRefCursorCollectionService();

      // call the stored procedure and receive a list of list as result
      List<List<RefcurObject>> listOfList;
      listOfList = service.getRefcurCollection(ThreadLocalRandom.current().nextInt(2, 8), 0);

      // print information
      int row = 1;
      for (List<RefcurObject> list : listOfList) {
        System.out.println("row:" + (row++));
        for (RefcurObject obj : list) {
          System.out.format("element RefcurObject[n:%s, v:'%s']%n", obj.n, obj.v);
        }
      }

      // call the stored procedure and receive a list of PL/SQL record/transfer objects containing a collection/list
      List<TypedRefCursorCollectionTO.RecRefcurCollectionMixed> mixedList;
      mixedList = service.getRefcurCollectionMixed(ThreadLocalRandom.current().nextInt(2, 8), 0);

      // print information
      for (RecRefcurCollectionMixed mixedElement : mixedList) {
        System.out.format("row element [id:%s, ts:%s]%n", mixedElement.rowNumberId, mixedElement.ts);
        for (RefcurObject obj : mixedElement.refcurList) {
          System.out.format("\tlist element RefcurObject[n:%s, v:'%s']%n", obj.n, obj.v);
        }
      }
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }
}
