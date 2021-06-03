
package plsql_workbench_examples.springapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.stereotype.Component;

import service.ServerInfoService;
import transferobject.ServerInfoTO;

@Component
public class ServerInfoSpringApi {
  @Autowired
  private ServerInfoService serverInfoService;

  public static void main(String[] args)
  {
    // Register Spring Beans, Spring Context and call demo method 
    try (GenericApplicationContext ctx = BaseSpringConfig.getCtx(ServerInfoSpringApi.class)) {
      ctx.getBean(ServerInfoSpringApi.class).runDemo();
    }
  }

  private void runDemo()
  {
    try {
      int diff = 10;

      // calling the stored procedure, receiving a transfer object
      ServerInfoTO info = serverInfoService.call(diff);

      // print server information
      System.out.println("database date(+" + diff + "):" + info.getODate());
      System.out.println("database timestamp(-" + diff + "):" + info.getOTimestamp());
      System.out.println("database instance name:" + info.getOInstance());
      System.out.println("database version:" + info.getODbVersion() + "." + info.getODbRelease());
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }
}
