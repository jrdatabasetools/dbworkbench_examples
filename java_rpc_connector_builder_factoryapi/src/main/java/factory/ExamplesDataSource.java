
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
      hkConfig.setUsername(System.getProperty("dbw_examples.username"));
      hkConfig.setPassword(System.getProperty("dbw_examples.password"));
      hkConfig.setJdbcUrl(System.getProperty("dbw_examples.url"));
      hkConfig.setMaximumPoolSize(16);
      hkConfig.setMinimumIdle(2);
      hikariDataSource = new HikariDataSource(hkConfig);
    }

    return hikariDataSource;
  }
}
