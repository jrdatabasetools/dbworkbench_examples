/*
 * Copyright (c) Jan Richter, JR-Database-Tools GmbH, Germany, Hamburg, 2015-2021. All rights reserved.
 */

package application;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import javax.sql.DataSource;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import factory.RmiServerRegistry;
import oracle.ucp.jdbc.PoolDataSource;
import oracle.ucp.jdbc.PoolDataSourceFactory;

@Configuration
public class MainServerSpringApi {
  public static void main(String[] args) {
    try (AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext()) {
      System.out.println("start server");

      ctx.register(MainServerSpringApi.class);
      ctx.register(RmiServerRegistry.class);
      ctx.scan("impl");
      ctx.refresh();

      RmiServerRegistry rmiServerRegistry = ctx.getBean(RmiServerRegistry.class);
      rmiServerRegistry.bindServices();

      System.out.println("server started");
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Bean
  public Registry getRegistry() throws RemoteException {
    return LocateRegistry.createRegistry(1099);
  }

  @Bean
  public DataSource getDataSource() throws Exception {
    PoolDataSource poolDataSource = PoolDataSourceFactory.getPoolDataSource();
    poolDataSource.setConnectionFactoryClassName("oracle.jdbc.pool.OracleDataSource");

    // environment variables are set in rmi_springapi_client/pom.xml docker execution in docker-start
    poolDataSource.setURL(System.getenv("db.url"));
    poolDataSource.setUser(System.getenv("db.username"));
    poolDataSource.setPassword(System.getenv("db.password"));
    poolDataSource.setMinPoolSize(1);
    poolDataSource.setMaxPoolSize(10);
    poolDataSource.setLoginTimeout(5);
    return poolDataSource;
  }
}
