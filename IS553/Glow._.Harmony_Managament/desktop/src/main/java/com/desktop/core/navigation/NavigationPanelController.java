package com.desktop.core.navigation;

import java.util.Map;
import java.util.HashMap;
import com.desktop.views.AppFrameController;
import com.desktop.views.shared.models.user.UserModel;
import com.desktop.core.navigation.components.Content.ContentPanelController;
import com.desktop.core.navigation.components.SideNav.SideNavPanelController;
import com.desktop.core.navigation.components.ToolBar.ToolBarPanelController;
import com.desktop.core.navigation.components.ToolBar.components.UserNameTitleLabelComponent;
import com.desktop.core.navigation.services.NavigationService;
import com.desktop.core.utils.interfaces.ComponentInterface;
import com.desktop.core.utils.interfaces.ControllerInterface;

public class NavigationPanelController implements ControllerInterface {
  public UserModel user;
  public NavigationService navigationService = new NavigationService();
  public AppFrameController parentController;
  public NavigationPanelComponent navigationPanelComponent;
  public Map<String, ControllerInterface> childControllers;
  public Map<String, ComponentInterface> childComponents;

  public NavigationPanelController(AppFrameController controller, UserModel user) {
    this.parentController = controller;
    this.user = user;
    _initComponent();
    _initChildControllers();
  }

  @Override
  public void _initComponent() {
    navigationPanelComponent = new NavigationPanelComponent(this);
  }

  @Override
  public void _initChildControllers() {
    childControllers = new HashMap<>();
    childControllers.put("SideNavPanelController", new SideNavPanelController(this));
    childControllers.put("ToolBarPanelController", new ToolBarPanelController(this));
    childControllers.put("ContentPanelController", new ContentPanelController(this));
    setUser();
  }

  public void logOut() {
    parentController.switchComponent(null, this);
  }

  /**
   * Sets the user information in the navigation panel.
   * 
   * This method updates the text of the UserNameTitleLabelComponent to display a welcome message
   * with the user's first and last names. It also assigns the user object to the ContentPanelController.
   * 
   * If an exception occurs during this process, it sets the text of the UserNameTitleLabelComponent
   * to a default welcome message with "User".
   * 
   * @throws Exception if there is an issue accessing the child controllers or components.
   */
  public void setUser() {
    try {
      ((UserNameTitleLabelComponent) ((ToolBarPanelController) childControllers
          .get("ToolBarPanelController")).childComponents.get("UserNameTitleLabelComponent"))
          .setText("Welcome, " + user.getNames() + " " + user.getSurNames());
      ((ContentPanelController) childControllers.get("ContentPanelController")).user = user;
    } catch (Exception e) {
      ((UserNameTitleLabelComponent) childComponents.get("UserNameTitleLabelComponent")).setText("Welcome, " + "User");
    }
  }
}
