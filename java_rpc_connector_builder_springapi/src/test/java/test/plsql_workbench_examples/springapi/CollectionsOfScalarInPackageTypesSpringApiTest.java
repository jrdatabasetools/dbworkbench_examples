
package test.plsql_workbench_examples.springapi;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import plsql_workbench_examples.configuration.BaseSpringConfig;
import service.CollectionInPackageService;
import transferobject.CollectionInPackageTO;

@ContextConfiguration(classes = { BaseSpringConfig.class })
@ExtendWith(SpringExtension.class)
public class CollectionsOfScalarInPackageTypesSpringApiTest {
  @Autowired
  private CollectionInPackageService collectionInPackageService;

  @Test
  public void runDemo() throws Exception
  {
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
    CollectionInPackageTO.DoItTO result = collectionInPackageService.doIt(numberList, timestampList);

    // print result data
    for (Timestamp ts : result.getIoColTimestamp()) {
      System.out.println("ts:" + ts);
    }
    for (String s : result.getOColVarchar()) {
      System.out.println(s);
    }
  }
}
