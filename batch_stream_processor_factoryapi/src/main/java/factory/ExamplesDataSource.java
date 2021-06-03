
package factory;

import javax.sql.DataSource;

import oracle.ucp.jdbc.PoolDataSource;
import oracle.ucp.jdbc.PoolDataSourceFactory;

class ExamplesDataSource {
  private ExamplesDataSource() {}

  private static PoolDataSource dataSource;

  static DataSource getDataSource() throws Exception {
    if (dataSource == null) {
      PoolDataSource poolDataSource = PoolDataSourceFactory.getPoolDataSource();
      poolDataSource.setConnectionFactoryClassName("oracle.jdbc.pool.OracleDataSource");
      poolDataSource.setURL(System.getProperty("dbw_examples.url"));
      poolDataSource.setUser(System.getProperty("dbw_examples.username"));
      poolDataSource.setPassword(System.getProperty("dbw_examples.password"));
      poolDataSource.setMinPoolSize(Integer.parseInt(System.getProperty("dbw_examples.poolsize.min")));
      poolDataSource.setMaxPoolSize(Integer.parseInt(System.getProperty("dbw_examples.poolsize.max")));
      dataSource = poolDataSource;
    }
    return dataSource;
  }
}
