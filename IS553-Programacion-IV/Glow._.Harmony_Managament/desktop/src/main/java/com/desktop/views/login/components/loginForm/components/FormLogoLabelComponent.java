package com.desktop.views.login.components.loginForm.components;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import com.desktop.core.utils.interfaces.ComponentInterface;
import com.desktop.views.login.components.loginForm.LoginFormPanelController;

public class FormLogoLabelComponent extends JLabel implements ComponentInterface {
  public LoginFormPanelController controller;

  public FormLogoLabelComponent(LoginFormPanelController controller) {
    this.controller = controller;
    _configureComponent();
    _listenerSizing();
  }

  @Override
  public void _configureComponent() {
    ImageIcon logoImage = new ImageIcon(getClass().getResource("/images/glow._.harmony_logo.png"));
    this.setIcon(logoImage);
    ((SubFormPanelComponent) controller.childComponents.get("SubFormPanelComponent")).add(this);
  }

  /**
   * Adds a component listener to a child component that triggers the
   * resizeComponents method
   * whenever the component is resized.
   */
  private void _listenerSizing() {
    ((SubFormPanelComponent) controller.childComponents.get("SubFormPanelComponent"))
        .addComponentListener(new ComponentAdapter() {
          @Override
          public void componentResized(ComponentEvent e) {
            resizeComponents();
          }
        });
  }

  /**
   * Adjusts the size and position of the components within the login form panel.
   * This method centers the component within the login form panel by calculating
   * the center coordinates and setting the bounds accordingly.
   */
  private void resizeComponents() {
    int centerX = ((SubFormPanelComponent) controller.childComponents.get("SubFormPanelComponent")).getWidth() / 2;
    int centerY = ((SubFormPanelComponent) controller.childComponents.get("SubFormPanelComponent")).getHeight() / 2;
    this.setBounds(centerX - 100, centerY - 250, 200, 200);
    this.repaint();
    this.revalidate();
  }
}
