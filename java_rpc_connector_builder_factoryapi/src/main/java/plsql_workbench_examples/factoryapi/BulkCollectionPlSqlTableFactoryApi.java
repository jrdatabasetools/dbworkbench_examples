
package plsql_workbench_examples.factoryapi;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.UUID;

import factory.ExamplesRPCFactory;
import plsql_workbench_examples.timer.TimerCounter;
import service.BulkPlsqlTableService;

public class BulkCollectionPlSqlTableFactoryApi {
  private final static int ELEMENTS = 50000;

  public static void main(String[] args)
  {
    try {
      // set database credentials and configuration parameters
      System.setProperty("dbw_examples.url", "jdbc:oracle:thin:@192.168.0.109:1521/orcl");
      System.setProperty("dbw_examples.username", "dbw_examples");
      System.setProperty("dbw_examples.password", "dbw_examples");

      // getting the service
      BulkPlsqlTableService service = ExamplesRPCFactory.getBulkPlsqlTableService();

      // date is transmitted as varchar2 and converted in pl/sql as date type again, because only number and varchar2 types are 
      // supported thru jdbc driver
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

      // generating 50000 elements to transfer to the stored procedure 
      Integer[] numberList = new Integer[ELEMENTS];
      String[] dateList = new String[ELEMENTS];
      String[] stringList = new String[ELEMENTS];
      for (int i = 0; i < ELEMENTS; i++) {
        numberList[i] = (int) (Math.random() * Integer.MAX_VALUE);
        dateList[i] = sdf.format(new Date(System.currentTimeMillis() + (long) (Math.random() * Integer.MAX_VALUE)));
        stringList[i] = UUID.randomUUID().toString();
      }

      // timer to check the throughput
      TimerCounter tc = new TimerCounter();

      // calling the stored procedure
      service.bulkPlsqlTable(numberList, dateList, stringList);

      // print out throughput
      System.out.println(tc.perSecond("pl/sql bulk performance (first call)", ELEMENTS));

      // calling the stored procedure
      service.bulkPlsqlTable(numberList, dateList, stringList);

      // print out throughput
      System.out.println(tc.perSecond("pl/sql bulk performance (second call)", ELEMENTS));
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }
}
