
package context;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServerContext {
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
