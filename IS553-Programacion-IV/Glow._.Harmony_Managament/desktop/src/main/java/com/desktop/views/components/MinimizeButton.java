package com.desktop.views.components;

import java.awt.Color;
import java.awt.Container;
import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JButton;

import com.desktop.core.utils.interfaces.ComponentInterface;
import com.desktop.views.AppFrameController;

public class MinimizeButton extends JButton implements ComponentInterface {
  public AppFrameController controller;

  public MinimizeButton(AppFrameController controller) {
    this.controller = controller;
    _configureComponent();
    _listenerClicking();
  }

  public void _configureComponent() {
    this.setText("_");
    this.setForeground(Color.decode("#FFFFFF"));
    this.setBackground(Color.decode("#121212"));
    this.setBorderPainted(false);
    this.setFocusPainted(false);
    ((Container) controller.childComponents.get("ButtonsPanelComponent")).add(this, BorderLayout.WEST);
  }

  /**
   * Adds an ActionListener to the button that minimizes the application window
   * when clicked. The ActionListener sets the state of the application's main
   * frame to ICONIFIED, effectively minimizing the window.
   */
  private void _listenerClicking() {
    this.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent e) {
        controller.appFrame.setState(JFrame.ICONIFIED);
      }
    });
  }
}
