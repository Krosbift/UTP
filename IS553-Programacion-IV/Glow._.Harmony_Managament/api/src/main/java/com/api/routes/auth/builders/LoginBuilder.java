package com.api.routes.auth.builders;

import java.util.ArrayList;
import java.util.List;
import com.api.routes.auth.sql.AuthSql;
import com.api.routes.auth.dtos.LoginUserDto;
import com.api.routes.shared.utils.query.Binds;

public class LoginBuilder {
  /**
   * Builds a SQL query to find a user based on the provided login details.
   *
   * @param user the login details of the user
   * @return a Binds object containing the SQL query and the parameters
   */
  public static Binds buildFindUser(LoginUserDto user) {
    StringBuilder sql = new StringBuilder(AuthSql.LOGIN.getQuery());
    List<Object> params = new ArrayList<>();
    sql.append(" AND IUS.EMAIL = ?");
    params.add(user.getEmail());
    return new Binds(sql.toString(), params.toArray());
  }
}
