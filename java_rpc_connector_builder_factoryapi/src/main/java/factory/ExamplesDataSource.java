
package factory;

import javax.sql.DataSource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

class ExamplesDataSource {
  private static HikariDataSource hikariDataSource;

  private ExamplesDataSource() {}

  public static DataSource getDataSource() {
    if (hikariDataSource == null) {
      HikariConfig hkConfig = new HikariConfig();
      hkConfig.setUsername("dbw_examples");
      hkConfig.setPassword("dbw_examples");
      hkConfig.setJdbcUrl("jdbc:oracle:thin:@localhost:1521/xepdb1");
      hkConfig.setMaximumPoolSize(16);
      hkConfig.setMinimumIdle(2);
      hikariDataSource = new HikariDataSource(hkConfig);
    }

    return hikariDataSource;
  }
}
