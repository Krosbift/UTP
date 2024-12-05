package com.desktop.core.navigation;

import java.awt.Color;
import javax.swing.JPanel;

import com.desktop.core.utils.interfaces.ComponentInterface;

public class NavigationPanelComponent extends JPanel implements ComponentInterface {
  public NavigationPanelController controller;

  public NavigationPanelComponent(NavigationPanelController controller) {
    this.controller = controller;
    _configureComponent();
  }

  @Override
  public void _configureComponent() {
    this.setLayout(null);
    this.setBackground(Color.decode("#f5f5f5"));
    this.setBounds(0, 0, controller.parentController.appFrame.getWidth(),
        controller.parentController.appFrame.getHeight());
    controller.parentController.appFrame.add(this);
  }
}
