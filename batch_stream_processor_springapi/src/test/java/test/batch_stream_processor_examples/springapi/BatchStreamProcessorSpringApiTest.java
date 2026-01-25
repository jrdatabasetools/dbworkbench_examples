
package test.batch_stream_processor_examples.springapi;

import java.util.stream.Stream;

import javax.sql.DataSource;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import service.BatchStreamProcessorService;
import transferobject.BatchStreamProcessorTO;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { BatchStreamProcessorSpringApiTest.class } )
@ComponentScan(basePackages = "impl")
public class BatchStreamProcessorSpringApiTest {
  @Autowired
  private BatchStreamProcessorService batchStreamProcessorService;

  @Test
  public void runDemo() throws Exception {
    // use try-with-resources statement to free resources after call
    try (Stream<BatchStreamProcessorTO.RecBatchRow> stream = batchStreamProcessorService.job1Stream(1000, 0)) {
      stream.forEach(e -> System.out.format("no:%d   description:%s   timestamp:%3$tD %3$tT%n",
                                            e.getNo(),
                                            e.getDescription(),
                                            e.getTs()));
    }
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
