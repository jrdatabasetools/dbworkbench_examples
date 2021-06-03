
package plsql_workbench_examples.springapi;

import java.sql.Date;
import java.sql.Timestamp;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import factory.ExamplesRPCFactory;
import service.SubclassedObjectsService;
import transferobject.BaseObject;
import transferobject.DerivedObject;

@ContextConfiguration(classes = { ExamplesRPCFactory.class })
@ExtendWith(value = SpringExtension.class)
public class SubclassedObjectsSpringApiTest {
  @Autowired
  private SubclassedObjectsService subclassedObjectsService;

  @Test
  public void testSubclassedObject() throws Exception
  {
    int diff = 10;

    // create a base object from the database function
    BaseObject baseObject = subclassedObjectsService.getBaseObject();

    System.out.format("java-inspect: %s [d:%s/ts:%s/instance:%s/version:%d.%d]%n",
                      baseObject.getClass(),
                      baseObject.d,
                      baseObject.ts,
                      baseObject.instance,
                      baseObject.dbVersion,
                      baseObject.dbRelease);

    System.out.println("pl/sql inspect : " + subclassedObjectsService.inspectObject(baseObject));

    // create an derived object from the database function
    baseObject = subclassedObjectsService.getDerivedObject(diff);
    DerivedObject derivedObject = (DerivedObject) baseObject;
    System.out.format("java-inspect : %s [d:%s/ts:%s/instance:%s/version:%d.%d/newS:%s/newTs:%s/newD:%s/newN:%d]%n",
                      baseObject.getClass(),
                      baseObject.d,
                      baseObject.ts,
                      baseObject.instance,
                      baseObject.dbVersion,
                      baseObject.dbRelease,
                      derivedObject.newS,
                      derivedObject.newTs,
                      derivedObject.newD,
                      derivedObject.newN);

    System.out.println("pl/sql inspect : " + subclassedObjectsService.inspectObject(baseObject));

    // inspect of object instantiated in java
    derivedObject = new DerivedObject();
    derivedObject.d = new Date(System.currentTimeMillis());
    derivedObject.instance = "any string";
    derivedObject.ts = new Timestamp(System.currentTimeMillis());
    derivedObject.dbVersion = 88;
    derivedObject.dbRelease = 99;
    derivedObject.newD = new Date(0);
    derivedObject.newN = 1234567890;
    derivedObject.newS = "another string";
    derivedObject.newTs = new Timestamp((long) (Math.random() * 1000L * 3600L * 24L * 365L * 50L));
    System.out.println("pl/sql inspect () : " + subclassedObjectsService.inspectObject(derivedObject));
  }
}