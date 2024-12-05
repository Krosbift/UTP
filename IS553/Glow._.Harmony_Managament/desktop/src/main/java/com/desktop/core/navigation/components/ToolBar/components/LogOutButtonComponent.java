package com.desktop.core.navigation.components.ToolBar.components;

import javax.swing.JButton;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Color;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import com.desktop.core.navigation.components.ToolBar.ToolBarPanelController;
import com.desktop.core.utils.interfaces.ComponentInterface;

public class LogOutButtonComponent extends JButton implements ComponentInterface {
  public ToolBarPanelController controller;

  public LogOutButtonComponent(ToolBarPanelController controller) {
    this.controller = controller;
    _configureComponent();
    _listenerSizing();
    _listenerHind();
    _listenerAction();
  }

  @Override
  public void _configureComponent() {
    this.setText("Cerrar sesión");
    this.setFont(new Font("Arial", Font.BOLD, 14));
    this.setMargin(new Insets(5, 10, 5, 10));
    this.setBorder(new EmptyBorder(0, 0, 0, 10));
    this.setBounds(controller.toolBarPanelComponent.getWidth() - 160, 10, 150,
        controller.toolBarPanelComponent.getHeight() - 20);
    this.setVerticalAlignment(JButton.CENTER);
    this.setAlignmentX(RIGHT_ALIGNMENT);
    this.setBackground(Color.decode("#F0F0F0"));
    this.setForeground(Color.BLACK);
    this.setFocusPainted(false);
    this.setContentAreaFilled(false);
    this.setOpaque(true);
    controller.toolBarPanelComponent.add(this);
  }

  private void _listenerSizing() {
    controller.toolBarPanelComponent.addComponentListener(new ComponentAdapter() {
      @Override
      public void componentResized(ComponentEvent e) {
        _resizeComponent();
      }
    });
  }

  private void _resizeComponent() {
    this.setBounds(controller.toolBarPanelComponent.getWidth() - 160, 10, 150,
        controller.toolBarPanelComponent.getHeight() - 20);
    this.repaint();
    this.revalidate();
  }

  private void _listenerHind() {
    this.addMouseListener(new java.awt.event.MouseAdapter() {
      @Override
      public void mouseEntered(java.awt.event.MouseEvent evt) {
        setBackground(Color.decode("#FFE4E1"));
        setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
      }

      @Override
      public void mouseExited(java.awt.event.MouseEvent evt) {
        setBackground(Color.decode("#F0F0F0"));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
      }
    });
  }

  @SuppressWarnings("unused")
  private void _listenerAction() {
    this.addActionListener(e -> {
      controller.parentController.logOut();
    });
  }

}
