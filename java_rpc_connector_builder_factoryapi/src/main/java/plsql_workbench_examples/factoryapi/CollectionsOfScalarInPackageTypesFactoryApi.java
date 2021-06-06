
package plsql_workbench_examples.factoryapi;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import factory.ExamplesRPCFactory;
import service.CollectionInPackageService;
import transferobject.CollectionInPackageTO;

public class CollectionsOfScalarInPackageTypesFactoryApi {
  public static void main(String[] args)
  {
    try {
      // set database credentials and configuration parameters
      System.setProperty("dbw_examples.url", "jdbc:oracle:thin:@192.168.0.102:1521/orcl");
      System.setProperty("dbw_examples.username", "dbw_examples");
      System.setProperty("dbw_examples.password", "dbw_examples");
      System.setProperty("dbw_examples.poolsize.min", Integer.toString(3));
      System.setProperty("dbw_examples.poolsize.max", Integer.toString(10));

      // get the service
      CollectionInPackageService service = ExamplesRPCFactory.getCollectionInPackageService();

      // make some test data
      List<Integer> numberList = new ArrayList<>();
      List<Timestamp> timestampList = new ArrayList<>();

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
    catch (Exception e) {
      e.printStackTrace();
    }
  }
}
