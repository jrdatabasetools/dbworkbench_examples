
package test.plsql_workbench_examples.springapi;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import plsql_workbench_examples.configuration.BaseSpringConfig;
import service.TypedRefCursorCollectionService;
import transferobject.RefcurObject;
import transferobject.TypedRefCursorCollectionTO;
import transferobject.TypedRefCursorCollectionTO.RecRefcurCollectionMixed;

@ContextConfiguration(classes = { BaseSpringConfig.class })
@ExtendWith(SpringExtension.class)
public class TypedRefCursorCollectionSpringApiTest {
  @Autowired
  private TypedRefCursorCollectionService typedRefCursorCollectionService;

  @Test
  public void runDemo() throws Exception
  {
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
    mixedList = typedRefCursorCollectionService.getRefcurCollectionMixed(ThreadLocalRandom.current().nextInt(2, 8), 0);

    // print information
    for (RecRefcurCollectionMixed mixedElement : mixedList) {
      System.out.format("row element [id:%s, ts:%s]%n", mixedElement.getRowNumberId(), mixedElement.getTs());
      for (RefcurObject obj : mixedElement.getRefcurList()) {
        System.out.format("\tlist element RefcurObject[n:%s, v:'%s']%n", obj.getN(), obj.getV());
      }
    }
  }
}
