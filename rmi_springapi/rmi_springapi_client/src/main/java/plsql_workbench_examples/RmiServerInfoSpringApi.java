
package plsql_workbench_examples;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import factory.ExamplesRPCFactory;
import service.ServerInfoSimpleObjectService;
import transferobject.SimpleObject;

@Configuration
@Component
public class RmiServerInfoSpringApi {
  @Autowired
  private ServerInfoSimpleObjectService serverInfoSimpleObjectService;

  public static void main(String[] args)
  {
    // Register Spring Beans, Spring Context and call demo method 
    try (AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext()) {
      ctx.register(ExamplesRPCFactory.class);
      ctx.register(RmiClientRegistrySpringApi.class);
      ctx.register(RmiServerInfoSpringApi.class);
      ctx.refresh();

      RmiServerInfoSpringApi demo = ctx.getBean(RmiServerInfoSpringApi.class);
      demo.runDemo();
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }

  private void runDemo()
  {
    try {
      int diff = 10;

      // calling the stored procedure, receiving a transfer object
      SimpleObject info = serverInfoSimpleObjectService.call(diff);

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
