
package application;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import javax.sql.DataSource;

import factory.RmiServerRegistry;
import oracle.ucp.jdbc.PoolDataSource;
import oracle.ucp.jdbc.PoolDataSourceFactory;

public class MainServerFactoryApi {
  public static void main(String[] args)
  {
    try {
      Registry registry = LocateRegistry.createRegistry(1099);

      // call bindServices to register services and set data source
      RmiServerRegistry.bindServices(registry, Integer.parseInt(System.getenv("port")), getDataSource());
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static DataSource getDataSource() throws Exception
  {
    PoolDataSource poolDataSource = PoolDataSourceFactory.getPoolDataSource();
    poolDataSource.setConnectionFactoryClassName("oracle.jdbc.pool.OracleDataSource");

    // environment variables are set in rmi_factoryapi_client/pom.xml docker execution in docker-start
    poolDataSource.setURL(System.getenv("db.url"));
    poolDataSource.setUser(System.getenv("db.username"));
    poolDataSource.setPassword(System.getenv("db.password"));
    poolDataSource.setMinPoolSize(1);
    poolDataSource.setMaxPoolSize(10);
    poolDataSource.setLoginTimeout(5);
    return poolDataSource;
  }
}
