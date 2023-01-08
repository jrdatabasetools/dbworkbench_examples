
package application;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import javax.sql.DataSource;

import factory.RmiServerRegistry;
import oracle.ucp.jdbc.PoolDataSource;
import oracle.ucp.jdbc.PoolDataSourceFactory;

public class MainServerFactoryApiManualRun {
  public static void main(String[] args) {
    try {
      Registry registry = LocateRegistry.createRegistry(1099);

      // call bindServices to register services and set data source
      RmiServerRegistry.bindServices(registry, 1098, getDataSource());
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static DataSource getDataSource() throws Exception {
    PoolDataSource poolDataSource = PoolDataSourceFactory.getPoolDataSource();
    poolDataSource.setConnectionFactoryClassName("oracle.jdbc.pool.OracleDataSource");
    poolDataSource.setURL("jdbc:oracle:thin:@192.168.0.109:1521/orcl");
    poolDataSource.setUser("dbw_examples");
    poolDataSource.setPassword("dbw_examples");
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
