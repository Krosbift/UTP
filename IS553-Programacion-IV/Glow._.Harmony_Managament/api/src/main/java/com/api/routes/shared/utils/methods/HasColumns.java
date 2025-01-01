package com.api.routes.shared.utils.methods;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class HasColumns {
  public static boolean verify(ResultSet rs, String column) throws SQLException {
    ResultSetMetaData rsmd = rs.getMetaData();
    int columns = rsmd.getColumnCount();
    for (int x = 1; x <= columns; x++) {
      if (column.equals(rsmd.getColumnName(x))) {
        return true;
      }
    }
    return false;
  }
}
