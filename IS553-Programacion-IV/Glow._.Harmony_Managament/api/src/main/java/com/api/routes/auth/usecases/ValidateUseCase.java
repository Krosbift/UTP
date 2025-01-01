package com.api.routes.auth.usecases;

import com.api.routes.auth.dtos.LoginUserDto;
import com.api.routes.shared.models.user.UserModel;

public class ValidateUseCase {
  public static boolean ValidateUser(LoginUserDto loginUserDto, UserModel userFound) {
    if (userFound == null) {
      return false;
    }
    if (!userFound.getPassword().equals(loginUserDto.getPassword())) {
      return false;
    }
    return true;
  }
}
