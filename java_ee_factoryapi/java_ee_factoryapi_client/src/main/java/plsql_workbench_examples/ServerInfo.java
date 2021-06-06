
package plsql_workbench_examples;

import factory.ExamplesRPCFactory;
import service.ServerInfoService;
import transferobject.ServerInfoTO;

public class ServerInfo {
  public static void main(String[] args)
  {
    try {
      int diff = 10;
      
      // get the service
      ServerInfoService service = ExamplesRPCFactory.getServerInfoService();

      // calling the stored procedure, receiving a transfer object
      ServerInfoTO info = service.call(diff);

      // print server information
      System.out.println("database date(+" + diff + "):" + info.oDate);
      System.out.println("database timestamp(-" + diff + "):" + info.oTimestamp);
      System.out.println("database instance name:" + info.oInstance);
      System.out.println("database version:" + info.oDbVersion + "." + info.oDbRelease);
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }
}
