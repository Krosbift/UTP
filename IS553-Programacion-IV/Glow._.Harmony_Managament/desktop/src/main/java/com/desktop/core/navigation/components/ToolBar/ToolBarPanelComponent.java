package com.desktop.core.navigation.components.ToolBar;

import java.awt.Color;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.JPanel;
import com.desktop.core.utils.interfaces.ComponentInterface;

public class ToolBarPanelComponent extends JPanel implements ComponentInterface {
  public ToolBarPanelController controller;

  public ToolBarPanelComponent(ToolBarPanelController controller) {
    this.controller = controller;
    _configureComponent();
    _listenerSizing();
  }

  @Override
  public void _configureComponent() {
    this.setVisible(true);
    this.setBounds(200, 0, controller.parentController.parentController.appFrame.getWidth() - 200, 60);
    this.setBackground(Color.decode("#e86c7c"));
    this.setLayout(null);
    controller.parentController.navigationPanelComponent.add(this);
  }

  /**
   * Adds a component listener to the application's main frame to handle resizing
   * events.
   * When the main frame is resized, the `resizeComponents` method is called to
   * adjust the
   * size of the components accordingly.
   * 
   * This method also calls `resizeComponents` initially to ensure that the
   * components
   * are sized correctly when the listener is first added.
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
   * Adjusts the size and position of the toolbar components based on the width of
   * the application frame.
   * The width of the side navigation is set to either one-fifth of the
   * application frame's width or a minimum of 200 pixels.
   * The toolbar is then positioned and resized to fit the remaining width of the
   * application frame, with a fixed height of 60 pixels.
   */
  private void resizeComponents() {
    int sideNavWidth = Math.max(controller.parentController.parentController.appFrame.getWidth() / 5, 200);
    this.setBounds(sideNavWidth, 0, controller.parentController.parentController.appFrame.getWidth() - sideNavWidth,
        60);
        this.repaint();
        this.revalidate();
  }
}
