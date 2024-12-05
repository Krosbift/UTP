package com.desktop.views.users.service;

import java.util.List;
import com.desktop.core.database.service.HttpClientService;
import com.desktop.views.shared.models.index.DocumentTypeModel;
import com.desktop.views.shared.models.index.RoleTypeModel;
import com.desktop.views.shared.models.user.UserModel;
import com.desktop.views.users.model.RegisterUserDto;

public class UsersService {
  private final HttpClientService httpClientService = new HttpClientService();

  public UserModel registerUser(RegisterUserDto registerUserDto) {
    httpClientService.endpoint = "/users";
    try {
      return httpClientService.post("/register", registerUserDto, UserModel.class);
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  public List<DocumentTypeModel> getDocumentTypes() {
    httpClientService.endpoint = "/index";
    try {
      return httpClientService.getList("/document-types", DocumentTypeModel.class);
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  public List<RoleTypeModel> getRoleTypes() {
    httpClientService.endpoint = "/index";
    try {
      return httpClientService.getList("/role-types", RoleTypeModel.class);
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }
}
