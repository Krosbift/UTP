package com.desktop.views.login.components;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import com.desktop.views.login.LoginPanelController;
import com.desktop.core.utils.interfaces.ComponentInterface;

public class RightPanelComponent extends JPanel implements ComponentInterface {
  public LoginPanelController controller;

  public RightPanelComponent(LoginPanelController controller) {
    this.controller = controller;
    _configureComponent();
    _listernerSizing();
  }

  @Override
  public void _configureComponent() {
    this.setLayout(new BorderLayout());
    controller.loginComponent.add(this);
  }

  /**
   * Adds a component listener to the current component to handle resizing events.
   * When the component is resized, the `resizeComponents` method is called to
   * adjust
   * the size of the components accordingly. This method also calls
   * `resizeComponents`
   * initially to ensure the components are sized correctly from the start.
   */
  private void _listernerSizing() {
    controller.loginComponent.addComponentListener(new ComponentAdapter() {
      @Override
      public void componentResized(ComponentEvent e) {
        resizeComponents();
      }
    });
    resizeComponents();
  }

  /**
   * Adjusts the size and position of the components within the panel.
   * 
   * This method calculates the new width of the panel by subtracting the maximum
   * value between one-third of the login component's width and 300 from the login
   * component's width. It then sets the bounds of the panel accordingly and
   * triggers revalidation and repainting to reflect the changes.
   */
  private void resizeComponents() {
    if (controller.loginComponent != null) {
      int loginWith = controller.loginComponent.getWidth();
      int width = loginWith - Math.max(loginWith / 3, 300);
      this.setBounds(Math.max(loginWith / 3, 300), 0, width, controller.loginComponent.getHeight());
      this.repaint();
      this.revalidate();
    }
  }
}
