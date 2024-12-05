package com.desktop.views;

import javax.swing.JFrame;
import java.awt.Toolkit;
import java.awt.BorderLayout;
import com.desktop.core.utils.interfaces.ComponentInterface;

public class AppFrameComponent extends JFrame implements ComponentInterface {
  public AppFrameController controller;

  public AppFrameComponent(AppFrameController controller) {
    this.controller = controller;
    _configureComponent();
  }

  @Override
  public void _configureComponent() {
    this.setSize(
        Toolkit.getDefaultToolkit().getScreenSize().width - 300,
        Toolkit.getDefaultToolkit().getScreenSize().height - 100);
    this.setLayout(new BorderLayout());
    this.setLocationRelativeTo(null);
    this.setUndecorated(true);
  }
}
