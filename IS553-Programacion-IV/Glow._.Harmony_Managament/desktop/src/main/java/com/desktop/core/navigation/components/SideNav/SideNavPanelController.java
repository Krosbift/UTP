package com.desktop.core.navigation.components.SideNav;

import java.util.Map;
import java.util.HashMap;
import com.desktop.core.navigation.NavigationPanelController;
import com.desktop.core.navigation.components.SideNav.components.ButtonMenuComponent;
import com.desktop.core.navigation.components.SideNav.components.LogoLabelComponent;
import com.desktop.core.navigation.components.Content.ContentPanelController;
import com.desktop.core.utils.interfaces.ComponentInterface;
import com.desktop.core.utils.interfaces.ControllerInterface;
import com.desktop.views.shared.models.ViewModel;

public class SideNavPanelController implements ControllerInterface {
  public NavigationPanelController parentController;
  public SideNavPanelComponent sideNavPanelComponent;
  public Map<String, ControllerInterface> childControllers;
  public Map<String, ComponentInterface> childComponents;

  public SideNavPanelController(NavigationPanelController controller) {
    this.parentController = controller;
    _initComponent();
    _initChildControllers();
    _initChildComponents();
  }

  @Override
  public void _initComponent() {
    sideNavPanelComponent = new SideNavPanelComponent(this);
  }

  @Override
  public void _initChildControllers() {
    childControllers = new HashMap<>();
  }

  public void _initChildComponents() {
    childComponents = new HashMap<>();
    childComponents.put("LogoLabelComponent", new LogoLabelComponent(this));
    findViews();
  }

  public void findViews() {
    ViewModel[] views = parentController.navigationService.getViews();
    for (ViewModel view : views) {
      if (parentController.user.getRoleType().getRoleTypeId() == 1 || view.getViewId() != 7) {
        childComponents.put(String.valueOf(view.getViewId()), new ButtonMenuComponent(this, view));
      }
    }
  }

  public void onMenuItemClick(ViewModel view) {
    ((ContentPanelController) parentController.childControllers.get("ContentPanelController")).loadView(view);
  }
}
