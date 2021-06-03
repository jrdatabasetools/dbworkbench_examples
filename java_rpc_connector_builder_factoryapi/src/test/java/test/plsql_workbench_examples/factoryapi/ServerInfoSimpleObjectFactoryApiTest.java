
package test.plsql_workbench_examples.factoryapi;

import org.junit.jupiter.api.Test;

import factory.ExamplesRPCFactory;
import service.ServerInfoSimpleObjectService;
import transferobject.SimpleObject;

public class ServerInfoSimpleObjectFactoryApiTest {
  @Test
  public void runDemo() throws Exception
  {
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
}
