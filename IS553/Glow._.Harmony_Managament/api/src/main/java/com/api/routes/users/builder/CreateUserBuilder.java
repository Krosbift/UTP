package com.api.routes.users.builder;

import java.util.ArrayList;
import java.util.List;
import com.api.routes.shared.utils.query.Binds;
import com.api.routes.users.dto.RegisterUserDto;

public class CreateUserBuilder {
  public static Binds buildRegisterUser(RegisterUserDto user) {
    StringBuilder sql = new StringBuilder("INSERT INTO TB_IMS_USERS ");
    StringBuilder columns = new StringBuilder("(");
    StringBuilder values = new StringBuilder("VALUES (");
    List<Object> params = new ArrayList<>();
    if (user.getNames() != null) {
      columns.append("NAMES, ");
      values.append("?, ");
      params.add(user.getNames());
    }
    if (user.getSurNames() != null) {
      columns.append("SURNAMES, ");
      values.append("?, ");
      params.add(user.getSurNames());
    }
    if (user.getDocumentTypeId() != null) {
      columns.append("DOCUMENTTYPEID, ");
      values.append("?, ");
      params.add(user.getDocumentTypeId());
    }
    if (user.getDocumentNumber() != null) {
      columns.append("DOCUMENTNUMBER, ");
      values.append("?, ");
      params.add(user.getDocumentNumber());
    }
    if (user.getEmail() != null) {
      columns.append("EMAIL, ");
      values.append("?, ");
      params.add(user.getEmail());
    }
    if (user.getPassword() != null) {
      columns.append("PASSWORD, ");
      values.append("?, ");
      params.add(user.getPassword());
    }
    if (user.getPhone() != null) {
      columns.append("PHONENUMBER, ");
      values.append("?, ");
      params.add(user.getPhone());
    }
    if (user.getRoleTypeId() != null) {
      columns.append("ROLETYPEID, ");
      values.append("?, ");
      params.add(user.getRoleTypeId());
    }
    if (user.getAddress() != null) {
      columns.append("ADDRESS, ");
      values.append("?, ");
      params.add(user.getAddress());
    }
    if (columns.charAt(columns.length() - 2) == ',') {
      columns.setLength(columns.length() - 2);
      values.setLength(values.length() - 2);
    }
    columns.append(") ");
    values.append(") ");
    sql.append(columns).append(values);
    return new Binds(sql.toString(), params.toArray());
  }
}
