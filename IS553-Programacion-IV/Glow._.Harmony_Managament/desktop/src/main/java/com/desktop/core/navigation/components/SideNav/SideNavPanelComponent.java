package com.desktop.core.navigation.components.SideNav;

import java.awt.Color;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.JPanel;
import com.desktop.core.utils.interfaces.ComponentInterface;

public class SideNavPanelComponent extends JPanel implements ComponentInterface {
  public SideNavPanelController controller;

  public SideNavPanelComponent(SideNavPanelController controller) {
    this.controller = controller;
    _configureComponent();
    _listenerSizing();
  }

  @Override
  public void _configureComponent() {
    this.setVisible(true);
    this.setBounds(0, 0, 200, controller.parentController.parentController.appFrame.getHeight());
    this.setBackground(Color.decode("#eeceed"));
    controller.parentController.navigationPanelComponent.add(this);
  }

  /**
   * Adds a component listener to the application's main frame to handle resizing events.
   * The listener triggers the `resizeComponents` method whenever the frame is resized.
   * Additionally, it calls `resizeComponents` initially to ensure components are sized correctly.
   */
  private void _listenerSizing() {
    controller.parentController.parentController.appFrame.addComponentListener(new ComponentAdapter() {
      @Override
      public void componentResized(ComponentEvent evt) {
        resizeComponents();
      }
    });
    resizeComponents();
  }

  /**
   * Adjusts the size and bounds of the side navigation panel component.
   * The width of the panel is set to one-fifth of the application frame's width,
   * with a minimum width of 200 pixels. The height is set to match the height
   * of the application frame.
   */
  private void resizeComponents() {
    int sideNavWidth = Math.max(controller.parentController.parentController.appFrame.getWidth() / 5, 200);
    this.setBounds(0, 0, sideNavWidth, controller.parentController.parentController.appFrame.getHeight());
    this.repaint();
    this.revalidate();
  }
}
