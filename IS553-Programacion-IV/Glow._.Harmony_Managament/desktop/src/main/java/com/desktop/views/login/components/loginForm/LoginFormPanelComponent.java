package com.desktop.views.login.components.loginForm;

import java.awt.Color;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.JPanel;
import com.desktop.core.utils.interfaces.ComponentInterface;

public class LoginFormPanelComponent extends JPanel implements ComponentInterface {
  public LoginFormPanelController controller;

  public LoginFormPanelComponent(LoginFormPanelController controller) {
    this.controller = controller;
    _configureComponent();
    _listernerSizing();
  }

  @Override
  public void _configureComponent() {
    this.setBackground(Color.decode("#ffffff"));
    this.setLayout(null);
    controller.parentController.loginComponent.add(this);
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
    controller.parentController.loginComponent.addComponentListener(new ComponentAdapter() {
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
    if (controller.parentController.loginComponent != null) {
      int loginWith = controller.parentController.loginComponent.getWidth();
      int width = Math.max(loginWith / 3, 300);
      this.setBounds(0, 0, width, controller.parentController.loginComponent.getHeight());
      this.repaint();
      this.revalidate();
    }
  }
}
