package com.api.routes.shared.mappers.index;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

import com.api.routes.shared.models.index.RoleTypeModel;
import com.api.routes.shared.utils.methods.HasColumns;

public class RoleTypeMapper {
  public static RowMapper<RoleTypeModel> roleTypesRowMapper = new RowMapper<RoleTypeModel>() {
    @Override
    public RoleTypeModel mapRow(@SuppressWarnings("null") ResultSet rs, int rowNum) throws SQLException {
      RoleTypeModel roleType = new RoleTypeModel()
          .setRoleTypeId(rs, HasColumns.verify(rs, "ROLETYPEID"))
          .setRoleType(rs, HasColumns.verify(rs, "ROLETYPE"))
          .setDescription(rs, HasColumns.verify(rs, "DESCRIPTION"))
          .setActive(rs, HasColumns.verify(rs, "ACTIVE"))
          .build();
      return roleType;
    }
  };
}
