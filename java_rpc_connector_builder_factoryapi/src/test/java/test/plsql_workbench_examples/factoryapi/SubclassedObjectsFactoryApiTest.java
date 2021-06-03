
package test.plsql_workbench_examples.factoryapi;

import java.sql.Date;
import java.sql.Timestamp;

import org.junit.jupiter.api.Test;

import factory.ExamplesRPCFactory;
import service.SubclassedObjectsService;
import transferobject.BaseObject;
import transferobject.DerivedObject;

public class SubclassedObjectsFactoryApiTest {
  @Test
  public void runDemo() throws Exception
  {
    int diff = 10;
    SubclassedObjectsService service = ExamplesRPCFactory.getSubclassedObjectsService();

    // create a base object from the database function
    BaseObject baseObject = service.getBaseObject();

    System.out.format("java-inspect: %s [d:%s/ts:%s/instance:%s/version:%d.%d]%n",
                      baseObject.getClass(),
                      baseObject.d,
                      baseObject.ts,
                      baseObject.instance,
                      baseObject.dbVersion,
                      baseObject.dbRelease);

    System.out.println("pl/sql inspect : " + service.inspectObject(baseObject));

    // create an derived object from the database function
    baseObject = service.getDerivedObject(diff);
    DerivedObject derivedObject = (DerivedObject) baseObject;
    System.out.format("java-inspect : %s [d:%s/ts:%s/instance:%s/version:%d.%d/newS:%s/newTs:%s/newD:%s/newN:%d]%n",
                      derivedObject.getClass(),
                      derivedObject.d,
                      derivedObject.ts,
                      derivedObject.instance,
                      derivedObject.dbVersion,
                      derivedObject.dbRelease,
                      derivedObject.newS,
                      derivedObject.newTs,
                      derivedObject.newD,
                      derivedObject.newN);

    System.out.println("pl/sql inspect : " + service.inspectObject(baseObject));

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
    System.out.println("pl/sql inspect : " + service.inspectObject(derivedObject));
  }
}
