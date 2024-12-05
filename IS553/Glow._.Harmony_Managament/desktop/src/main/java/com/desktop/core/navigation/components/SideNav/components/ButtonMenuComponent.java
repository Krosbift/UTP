package com.desktop.core.navigation.components.SideNav.components;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import com.desktop.core.navigation.components.SideNav.SideNavPanelController;
import com.desktop.core.utils.interfaces.ComponentInterface;
import com.desktop.views.shared.models.ViewModel;

public class ButtonMenuComponent extends JButton implements ComponentInterface {
  private ViewModel view;
  private SideNavPanelController controller;
  private Color defaultColor = Color.decode("#eeceed");
  private Color hoverColor = Color.decode("#ffdffe");

  public ButtonMenuComponent(SideNavPanelController controller, ViewModel view) {
    this.controller = controller;
    this.view = view;
    this.setText(view.getViewName());
    this.setToolTipText(view.getDescription());
    _configureComponent();
    _addActionListener();
    _addMouseListener();
    _listenerSizing();
  }

  @Override
  public void _configureComponent() {
    this.setBackground(defaultColor);
    this.setFocusPainted(false);
    this.setBorderPainted(false);
    this.setOpaque(true);
    this.setFont(new Font(this.getFont().getName(), Font.PLAIN, 18));
    this.setAlignmentX(Component.CENTER_ALIGNMENT);
    this.setFocusPainted(false);
    this.setContentAreaFilled(false);
    this.setOpaque(true);
    controller.sideNavPanelComponent.setLayout(new BoxLayout(controller.sideNavPanelComponent, BoxLayout.Y_AXIS));
    controller.sideNavPanelComponent.add(this);
  }

  private void _addActionListener() {
    this.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        controller.onMenuItemClick(view);
      }
    });
  }

  private void _addMouseListener() {
    this.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseEntered(MouseEvent e) {
        setBackground(hoverColor);
        setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
      }

      @Override
      public void mouseExited(MouseEvent e) {
        setBackground(defaultColor);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
      }
    });
  }

  private void _listenerSizing() {
    controller.parentController.parentController.appFrame.addComponentListener(new ComponentAdapter() {
      @Override
      public void componentResized(ComponentEvent e) {
        resizeComponents();
      }
    });
    resizeComponents();
  }

  private void resizeComponents() {
    this.setMaximumSize(new Dimension(controller.sideNavPanelComponent.getWidth(), 50));
    this.setMinimumSize(new Dimension(controller.sideNavPanelComponent.getWidth(), 50));
    this.setPreferredSize(new Dimension(controller.sideNavPanelComponent.getWidth(), 50));
    this.revalidate();
    this.repaint();
  }
}
