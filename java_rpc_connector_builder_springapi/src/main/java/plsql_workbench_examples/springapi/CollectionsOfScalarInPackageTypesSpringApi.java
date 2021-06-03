
package plsql_workbench_examples.springapi;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.stereotype.Component;

import service.CollectionInPackageService;
import transferobject.CollectionInPackageTO;

@Component
public class CollectionsOfScalarInPackageTypesSpringApi {
  @Autowired
  private CollectionInPackageService collectionInPackageService;

  public static void main(String[] args)
  {
    // Register Spring Beans, Spring Context and call demo method 
    try (GenericApplicationContext ctx = BaseSpringConfig.getCtx(CollectionsOfScalarInPackageTypesSpringApi.class)) {
      ctx.getBean(CollectionsOfScalarInPackageTypesSpringApi.class).runDemo();
    }
  }

  private void runDemo()
  {
    try {
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
    catch (Exception e) {
      e.printStackTrace();
    }
  }
}
