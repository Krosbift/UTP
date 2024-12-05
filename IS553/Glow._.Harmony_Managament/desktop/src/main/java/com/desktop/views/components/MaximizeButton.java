package com.desktop.views.components;

import java.awt.Color;
import java.awt.Container;
import java.awt.BorderLayout;
import javax.swing.JButton;

import com.desktop.core.utils.interfaces.ComponentInterface;
import com.desktop.views.AppFrameController;

public class MaximizeButton extends JButton implements ComponentInterface {
  private AppFrameController controller;

  public MaximizeButton(AppFrameController controller) {
    this.controller = controller;
    _configureComponent();
    _listenerClicking();
  }

  public void _configureComponent() {
    this.setText("⬜");
    this.setForeground(Color.decode("#FFFFFF"));
    this.setBackground(Color.decode("#121212"));
    this.setBorderPainted(false);
    this.setFocusPainted(false);
    ((Container) controller.childComponents.get("ButtonsPanelComponent")).add(this, BorderLayout.CENTER);
  }

  /**
   * Adds an ActionListener to the button that toggles the extended state of the application frame.
   * When the button is clicked, the frame's state is toggled between normal (0) and maximized (6).
   */
  private void _listenerClicking() {
    this.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent e) {
        controller.appFrame.setExtendedState(controller.appFrame.getExtendedState() == 0 ? 6 : 0);
      }
    });
  }
}
