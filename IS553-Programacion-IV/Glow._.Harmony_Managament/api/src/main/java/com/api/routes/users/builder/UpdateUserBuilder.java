package com.api.routes.users.builder;

import java.util.ArrayList;
import java.util.List;
import com.api.routes.shared.utils.query.Binds;
import com.api.routes.users.dto.RegisterUserDto;

public class UpdateUserBuilder {
  public static Binds buildUpdateUser(RegisterUserDto user, int userId) {
    StringBuilder sql = new StringBuilder("UPDATE TB_IMS_USERS SET ");
    List<Object> params = new ArrayList<>();
    if (user.getNames() != null) {
      sql.append("NAMES = ?, ");
      params.add(user.getNames());
    }
    if (user.getSurNames() != null) {
      sql.append("SURNAMES = ?, ");
      params.add(user.getSurNames());
    }
    if (user.getDocumentTypeId() != null) {
      sql.append("DOCUMENTTYPEID = ?, ");
      params.add(user.getDocumentTypeId());
    }
    if (user.getDocumentNumber() != null) {
      sql.append("DOCUMENTNUMBER = ?, ");
      params.add(user.getDocumentNumber());
    }
    if (user.getEmail() != null) {
      sql.append("EMAIL = ?, ");
      params.add(user.getEmail());
    }
    if (user.getPassword() != null) {
      sql.append("PASSWORD = ?, ");
      params.add(user.getPassword());
    }
    if (user.getPhone() != null) {
      sql.append("PHONENUMBER = ?, ");
      params.add(user.getPhone());
    }
    if (user.getRoleTypeId() != null) {
      sql.append("ROLETYPEID = ?, ");
      params.add(user.getRoleTypeId());
    }
    if (user.getAddress() != null) {
      sql.append("ADDRESS = ?, ");
      params.add(user.getAddress());
    }
    if (sql.charAt(sql.length() - 2) == ',') {
      sql.setLength(sql.length() - 2);
    }
    sql.append(" WHERE USERID = ?");
    params.add(userId);
    return new Binds(sql.toString(), params.toArray());
  }
}
