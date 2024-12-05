package com.desktop.views;

import java.util.Map;
import java.util.HashMap;
import java.awt.Component;
import java.awt.Container;
import com.desktop.core.navigation.NavigationPanelController;
import com.desktop.core.utils.interfaces.ComponentInterface;
import com.desktop.core.utils.interfaces.ControllerInterface;
import com.desktop.views.components.ButtonsPanelComponent;
import com.desktop.views.components.CloseButton;
import com.desktop.views.components.MaximizeButton;
import com.desktop.views.components.MinimizeButton;
import com.desktop.views.components.TitleTopBarPanelComponent;
import com.desktop.views.components.TopBarPanelComponent;
import com.desktop.views.login.LoginPanelController;
import com.desktop.views.service.AppService;
import com.desktop.views.shared.models.user.UserModel;

public class AppFrameController implements ControllerInterface {
  public AppFrameComponent appFrame;
  public AppService appService;
  public Map<String, ControllerInterface> childControllers;
  public Map<String, ComponentInterface> childComponents;

  public AppFrameController() {
    _activeDB();
    _initComponent();
    _initChildComponents();
    _initChildControllers();
  }

  @Override
  public void _initComponent() {
    appFrame = new AppFrameComponent(this);
  }

  @Override
  public void _initChildControllers() {
    childControllers = new HashMap<>();
    childControllers.put("LoginPanelController", new LoginPanelController(this));
  }

  public void _initChildComponents() {
    childComponents = new HashMap<>();
    childComponents.put("TopBarPanelComponent", new TopBarPanelComponent(this));
    childComponents.put("TitleTopBarPanelComponent", new TitleTopBarPanelComponent(this));
    childComponents.put("ButtonsPanelComponent", new ButtonsPanelComponent(this));
    childComponents.put("MinimizeButton", new MinimizeButton(this));
    childComponents.put("MaximizeButton", new MaximizeButton(this));
    childComponents.put("CloseButton", new CloseButton(this));
  }

  public void start() {
    appFrame.setVisible(true);
  }

  /**
   * Switches the current component in the application frame based on the provided
   * user.
   * If the user is null, it switches to the login panel; otherwise, it switches
   * to the navigation panel.
   *
   * @param user      the user of the user; if null, the login panel is
   *                   displayed
   * @param controller the controller instance to manage the current panel
   */
  public void switchComponent(UserModel user, ControllerInterface controller) {
    if (user == null) {
      removeAllComponents(((NavigationPanelController) controller).navigationPanelComponent);
      childComponents.clear();
      appFrame.revalidate();
      appFrame.repaint();
      childControllers.put("LoginPanelController", new LoginPanelController(this));
    } else {
      removeAllComponents(((LoginPanelController) controller).loginComponent);
      childComponents.clear();
      appFrame.revalidate();
      appFrame.repaint();
      childControllers.put("NavigationPanelController", new NavigationPanelController(this, user));
    }
  }

  /**
   * Recursively removes all components from the specified component and its
   * children.
   * If the component is a container, it will remove all its child components
   * first.
   * Finally, it removes the specified component from the app frame.
   *
   * @param component the component from which all child components will be
   *                  removed
   */
  private void removeAllComponents(Component component) {
    if (component instanceof Container) {
      Container container = (Container) component;
      for (Component child : container.getComponents()) {
        removeAllComponents(child);
      }
      container.removeAll();
    }
    appFrame.remove(component);
  }

  /**
   * Initializes the AppService and activates the database.
   */
  private void _activeDB() {
    appService = new AppService();
    appService.activeDatabase();
  }
}
