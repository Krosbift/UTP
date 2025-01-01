package com.api.routes.shared.utils.query;

/**
 * Represents a container for SQL query and its parameters.
 */
public class Binds {
  private String sql;
  private Object[] params;

  public Binds(String sql, Object[] params) {
    this.sql = sql;
    this.params = params;
  }

  /**
   * Retrieves the SQL query string.
   *
   * @return the SQL query string.
   */
  public String getSql() {
    return sql;
  }

  /**
   * Retrieves the parameters associated with this instance.
   *
   * @return an array of objects representing the parameters.
   */
  public Object[] getParams() {
    return params;
  }
}
