
package plsql_workbench_examples;

import factory.ExamplesRPCFactory;
import service.ServerInfoSimpleObjectService;
import transferobject.SimpleObject;

public class RmiServerInfoFactoryApi {
  public static void main(String[] args)
  {
    try {
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
