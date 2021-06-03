
package plsql_workbench_examples;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import service.ServerInfoService;
import transferobject.ServerInfoTO;

@Configuration
@ComponentScan(basePackages = { "invoker" })
public class ServerInfo {
  @Autowired
  private ServerInfoService serverInfoService;

  public static void main(String[] args)
  {
    System.setProperty("modulePrefix", "ejb:/java_ee_springapi_server");

    // Register Spring Beans, Spring Context and call demo method 
    try (AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(ServerInfo.class)) {
      ServerInfo demo = ctx.getBean(ServerInfo.class);
      demo.runDemo();
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }

  private void runDemo() throws Exception
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

  @Bean
  public Context getContext() throws Exception
  {
    final Hashtable<String, String> jndiProperties = new Hashtable<>();
    jndiProperties.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");
    if (Boolean.getBoolean("http")) {
      //use HTTP based invocation. Each invocation will be a HTTP request
      jndiProperties.put(Context.PROVIDER_URL, "http://localhost:14567/wildfly-services");
    }
    else {
      //use HTTP upgrade, an initial upgrade requests is sent to upgrade to the remoting protocol
      jndiProperties.put(Context.PROVIDER_URL, "remote+http://localhost:14567");
    }
    return new InitialContext(jndiProperties);
  }
}
