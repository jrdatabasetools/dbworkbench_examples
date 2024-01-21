
package application;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import javax.sql.DataSource;

import factory.RmiServerRegistry;
import oracle.ucp.jdbc.PoolDataSource;
import oracle.ucp.jdbc.PoolDataSourceFactory;

public class MainServerFactoryApi {
  public static void main(String[] args) {
    try {
      Registry registry = LocateRegistry.createRegistry(1099);

      // call bindServices to register services and set data source
      RmiServerRegistry.bindServices(registry, Integer.parseInt(System.getenv("port")), getDataSource());
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static DataSource getDataSource() throws Exception {
    PoolDataSource poolDataSource = PoolDataSourceFactory.getPoolDataSource();
    poolDataSource.setConnectionFactoryClassName("oracle.jdbc.pool.OracleDataSource");

    // environment variables are set in rmi_factoryapi_client/pom.xml docker execution in docker-start
    poolDataSource.setURL(String.format("jdbc:oracle:thin:@%s:1521/xepdb1", System.getenv("db.host")));
    poolDataSource.setUser(System.getenv("db.username"));
    poolDataSource.setPassword(System.getenv("db.password"));
    
    poolDataSource.setInitialPoolSize(1);
    poolDataSource.setMinPoolSize(2);
    poolDataSource.setMaxPoolSize(10);
    poolDataSource.setLoginTimeout(10);
    poolDataSource.setInactiveConnectionTimeout(30);
    poolDataSource.setTimeoutCheckInterval(15);
    poolDataSource.setValidateConnectionOnBorrow(true);
    poolDataSource.setConnectionWaitTimeout(60);
    return poolDataSource;
  }
}
