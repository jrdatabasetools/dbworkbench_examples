
package plsql_workbench_examples;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import factory.ExamplesRPCFactory;
import service.ServerInfoSimpleObjectService;
import transferobject.SimpleObject;

@ContextConfiguration(classes = { RmiClientRegistrySpringApi.class, ExamplesRPCFactory.class })
@ExtendWith(value = SpringExtension.class)
@Component
public class RmiServerInfoSpringApiTest {
  @Autowired
  private ServerInfoSimpleObjectService serverInfoSimpleObjectService;

  @Test
  public void testSimpleTransferObject() throws Exception
  {
    assertNotNull(serverInfoSimpleObjectService);

    int diff = 10;

    // calling the stored procedure, receiving a transfer object
    SimpleObject info = serverInfoSimpleObjectService.call(diff);

    // print server information
    System.out.println("database date(+" + diff + "):" + info.getD());
    System.out.println("database timestamp(-" + diff + "):" + info.getTs());
    System.out.println("database instance name:" + info.getInstance());
    System.out.println("database version:" + info.getDbVersion() + "." + info.getDbRelease());
  }
}
