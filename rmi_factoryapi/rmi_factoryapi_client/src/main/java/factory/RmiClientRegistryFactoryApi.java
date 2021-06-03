
package factory;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RmiClientRegistryFactoryApi {
  private static Registry registry;

  public static synchronized Registry getRegistry() throws RemoteException
  {
    if (registry == null) {
      registry = LocateRegistry.getRegistry();
    }
    return registry;
  }
}
