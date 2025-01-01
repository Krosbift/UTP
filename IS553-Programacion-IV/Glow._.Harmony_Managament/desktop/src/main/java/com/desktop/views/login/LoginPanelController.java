package com.desktop.views.login;

import java.util.Map;
import java.util.HashMap;
import com.desktop.views.AppFrameController;
import com.desktop.views.login.components.ImageLabelComponent;
import com.desktop.views.login.components.RightPanelComponent;
import com.desktop.views.login.components.loginForm.LoginFormPanelController;
import com.desktop.views.login.services.LoginService;
import com.desktop.core.utils.interfaces.ComponentInterface;
import com.desktop.core.utils.interfaces.ControllerInterface;

public class LoginPanelController implements ControllerInterface {
  public LoginService loginService = new LoginService();
  public AppFrameController parentController;
  public LoginPanelComponent loginComponent;
  public Map<String, ControllerInterface> childControllers;
  public Map<String, ComponentInterface> childComponents;

  public LoginPanelController(AppFrameController controller) {
    this.parentController = controller;
    _initComponent();
    _initChildComponents();
    _initChildControllers();
  }

  @Override
  public void _initComponent() {
    loginComponent = new LoginPanelComponent(this);
  }

  @Override
  public void _initChildControllers() {
    childControllers = new HashMap<>();
    childControllers.put("LoginFormPanelController", new LoginFormPanelController(this));
  }

  public void _initChildComponents() {
    childComponents = new HashMap<>();
    childComponents.put("RightPanelComponent", new RightPanelComponent(this));
    childComponents.put("ImageLabelComponent", new ImageLabelComponent(this));
  }
}
