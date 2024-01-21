
package plsql_workspace_examples;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import oracle.ucp.jdbc.PoolDataSource;
import oracle.ucp.jdbc.PoolDataSourceFactory;

@Configuration
@EnableAutoConfiguration
@ComponentScan("impl")
public class MainServer {
  @Bean
  DataSource dataSource() throws SQLException
  {
    PoolDataSource poolDataSource = PoolDataSourceFactory.getPoolDataSource();
    poolDataSource.setConnectionFactoryClassName("oracle.jdbc.pool.OracleDataSource");
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

  public static void main(String[] args)
  {
    SpringApplication.run(MainServer.class, args);
  }
}
