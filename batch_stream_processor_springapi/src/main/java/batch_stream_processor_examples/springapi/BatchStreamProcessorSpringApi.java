
package batch_stream_processor_examples.springapi;

import java.util.stream.Stream;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import service.BatchStreamProcessorService;
import transferobject.BatchStreamProcessorTO;

@Configuration
@ComponentScan(basePackages = { "impl" })
@Component
public class BatchStreamProcessorSpringApi {
  @Autowired
  private BatchStreamProcessorService batchStreamProcessorService;

  public static void main(String[] args) {
    // set database credentials and configuration parameters
    System.setProperty("dbw_examples.url", "jdbc:oracle:thin:@192.168.0.109:1521/orcl");
    System.setProperty("dbw_examples.username", "dbw_examples");
    System.setProperty("dbw_examples.password", "dbw_examples");

    // Register Spring Beans, Spring Context and call demo method 
    try (
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(BatchStreamProcessorSpringApi.class))
    {
      BatchStreamProcessorSpringApi demo = ctx.getBean(BatchStreamProcessorSpringApi.class);
      demo.runDemo();
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }

  private void runDemo() {
    // use try-with-resources statement to free resources after call
    try (Stream<BatchStreamProcessorTO.RecBatchRow> stream = batchStreamProcessorService.job1Stream(1000, 0)) {
      stream.forEach(e -> System.out.format("no:%d   description:%s   timestamp:%3$tD %3$tT%n",
                                            e.getNo(),
                                            e.getDescription(),
                                            e.getTs()));
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Bean
  public DataSource getDataSource() throws Exception {
    HikariConfig hkConfig = new HikariConfig();
    hkConfig.setUsername(System.getProperty("dbw_examples.username"));
    hkConfig.setPassword(System.getProperty("dbw_examples.password"));
    hkConfig.setJdbcUrl(System.getProperty("dbw_examples.url"));
    hkConfig.setMaximumPoolSize(16);
    hkConfig.setMinimumIdle(2);
    return new HikariDataSource(hkConfig);
  }
}
