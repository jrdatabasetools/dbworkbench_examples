
package plsql_workbench_examples.factoryapi;

import factory.ExamplesRPCFactory;
import service.ServerInfoSimpleObjectService;
import transferobject.SimpleObject;

public class ServerInfoSimpleObjectFactoryApi {
  public static void main(String[] args)
  {
    try {
      // set database credentials and configuration parameters
      System.setProperty("dbw_examples.url", "jdbc:oracle:thin:@192.168.0.102:1521/orcl");
      System.setProperty("dbw_examples.username", "dbw_examples");
      System.setProperty("dbw_examples.password", "dbw_examples");

      int diff = 10;

      // get the service
      ServerInfoSimpleObjectService service = ExamplesRPCFactory.getServerInfoSimpleObjectService();

      // calling the stored procedure, receiving a transfer object
      SimpleObject info = service.call(diff);

      // print server information
      System.out.println("database date(+" + diff + "):" + info.d);
      System.out.println("database timestamp(-" + diff + "):" + info.ts);
      System.out.println("database instance name:" + info.instance);
      System.out.println("database version:" + info.dbVersion + "." + info.dbRelease);
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }
}
