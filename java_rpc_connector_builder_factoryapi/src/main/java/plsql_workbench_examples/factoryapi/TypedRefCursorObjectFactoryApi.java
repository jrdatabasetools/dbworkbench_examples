
package plsql_workbench_examples.factoryapi;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import factory.ExamplesRPCFactory;
import service.TypedRefCursorObjectService;
import transferobject.RefcurObject;
import transferobject.TypedRefCursorObjectTO;

public class TypedRefCursorObjectFactoryApi {
  public static void main(String[] args)
  {
    try {
      // set database credentials and configuration parameters
      System.setProperty("dbw_examples.url", "jdbc:oracle:thin:@192.168.0.109:1521/orcl");
      System.setProperty("dbw_examples.username", "dbw_examples");
      System.setProperty("dbw_examples.password", "dbw_examples");

      // get the service
      TypedRefCursorObjectService service = ExamplesRPCFactory.getTypedRefCursorObjectService();

      // call the stored procedure and receive a list of objects
      List<RefcurObject> list;
      list = service.getRefcurObject(ThreadLocalRandom.current().nextInt(5, 13), 0);

      // print information
      for (RefcurObject item : list) {
        System.out.format("RefcurObject[n:%s, v:%s]%n", item.n, item.v);
      }

      // call the stored procedure and receive a list of mixed objects
      List<TypedRefCursorObjectTO.RecRefcurObjectMixed> mixedList;
      mixedList = service.getRefcurObjectMixed(ThreadLocalRandom.current().nextInt(7, 17), 0);

      // print information
      for (TypedRefCursorObjectTO.RecRefcurObjectMixed item : mixedList) {
        System.out.format("ts:%s - RefcurObject[n:%s, v:%s]%n", item.ts, item.o.n, item.o.v);
      }
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }
}
