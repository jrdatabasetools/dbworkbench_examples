
package test.plsql_workbench_examples.factoryapi;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import factory.ExamplesRPCFactory;
import service.CollectionInPackageService;
import transferobject.CollectionInPackageTO;

public class CollectionsOfScalarInPackageTypesFactoryApiTest {
  @Test
  public void runDemo() throws Exception
  {
    // get the service
    CollectionInPackageService service = ExamplesRPCFactory.getCollectionInPackageService();

    // make some test data
    List<Integer> numberList = new ArrayList<Integer>();
    List<Timestamp> timestampList = new ArrayList<Timestamp>();

    for (int i = 0; i < 8; i++) {
      numberList.add(i);
    }

    for (int i = 0; i < 6; i++) {
      timestampList.add(new Timestamp(System.currentTimeMillis()));
    }

    // call the service
    CollectionInPackageTO.DoItTO result = service.doIt(numberList, timestampList);

    // print result data
    for (Timestamp ts : result.ioColTimestamp) {
      System.out.println("ts:" + ts);
    }
    for (String s : result.oColVarchar) {
      System.out.println(s);
    }
  }
}
