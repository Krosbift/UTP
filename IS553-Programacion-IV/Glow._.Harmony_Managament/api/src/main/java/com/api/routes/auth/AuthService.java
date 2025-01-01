package com.api.routes.auth;

import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.api.routes.auth.dtos.LoginUserDto;
import com.api.routes.shared.utils.query.Binds;
import com.api.routes.auth.builders.LoginBuilder;
import com.api.routes.shared.models.user.UserModel;
import com.api.routes.auth.usecases.ValidateUseCase;
import com.api.routes.shared.mappers.user.UserMapper;

@Service
public class AuthService {
  @Autowired
  private JdbcTemplate jdbcTemplate;

  public UserModel login(LoginUserDto loginUserDto) {
    Binds binds = LoginBuilder.buildFindUser(loginUserDto);
    try {
      List<UserModel> userFound = jdbcTemplate.query(binds.getSql(), UserMapper.userRowMapper, binds.getParams());
      boolean isValid = ValidateUseCase.ValidateUser(loginUserDto, userFound.isEmpty() ? null : userFound.get(0));
      return isValid ? userFound.get(0) : null;
    } catch (Exception error) {
      throw new RuntimeException("An unexpected error occurred: " + error.getMessage());
    }
  }
}
