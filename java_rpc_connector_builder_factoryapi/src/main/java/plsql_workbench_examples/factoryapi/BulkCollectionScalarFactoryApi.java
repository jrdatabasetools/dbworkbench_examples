
package plsql_workbench_examples.factoryapi;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import factory.ExamplesRPCFactory;
import plsql_workbench_examples.timer.TimerCounter;
import service.BulkCollectionScalarService;

public class BulkCollectionScalarFactoryApi {
  private final static int ELEMENTS = 50000;

  public static void main(String[] args)
  {
    try {
      // set database credentials and configuration parameters
      System.setProperty("dbw_examples.url", "jdbc:oracle:thin:@192.168.0.102:1521/orcl");
      System.setProperty("dbw_examples.username", "dbw_examples");
      System.setProperty("dbw_examples.password", "dbw_examples");
      System.setProperty("dbw_examples.poolsize.min", Integer.toString(3));
      System.setProperty("dbw_examples.poolsize.max", Integer.toString(10));

      // getting the service
      BulkCollectionScalarService service = ExamplesRPCFactory.getBulkCollectionScalarService();

      // generating 50000 elements to transfer to the stored procedure 
      List<Integer> numberList = new ArrayList<>(ELEMENTS);
      List<Date> dateList = new ArrayList<>(ELEMENTS);
      List<String> stringList = new ArrayList<>(ELEMENTS);
      for (int i = 0; i < ELEMENTS; i++) {
        numberList.add((int) (Math.random() * Integer.MAX_VALUE));
        dateList.add(new Date(System.currentTimeMillis() + (long) (Math.random() * Integer.MAX_VALUE)));
        stringList.add(UUID.randomUUID().toString());
      }

      // timer to check the throughput
      TimerCounter tc = new TimerCounter();

      // calling the stored procedure
      service.call(numberList, dateList, stringList);

      // print out throughput
      System.out.println(tc.perSecond("scalar bulk performance (first call)", ELEMENTS));

      // calling the stored procedure
      service.call(numberList, dateList, stringList);

      // print out throughput
      System.out.println(tc.perSecond("scalar bulk performance (second call)", ELEMENTS));
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }
}
