
package test.plsql_workbench_examples.springapi;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import plsql_workbench_examples.configuration.BaseSpringConfig;
import service.TypedRefCursorObjectService;
import transferobject.RefcurObject;
import transferobject.TypedRefCursorObjectTO;

@ContextConfiguration(classes = { BaseSpringConfig.class })
@ExtendWith(SpringExtension.class)
public class TypedRefCursorObjectSpringApiTest {
  @Autowired
  private TypedRefCursorObjectService typedRefCursorObjectService;

  @Test
  public void runDemo() throws Exception
  {
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
}
