package com.desktop.views.login.components.loginForm.components;

import java.awt.Color;
import javax.swing.JPanel;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import com.desktop.core.utils.interfaces.ComponentInterface;
import com.desktop.views.login.components.loginForm.LoginFormPanelController;

public class SubFormPanelComponent extends JPanel implements ComponentInterface {
  public LoginFormPanelController controller;

  public SubFormPanelComponent(LoginFormPanelController controller) {
    this.controller = controller;
    _configureComponent();
    _listenerSizing();
  }

  @Override
  public void _configureComponent() {
    this.setBackground(Color.decode("#ffdffe"));
    this.setLayout(null);
    controller.loginFormPanelComponent.add(this);
  }

  /**
   * Adds a component listener to the current component to handle resizing events.
   * When the component is resized, the {@code resizeComponents()} method is
   * called
   * to adjust the size of the sub-components accordingly.
   */
  private void _listenerSizing() {
    controller.loginFormPanelComponent.addComponentListener(new ComponentAdapter() {
      @Override
      public void componentResized(ComponentEvent e) {
        resizeComponents();
      }
    });
  }

  /**
   * Adjusts the size and position of the components within the panel.
   * This method sets the bounds of the panel to match the dimensions
   * of the loginFormPanelComponent from the controller, and then
   * triggers a revalidation and repaint to ensure the changes are
   * reflected visually.
   */
  private void resizeComponents() {
    int width = controller.loginFormPanelComponent.getWidth() - 50;
    int height = controller.loginFormPanelComponent.getHeight() - 50;

    this.setBounds(25, 25, width, height);
    this.repaint();
    this.revalidate();
  }
}
