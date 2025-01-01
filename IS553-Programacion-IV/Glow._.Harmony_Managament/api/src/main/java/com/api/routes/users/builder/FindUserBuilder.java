package com.api.routes.users.builder;

import java.util.ArrayList;
import java.util.List;
import com.api.routes.shared.utils.query.Binds;
import com.api.routes.users.sql.UserSql;

public class FindUserBuilder {
  public static Binds buildFindUser(String email) {
    StringBuilder sql = new StringBuilder(UserSql.FIND_USER.getQuery());
    List<Object> params = new ArrayList<>();
    if (email != null) {
      sql.append(" AND IUS.EMAIL = ?");
      params.add(email);
    }
    return new Binds(sql.toString(), params.toArray());
  }
}
