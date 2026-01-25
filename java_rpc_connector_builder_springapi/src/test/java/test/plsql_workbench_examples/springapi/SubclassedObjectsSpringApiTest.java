
package test.plsql_workbench_examples.springapi;

import java.sql.Date;
import java.sql.Timestamp;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import plsql_workbench_examples.configuration.BaseSpringConfig;
import service.SubclassedObjectsService;
import transferobject.BaseObject;
import transferobject.DerivedObject;

@ContextConfiguration(classes = { BaseSpringConfig.class })
@ExtendWith(SpringExtension.class)
public class SubclassedObjectsSpringApiTest {
  @Autowired
  private SubclassedObjectsService subclassedObjectsService;

  @Test
  public void runDemo() throws Exception
  {
    int diff = 10;

    // create a base object from the database function
    BaseObject baseObject = subclassedObjectsService.getBaseObject();

    System.out.format("java-inspect: %s [d:%s/ts:%s/instance:%s/version:%d.%d]%n",
                      baseObject.getClass(),
                      baseObject.getD(),
                      baseObject.getTs(),
                      baseObject.getInstance(),
                      baseObject.getDbVersion(),
                      baseObject.getDbRelease());

    System.out.println("pl/sql inspect : " + subclassedObjectsService.inspectObject(baseObject));

    // create an derived object from the database function
    baseObject = subclassedObjectsService.getDerivedObject(diff);
    DerivedObject derivedObject = (DerivedObject) baseObject;
    System.out.format("java-inspect : %s [d:%s/ts:%s/instance:%s/version:%d.%d/newS:%s/newTs:%s/newD:%s/newN:%d]%n",
                      derivedObject.getClass(),
                      derivedObject.getD(),
                      derivedObject.getTs(),
                      derivedObject.getInstance(),
                      derivedObject.getDbVersion(),
                      derivedObject.getDbRelease(),
                      derivedObject.getNewS(),
                      derivedObject.getNewTs(),
                      derivedObject.getNewD(),
                      derivedObject.getNewN());

    System.out.println("pl/sql inspect : " + subclassedObjectsService.inspectObject(baseObject));

    // inspect of object instantiated in java
    derivedObject = new DerivedObject();
    derivedObject.setD(new Date(System.currentTimeMillis()));
    derivedObject.setInstance("any string");
    derivedObject.setTs(new Timestamp(System.currentTimeMillis()));
    derivedObject.setDbVersion(88);
    derivedObject.setDbRelease(99);
    derivedObject.setNewD(new Date(0));
    derivedObject.setNewN(1234567890);
    derivedObject.setNewS("another string");
    derivedObject.setNewTs(new Timestamp((long) (Math.random() * 1000L * 3600L * 24L * 365L * 50L)));
    System.out.println("pl/sql inspect : " + subclassedObjectsService.inspectObject(derivedObject));
  }
}
