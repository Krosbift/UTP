package com.desktop.views.login;

import javax.swing.JPanel;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class LoginPanelComponent extends JPanel {
  public LoginPanelController controller;

  public LoginPanelComponent(LoginPanelController controller) {
    this.controller = controller;
    _configurateComponent();
    _listernerSizing();
  }

  public void _configurateComponent() {
    this.setLayout(null);
    this.setBounds(0, 0, controller.parentController.appFrame.getWidth(),
        controller.parentController.appFrame.getHeight());
    controller.parentController.appFrame.add(this);
  }

  /**
   * Adds a component listener to the current component to handle resizing events.
   * When the component is resized, the `resizeComponents` method is called to
   * adjust the size of the components accordingly.
   */
  private void _listernerSizing() {
    controller.parentController.appFrame.addComponentListener(new ComponentAdapter() {
      @Override
      public void componentResized(ComponentEvent e) {
        resizeComponents();
      }
    });
    resizeComponents();
  }

  /**
   * Resizes the components of the login panel to match the dimensions of the parent application frame.
   * This method sets the bounds of the login panel to the width and height of the parent frame,
   * and then triggers revalidation and repainting of the component to reflect the changes.
   */
  private void resizeComponents() {
    this.setBounds(0, 0, controller.parentController.appFrame.getWidth(),
        controller.parentController.appFrame.getHeight());
    revalidate();
    repaint();
  }
}
