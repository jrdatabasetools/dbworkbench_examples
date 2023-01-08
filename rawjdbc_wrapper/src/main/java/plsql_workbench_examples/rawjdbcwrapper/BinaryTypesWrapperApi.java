
package plsql_workbench_examples.rawjdbcwrapper;

import java.sql.Connection;
import java.sql.DriverManager;

import transferobject.BinaryTypesTO;
import wrapper.BinaryTypesWrapper;

public class BinaryTypesWrapperApi {
  public static void main(String[] args)
  {
    try {
      Class.forName("oracle.jdbc.driver.OracleDriver");

      try (Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.0.109:1521/orcl",
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
    catch (Exception e) {
      e.printStackTrace();
    }
  }
}
