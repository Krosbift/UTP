package com.desktop.core.navigation.components.Content;

import java.awt.Color;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.JPanel;
import com.desktop.core.utils.interfaces.ComponentInterface;

public class ContentPanelComponent extends JPanel implements ComponentInterface {
  public ContentPanelController controller;

  public ContentPanelComponent(ContentPanelController controller) {
    this.controller = controller;
    _configureComponent();
    _listenerSizing();
  }

  @Override
  public void _configureComponent() {
    this.setLayout(null);
    this.setBounds(200, 60,
        controller.parentController.parentController.appFrame.getWidth()
            - 200,
        controller.parentController.parentController.appFrame.getHeight() - 60);
    this.setBackground(Color.decode("#f0f0f0"));
    controller.parentController.navigationPanelComponent.add(this);
  }

  /**
   * Adds a component listener to the application's main frame to handle resizing
   * events.
   * When the frame is resized, the `resizeComponents` method is called to adjust
   * the sizes
   * of the components accordingly.
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
   * Resizes the components within the content panel based on the current
   * dimensions
   * of the application frame. The width of the side navigation is set to either
   * one-fifth of the application frame's width or a minimum of 200 pixels,
   * whichever
   * is greater. The content panel's bounds are then adjusted accordingly.
   * Finally, the component is revalidated and repainted to reflect the changes.
   */
  private void resizeComponents() {
    int sideNavWidth = Math.max(controller.parentController.parentController.appFrame.getWidth() / 5, 200);
    this.setBounds(sideNavWidth, 60,
        controller.parentController.parentController.appFrame.getWidth() - sideNavWidth,
        controller.parentController.parentController.appFrame.getHeight() - 60);
    this.repaint();
    this.revalidate();
  }
}
