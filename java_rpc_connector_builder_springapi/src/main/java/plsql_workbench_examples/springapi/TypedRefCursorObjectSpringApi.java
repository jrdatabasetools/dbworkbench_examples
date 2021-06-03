
package plsql_workbench_examples.springapi;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.stereotype.Component;

import service.TypedRefCursorObjectService;
import transferobject.RefcurObject;
import transferobject.TypedRefCursorObjectTO;

@Component
public class TypedRefCursorObjectSpringApi {
  @Autowired
  private TypedRefCursorObjectService typedRefCursorObjectService;

  public static void main(String[] args)
  {
    // Register Spring Beans, Spring Context and call demo method 
    try (GenericApplicationContext ctx = BaseSpringConfig.getCtx(TypedRefCursorObjectSpringApi.class)) {
      ctx.getBean(TypedRefCursorObjectSpringApi.class).runDemo();
    }
  }

  private void runDemo()
  {
    try {
      // call the stored procedure and receive a list of objects
      List<RefcurObject> list = typedRefCursorObjectService.getRefcurObject(ThreadLocalRandom.current().nextInt(5, 13),
                                                                            0);

      // print information
      for (RefcurObject item : list) {
        System.out.format("RefcurObject[n:%s, v:%s]%n", item.getN(), item.getV());
      }

      // call the stored procedure and receive a list of mixed objects
      List<TypedRefCursorObjectTO.RecRefcurObjectMixed> mixedList;
      mixedList = typedRefCursorObjectService.getRefcurObjectMixed(ThreadLocalRandom.current().nextInt(7, 17), 0);

      // print information
      for (TypedRefCursorObjectTO.RecRefcurObjectMixed item : mixedList) {
        System.out.format("ts:%s - RefcurObject[n:%s, v:%s]%n", item.getTs(), item.getO().getN(), item.getO().getV());
      }
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }
}
