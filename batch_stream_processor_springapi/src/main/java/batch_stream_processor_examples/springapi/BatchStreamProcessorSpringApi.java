
package batch_stream_processor_examples.springapi;

import java.util.stream.Stream;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import oracle.ucp.jdbc.PoolDataSource;
import oracle.ucp.jdbc.PoolDataSourceFactory;
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
    System.setProperty("dbw_examples.url", "jdbc:oracle:thin:@192.168.0.102:1521/orcl");
    System.setProperty("dbw_examples.username", "dbw_examples");
    System.setProperty("dbw_examples.password", "dbw_examples");
    System.setProperty("dbw_examples.poolsize.min", Integer.toString(3));
    System.setProperty("dbw_examples.poolsize.max", Integer.toString(10));
    
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
    PoolDataSource poolDataSource = PoolDataSourceFactory.getPoolDataSource();
    poolDataSource.setConnectionFactoryClassName("oracle.jdbc.pool.OracleDataSource");
    poolDataSource.setURL(System.getProperty("dbw_examples.url"));
    poolDataSource.setUser(System.getProperty("dbw_examples.username"));
    poolDataSource.setPassword(System.getProperty("dbw_examples.password"));
    poolDataSource.setMinPoolSize(Integer.parseInt(System.getProperty("dbw_examples.poolsize.min")));
    poolDataSource.setMaxPoolSize(Integer.parseInt(System.getProperty("dbw_examples.poolsize.max")));
    return poolDataSource;
  }
}
