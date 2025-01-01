package com.api.routes.shared.mappers.user;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import com.api.routes.shared.models.user.UserModel;
import com.api.routes.shared.utils.methods.HasColumns;

public class UserMapper {
  public static RowMapper<UserModel> userRowMapper = new RowMapper<UserModel>() {
    @Override
    public UserModel mapRow(@SuppressWarnings("null") ResultSet rs, int rowNum) throws SQLException {
      UserModel user = new UserModel()
          .setUserId(rs, HasColumns.verify(rs, "USERID"))
          .setNames(rs, HasColumns.verify(rs, "NAMES"))
          .setSurNames(rs, HasColumns.verify(rs, "SURNAMES"))
          .initDocumentType()
          .setDocumentTypeId(rs, HasColumns.verify(rs, "DOCUMENTTYPEID"))
          .setDocumentType(rs, HasColumns.verify(rs, "DOCUMENTTYPE"))
          .setDocumentNumber(rs, HasColumns.verify(rs, "DOCUMENTNUMBER"))
          .setEmail(rs, HasColumns.verify(rs, "EMAIL"))
          .setPassword(rs, HasColumns.verify(rs, "PASSWORD"))
          .setPhone(rs, HasColumns.verify(rs, "PHONENUMBER"))
          .initRoleType()
          .setRoleTypeId(rs, HasColumns.verify(rs, "ROLETYPEID"))
          .setRoleType(rs, HasColumns.verify(rs, "ROLETYPE"))
          .setAddress(rs, HasColumns.verify(rs, "ADDRESS"))
          .setActive(rs, HasColumns.verify(rs, "ACTIVE"))
          .build();
      return user;
    }
  };
}
