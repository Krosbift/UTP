package com.desktop.core.navigation.components.ToolBar;

import java.util.Map;
import java.util.HashMap;
import com.desktop.core.navigation.NavigationPanelController;
import com.desktop.core.navigation.components.ToolBar.components.LogOutButtonComponent;
import com.desktop.core.navigation.components.ToolBar.components.UserNameTitleLabelComponent;
import com.desktop.core.utils.interfaces.ComponentInterface;
import com.desktop.core.utils.interfaces.ControllerInterface;

public class ToolBarPanelController implements ControllerInterface {
  public String email;
  public NavigationPanelController parentController;
  public ToolBarPanelComponent toolBarPanelComponent;
  public Map<String, ControllerInterface> childControllers;
  public Map<String, ComponentInterface> childComponents;

  public ToolBarPanelController(NavigationPanelController controller) {
    this.parentController = controller;
    _initComponent();
    _initChildControllers();
    _initChildComponents();
  }

  @Override
  public void _initComponent() {
    toolBarPanelComponent = new ToolBarPanelComponent(this);
  }

  @Override
  public void _initChildControllers() {
    childControllers = new HashMap<>();
  }

  public void _initChildComponents() {
    childComponents = new HashMap<>();
    childComponents.put("UserNameTitleLabelComponent", new UserNameTitleLabelComponent(this));
    childComponents.put("LogOutButtonComponent", new LogOutButtonComponent(this));
  }
}
