
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
public class MainServerManualRun {
  @Bean
  DataSource dataSource() throws SQLException {
    PoolDataSource poolDataSource = PoolDataSourceFactory.getPoolDataSource();
    poolDataSource.setConnectionFactoryClassName("oracle.jdbc.pool.OracleDataSource");
    poolDataSource.setURL("jdbc:oracle:thin:@192.168.0.102:1521/orcl");
    poolDataSource.setUser("dbw_examples");
    poolDataSource.setPassword("dbw_examples");
    poolDataSource.setMinPoolSize(1);
    poolDataSource.setMaxPoolSize(10);
    poolDataSource.setLoginTimeout(5);
    return poolDataSource;
  }

  public static void main(String[] args) {
    SpringApplication.run(MainServerManualRun.class, args);
  }
}
