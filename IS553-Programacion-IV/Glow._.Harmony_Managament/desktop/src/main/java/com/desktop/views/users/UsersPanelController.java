package com.desktop.views.users;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.desktop.core.navigation.components.Content.ContentPanelController;
import com.desktop.core.utils.interfaces.ComponentInterface;
import com.desktop.core.utils.interfaces.ControllerInterface;
import com.desktop.views.shared.models.ViewModel;
import com.desktop.views.shared.models.index.DocumentTypeModel;
import com.desktop.views.shared.models.index.RoleTypeModel;
import com.desktop.views.users.components.RegisterUserComponent;
import com.desktop.views.users.service.UsersService;

public class UsersPanelController implements ControllerInterface {
  public ViewModel view;
  public UsersService userService = new UsersService();
  public ContentPanelController parentController;
  public UsersPanelComponent usersPanelComponent;
  public Map<String, ControllerInterface> childControllers;
  public Map<String, ComponentInterface> childComponents;
  public List<DocumentTypeModel> documentTypes;
  public List<RoleTypeModel> roleTypes;

  public UsersPanelController(ContentPanelController controller, ViewModel view) {
    this.parentController = controller;
    this.view = view;
    documentTypes = userService.getDocumentTypes();
    roleTypes = userService.getRoleTypes();
    _initComponent();
    _initChildControllers();
    _initChildComponents();
  }

  @Override
  public void _initComponent() {
    usersPanelComponent = new UsersPanelComponent(this);
  }

  @Override
  public void _initChildControllers() {
    childControllers = new HashMap<>();
  }

  public void _initChildComponents() {
    childComponents = new HashMap<>();
    childComponents.put("RegisterUserComponent", new RegisterUserComponent(this, view.getViewName()));
  }
}