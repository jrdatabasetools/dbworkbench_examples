
package plsql_workbench_examples.springapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.stereotype.Component;

import plsql_workbench_examples.configuration.BaseSpringConfig;
import service.ServerInfoSimpleObjectService;
import transferobject.SimpleObject;

@Component
public class ServerInfoSimpleObjectSpringApi {
  @Autowired
  private ServerInfoSimpleObjectService ServerInfoSimpleObjectService;

  public static void main(String[] args)
  {
    // set database credentials and configuration parameters
    System.setProperty("dbw_examples.url", "jdbc:oracle:thin:@192.168.0.109:1521/orcl");
    System.setProperty("dbw_examples.username", "dbw_examples");
    System.setProperty("dbw_examples.password", "dbw_examples");

    // Register Spring Beans, Spring Context and call demo method 
    try (GenericApplicationContext ctx = BaseSpringConfig.getCtx(ServerInfoSimpleObjectSpringApi.class)) {
      ctx.getBean(ServerInfoSimpleObjectSpringApi.class).runDemo();
    }
  }

  private void runDemo()
  {
    try {
      int diff = 10;

      // calling the stored procedure, receiving a transfer object
      SimpleObject info = ServerInfoSimpleObjectService.call(diff);

      // print server information
      System.out.println("database date(+" + diff + "):" + info.getD());
      System.out.println("database timestamp(-" + diff + "):" + info.getTs());
      System.out.println("database instance name:" + info.getInstance());
      System.out.println("database version:" + info.getDbVersion() + "." + info.getDbRelease());
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }
}
