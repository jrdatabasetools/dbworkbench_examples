
package test.plsql_workbench_examples.springapi;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import plsql_workbench_examples.configuration.BaseSpringConfig;
import service.ServerInfoSimpleObjectService;
import transferobject.SimpleObject;

@ContextConfiguration(classes = { BaseSpringConfig.class })
@ExtendWith(SpringExtension.class)
public class ServerInfoSimpleObjectSpringApiTest {
  @Autowired
  private ServerInfoSimpleObjectService ServerInfoSimpleObjectService;

  @Test
  public void runDemo() throws Exception
  {
    int diff = 10;

    // calling the stored procedure, receiving a transfer object
    SimpleObject info = ServerInfoSimpleObjectService.call(diff);

    // print server information
    System.out.println("database date(+" + diff + "):" + info.getD());
    System.out.println("database timestamp(-" + diff + "):" + info.getTs());
    System.out.println("database instance name:" + info.getInstance());
    System.out.println("database version:" + info.getDbVersion() + "." + info.getDbRelease());
  }
}
