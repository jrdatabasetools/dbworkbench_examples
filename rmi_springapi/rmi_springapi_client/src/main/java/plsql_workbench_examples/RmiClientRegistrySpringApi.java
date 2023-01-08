/*
 * Copyright (c) Jan Richter, www.jr-database-tools.com, Switzerland, 2015-2023. All rights reserved.
 */

package plsql_workbench_examples;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RmiClientRegistrySpringApi {
  @Bean
  public Registry getRegistry() throws RemoteException
  {
    return LocateRegistry.getRegistry(1099);
  }
}
