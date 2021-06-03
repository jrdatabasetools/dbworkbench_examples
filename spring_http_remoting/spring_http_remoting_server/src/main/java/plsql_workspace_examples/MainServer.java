
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
    poolDataSource.setURL(System.getenv("db.url"));
    poolDataSource.setUser(System.getenv("db.username"));
    poolDataSource.setPassword(System.getenv("db.password"));
    poolDataSource.setMinPoolSize(1);
    poolDataSource.setMaxPoolSize(10);
    poolDataSource.setLoginTimeout(5);
    return poolDataSource;
  }

  public static void main(String[] args)
  {
    SpringApplication.run(MainServer.class, args);
  }
}
