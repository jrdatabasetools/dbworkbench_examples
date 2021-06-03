
package test.batch_stream_processor_examples.springapi;

import java.util.stream.Stream;

import javax.sql.DataSource;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import impl.BatchStreamProcessorServiceImpl;
import oracle.ucp.jdbc.PoolDataSource;
import oracle.ucp.jdbc.PoolDataSourceFactory;
import service.BatchStreamProcessorService;
import transferobject.BatchStreamProcessorTO;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { BatchStreamProcessorSpringApiTest.class, BatchStreamProcessorServiceImpl.class })
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
