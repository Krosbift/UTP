package com.desktop.views.components;

import java.awt.Container;
import java.awt.BorderLayout;
import javax.swing.JButton;

import com.desktop.core.utils.interfaces.ComponentInterface;
import com.desktop.views.AppFrameController;

public class CloseButton extends JButton implements ComponentInterface {
  public AppFrameController controller;

  public CloseButton(AppFrameController controller) {
    this.controller = controller;
    _configureComponent();
    _listenerClicking();
  }

  public void _configureComponent() {
    this.setText("X");
    this.setForeground(java.awt.Color.decode("#FFFFFF"));
    this.setBackground(java.awt.Color.decode("#121212"));
    this.setBorderPainted(false);
    this.setFocusPainted(false);
    ((Container) controller.childComponents.get("ButtonsPanelComponent")).add(this, BorderLayout.EAST);
  }

  /**
   * Adds an ActionListener to the button that will terminate the application
   * when the button is clicked.
   */
  private void _listenerClicking() {
    this.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent e) {
        System.exit(0);
      }
    });
  }
}
