package com.desktop.core.navigation.components.SideNav.components;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import com.desktop.core.navigation.components.SideNav.SideNavPanelController;
import com.desktop.core.utils.interfaces.ComponentInterface;

public class LogoLabelComponent extends JLabel implements ComponentInterface {
  public SideNavPanelController controller;

  public LogoLabelComponent(SideNavPanelController controller) {
    this.controller = controller;
    _configureComponent();
    _listenerSizing();
  }

  @Override
  public void _configureComponent() {
    ImageIcon logoImage = new ImageIcon(getClass().getResource("/images/glow._.harmony_logo.png"));
    this.setIcon(logoImage);
    this.setAlignmentX(Component.CENTER_ALIGNMENT);
    controller.sideNavPanelComponent.setLayout(new GridBagLayout());
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.weightx = 1.0;
    gbc.weighty = 1.0;
    gbc.anchor = GridBagConstraints.CENTER;
    gbc.fill = GridBagConstraints.BOTH;
    controller.sideNavPanelComponent.add(this, gbc);
  }

  private void _listenerSizing() {
    controller.parentController.parentController.appFrame
        .addComponentListener(new ComponentAdapter() {
          @Override
          public void componentResized(ComponentEvent e) {
            resizeComponents();
          }
        });
    resizeComponents();
  }

  private void resizeComponents() {
    int size = controller.sideNavPanelComponent.getWidth();
    this.setBounds(0, 0, size, size);
    this.revalidate();
    this.repaint();
  }
}
