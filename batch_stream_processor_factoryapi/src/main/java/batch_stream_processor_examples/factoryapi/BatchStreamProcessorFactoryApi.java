
package batch_stream_processor_examples.factoryapi;

import java.util.stream.Stream;

import factory.ExamplesRPCFactory;
import service.BatchStreamProcessorService;
import transferobject.BatchStreamProcessorTO;

public class BatchStreamProcessorFactoryApi {
  public static void main(String[] args) {
    try {
      // set database credentials and configuration parameters
      System.setProperty("dbw_examples.url", "jdbc:oracle:thin:@192.168.0.109:1521/orcl");
      System.setProperty("dbw_examples.username", "dbw_examples");
      System.setProperty("dbw_examples.password", "dbw_examples");

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
    catch (Exception e) {
      e.printStackTrace();
    }
  }
}
