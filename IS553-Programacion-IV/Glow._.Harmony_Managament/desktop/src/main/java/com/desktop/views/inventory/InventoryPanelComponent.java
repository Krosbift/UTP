package com.desktop.views.inventory;

import java.awt.Color;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.JPanel;
import com.desktop.core.utils.interfaces.ComponentInterface;

public class InventoryPanelComponent extends JPanel implements ComponentInterface {
  public InventoryPanelController controller;

  public InventoryPanelComponent(InventoryPanelController controller) {
    this.controller = controller;
    _configureComponent();
    _listernerSizing();
  }

  @Override
  public void _configureComponent() {
    this.setLayout(null);
    this.setBackground(Color.decode("#ffdffe"));
    controller.parentController.contentPanelComponent.add(this);
  }

  /**
   * Adds a component listener to the current component to handle resizing events.
   * When the component is resized, the `resizeComponents` method is called to
   * adjust the size of the components accordingly.
   */
  private void _listernerSizing() {
    controller.parentController.contentPanelComponent.addComponentListener(new ComponentAdapter() {
      @Override
      public void componentResized(ComponentEvent e) {
        resizeComponents();
      }
    });
    resizeComponents();
  }

  /**
   * Resizes the components of the login panel to match the dimensions of the
   * parent application frame.
   * This method sets the bounds of the login panel to the width and height of the
   * parent frame,
   * and then triggers revalidation and repainting of the component to reflect the
   * changes.
   */
  private void resizeComponents() {
    int width = controller.parentController.contentPanelComponent.getWidth();
    int height = controller.parentController.contentPanelComponent.getHeight();
    this.setBounds(50, 40, width - 100, height - 80);
    this.revalidate();
    this.repaint();
  }
}