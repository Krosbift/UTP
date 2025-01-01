package com.api.core.database.config;

import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import com.api.core.config.env.EnvConfig;
import com.api.core.config.env.enums.EnvKeys;

/**
 * Configuration class for setting up the Microsoft SQL Server database
 * connection.
 * This class uses environment configuration to set up the DataSource and
 * JdbcTemplate beans.
 * 
 * <p>
 * Example usage:
 * </p>
 * 
 * <pre>
 * {@code
 * @Autowired
 * private JdbcTemplate jdbcTemplate;
 * 
 * public void someMethod() {
 *   jdbcTemplate.query("SELECT * FROM some_table", new RowMapper<SomeEntity>() {
 *     // RowMapper implementation
 *   });
 * }
 * </pre>
 */
@Configuration
public class MicrosoftSqlServerDB {
  private final EnvConfig envConfig = new EnvConfig();

  /**
   * Configures and provides a DataSource bean for connecting to a Microsoft SQL Server database.
   * 
   * @return a configured DataSource instance
   */
  @Bean
  DataSource dataSource() {
    DriverManagerDataSource dataSource = new DriverManagerDataSource();
    dataSource.setUrl(envConfig.get(EnvKeys.DB_URL));
    dataSource.setUsername(envConfig.get(EnvKeys.DB_USERNAME));
    dataSource.setPassword(envConfig.get(EnvKeys.DB_PASSWORD));
    dataSource.setDriverClassName(envConfig.get(EnvKeys.DB_DRIVER_CLASS_NAME));
    return dataSource;
  }

  /**
   * Creates and configures a {@link JdbcTemplate} bean using the provided {@link DataSource}.
   *
   * @param dataSource the {@link DataSource} to be used by the {@link JdbcTemplate}
   * @return a configured {@link JdbcTemplate} instance
   */
  @Bean
  JdbcTemplate jdbcTemplate(DataSource dataSource) {
    return new JdbcTemplate(dataSource);
  }
}
