
package plsql_workbench_examples;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import context.ServerContext;
import invoker.ServerInfoInvoker;
import service.ServerInfoService;
import transferobject.ServerInfoTO;

@ContextConfiguration(classes = { ServerContext.class, ServerInfoInvoker.class })
@ExtendWith(value = SpringExtension.class)
public class ServerInfoTest {
  @Autowired
  private ServerInfoService serverInfoService;

  @Test
  public void test() throws Exception
  {
    int diff = 10;

    // calling the stored procedure, receiving a transfer object
    ServerInfoTO info = serverInfoService.call(diff);

    // print server information
    System.out.println("database date(+" + diff + "):" + info.getODate());
    System.out.println("database timestamp(-" + diff + "):" + info.getOTimestamp());
    System.out.println("database instance name:" + info.getOInstance());
    System.out.println("database version:" + info.getODbVersion() + "." + info.getODbRelease());
  }
}
