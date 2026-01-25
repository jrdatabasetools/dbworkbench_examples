
package plsql_workbench_examples.configuration;

import javax.sql.DataSource;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.GenericApplicationContext;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

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
    HikariConfig hkConfig = new HikariConfig();
    hkConfig.setUsername("dbw_examples");
    hkConfig.setPassword("dbw_examples");
    hkConfig.setJdbcUrl("jdbc:oracle:thin:@localhost:1521/xepdb1");
    hkConfig.setMaximumPoolSize(16);
    hkConfig.setMinimumIdle(2);
    return new HikariDataSource(hkConfig);
  }
}
