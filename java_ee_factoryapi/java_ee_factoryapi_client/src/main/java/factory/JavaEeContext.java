
package factory;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;

final class JavaEeContext {
  static Context getContext() throws Exception {
    final Hashtable<String, String> jndiProperties = new Hashtable<>();
    jndiProperties.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");
    jndiProperties.put(Context.PROVIDER_URL, "remote+http://localhost:13456");
    return new InitialContext(jndiProperties);
  }

  static String getJndiModulePrefix() throws Exception {
    return "ejb:/java_ee_factoryapi_server";
  }
}
