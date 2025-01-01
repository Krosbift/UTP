package com.desktop.views.components;

import java.awt.Color;
import java.awt.Container;
import java.awt.BorderLayout;
import javax.swing.JPanel;

import com.desktop.core.utils.interfaces.ComponentInterface;
import com.desktop.views.AppFrameController;

public class ButtonsPanelComponent extends JPanel implements ComponentInterface {
  public AppFrameController controller;

  public ButtonsPanelComponent(AppFrameController controller) {
    this.controller = controller;
    _configureComponent();
  }

  public void _configureComponent() {
    this.setBackground(Color.decode("#121212"));
    this.setLayout(new BorderLayout());
    ((Container) controller.childComponents.get("TopBarPanelComponent")).add(this, BorderLayout.EAST);
  }
}
