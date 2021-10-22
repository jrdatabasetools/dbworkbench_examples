
package plsql_workbench_examples.springapi;

import javax.sql.DataSource;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.GenericApplicationContext;

import oracle.ucp.jdbc.PoolDataSource;
import oracle.ucp.jdbc.PoolDataSourceFactory;

@Configuration
@ComponentScan(basePackages = { "impl" })
public class BaseSpringConfig {
  public static GenericApplicationContext getCtx(Class<?> clazz) {
    // Register Spring Beans and return Spring Context 
    AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
    ctx.register(BaseSpringConfig.class);
    ctx.register(clazz);
    ctx.refresh();
    return ctx;
  }

  @Bean
  public DataSource getDataSource() throws Exception {
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
    return poolDataSource;
  }
}
