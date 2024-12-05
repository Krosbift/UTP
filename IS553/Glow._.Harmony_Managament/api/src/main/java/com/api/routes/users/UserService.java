package com.api.routes.users;

import java.util.List;
import java.sql.PreparedStatement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;
import com.api.routes.shared.mappers.user.UserMapper;
import com.api.routes.shared.models.user.UserModel;
import com.api.routes.shared.utils.query.Binds;
import com.api.routes.users.builder.CreateUserBuilder;
import com.api.routes.users.builder.FindUserBuilder;
import com.api.routes.users.builder.UpdateUserBuilder;
import com.api.routes.users.dto.RegisterUserDto;
import com.api.routes.users.sql.UserSql;

@Service
public class UserService {
  @Autowired
  private JdbcTemplate jdbcTemplate;

  private UserModel findUserById(int userId) {
    return jdbcTemplate.query(UserSql.FIND_USER_BY_ID.getQuery(), UserMapper.userRowMapper, userId).get(0);
  }

  public List<UserModel> findAllUsers() {
    try {
      return jdbcTemplate.query(UserSql.FIND_ALL_USERS.getQuery(), UserMapper.userRowMapper);
    } catch (Exception error) {
      throw new RuntimeException("An unexpected error occurred: " + error.getMessage());
    }
  }

  public UserModel findUser(String email) {
    Binds binds = FindUserBuilder.buildFindUser(email);
    try {
      List<UserModel> userFound = jdbcTemplate.query(binds.getSql(), UserMapper.userRowMapper, binds.getParams());
      return userFound.get(0);
    } catch (Exception error) {
      throw new RuntimeException("An unexpected error occurred: " + error.getMessage());
    }
  }

  public UserModel registerUser(RegisterUserDto registerData) {
    Binds binds = CreateUserBuilder.buildRegisterUser(registerData);
    try {
      KeyHolder keyHolder = new GeneratedKeyHolder();
      jdbcTemplate.update(connection -> {
        PreparedStatement ps = connection.prepareStatement(binds.getSql(), new String[] { "USERID" });
        final Object[] params = binds.getParams();
        for (int i = 0; i < params.length; i++) {
          ps.setObject(i + 1, params[i]);
        }
        return ps;
      }, keyHolder);
      @SuppressWarnings("null")
      int generatedId = keyHolder.getKey().intValue();
      return findUserById(generatedId);
    } catch (Exception error) {
      throw new RuntimeException("An unexpected error occurred: " + error.getMessage());
    }
  }

  public UserModel updateUser(RegisterUserDto updateData, int userId) {
    Binds binds = UpdateUserBuilder.buildUpdateUser(updateData, userId);
    try {
      jdbcTemplate.update(binds.getSql(), binds.getParams());
      return findUserById(userId);
    } catch (Exception error) {
      throw new RuntimeException("An unexpected error occurred: " + error.getMessage());
    }
  }

  public int activateUser(int userId) {
    try {
      jdbcTemplate.update(UserSql.ACTIVE_USER.getQuery(), userId);
      return userId;
    } catch (Exception error) {
      throw new RuntimeException("An unexpected error occurred: " + error.getMessage());
    }
  }

  public int deleteUser(int userId) {
    try {
      jdbcTemplate.update(UserSql.DELETE_USER.getQuery(), userId);
      return userId;
    } catch (Exception error) {
      throw new RuntimeException("An unexpected error occurred: " + error.getMessage());
    }
  }
}
