package com.desktop.views.components;

import java.awt.Color;
import java.awt.Container;
import java.awt.BorderLayout;
import javax.swing.JLabel;

import com.desktop.core.utils.interfaces.ComponentInterface;
import com.desktop.views.AppFrameController;

public class TitleTopBarPanelComponent extends JLabel implements ComponentInterface {
  public AppFrameController controller;

  public TitleTopBarPanelComponent(AppFrameController controller) {
    this.controller = controller;
    _configureComponent();
  }

  public void _configureComponent() {
    this.setText("Glow._.Harmony Inventory Management System");
    this.setForeground(Color.WHITE);
    this.setHorizontalAlignment(JLabel.CENTER);
    ((Container) controller.childComponents.get("TopBarPanelComponent")).add(this, BorderLayout.CENTER);
  }
}
