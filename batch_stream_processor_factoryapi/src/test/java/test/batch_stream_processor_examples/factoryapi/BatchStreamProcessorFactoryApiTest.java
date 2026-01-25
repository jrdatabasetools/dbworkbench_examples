
package test.batch_stream_processor_examples.factoryapi;

import java.util.stream.Stream;

import org.junit.jupiter.api.Test;

import factory.ExamplesRPCFactory;
import service.BatchStreamProcessorService;
import transferobject.BatchStreamProcessorTO;

public class BatchStreamProcessorFactoryApiTest {
  @Test
  public void runDemo() throws Exception
  {
    // getting the service
    BatchStreamProcessorService service = ExamplesRPCFactory.getBatchStreamProcessorService();

    // use try-with-resources statement to free resources after call
    try (Stream<BatchStreamProcessorTO.RecBatchRow> stream = service.job1Stream(1000, 0)) {
      stream.forEach(e -> System.out.format("no:%d   description:%s   timestamp:%3$tD %3$tT%n",
                                            e.no,
                                            e.description,
                                            e.ts));
    }
  }
}
