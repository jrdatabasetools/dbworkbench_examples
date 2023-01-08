/*
 * This file is autogenerated by PL/SQL Enterprise Workbench Connector Builder.
 * PL/SQL Enterprise Workbench Copyright (c) Jan Richter, www.jr-database-tools.com, Switzerland, 2015-2023. All rights reserved.
 *
 * THIS FILE WILL NOT BE OVERWRITTEN - TO GENERATE A NEW FILE ON NEXT RUN OF THE CONNECTOR BUILDER DELETE THIS FILE
 */

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
      poolDataSource.setInitialPoolSize(1);
      poolDataSource.setMinPoolSize(2);
      poolDataSource.setMaxPoolSize(10);
      poolDataSource.setLoginTimeout(10);
      poolDataSource.setInactiveConnectionTimeout(30);
      poolDataSource.setTimeoutCheckInterval(15);
      poolDataSource.setValidateConnectionOnBorrow(true);
      poolDataSource.setConnectionWaitTimeout(60);
      dataSource = poolDataSource;
    }
    return dataSource;
  }
}
