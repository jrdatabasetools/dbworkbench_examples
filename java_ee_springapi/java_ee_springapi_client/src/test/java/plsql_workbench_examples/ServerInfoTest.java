
package plsql_workbench_examples;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import invoker.ServerInfoInvoker;
import service.ServerInfoService;
import transferobject.ServerInfoTO;

@ContextConfiguration(classes = { ServerInfoTest.class, ServerInfoInvoker.class })
@ComponentScan(basePackages = { "invoker" })
@ExtendWith(value = SpringExtension.class)
public class ServerInfoTest {
  @Autowired
  private ServerInfoService serverInfoService;

  @Test
  public void test() throws Exception {
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
  public Context getContext() throws Exception {
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
