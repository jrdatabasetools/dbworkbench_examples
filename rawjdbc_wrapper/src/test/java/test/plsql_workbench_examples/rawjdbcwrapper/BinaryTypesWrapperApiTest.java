
package test.plsql_workbench_examples.rawjdbcwrapper;

import java.sql.Connection;
import java.sql.DriverManager;

import org.junit.jupiter.api.Test;

import transferobject.BinaryTypesTO;
import wrapper.BinaryTypesWrapper;

public class BinaryTypesWrapperApiTest {
  @Test
  public void runDemo() throws Exception {
    // get service
    Class.forName("oracle.jdbc.driver.OracleDriver");

    try (Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/xepdb1",
                                                             "dbw_examples",
                                                             "dbw_examples"))
    {
      // calling the stored procedure
      BinaryTypesTO binaryTypesTO = BinaryTypesWrapper.call(connection, 1f, 1d, 1);

      // print values
      System.out.format("%.12f   %.12f   %d%n",
                        binaryTypesTO.getOBinFloat(),
                        binaryTypesTO.getOBinDouble(),
                        binaryTypesTO.getReturnValue());
    }
  }
}
