package com.desktop.core.navigation.components.ToolBar.components;

import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import com.desktop.core.navigation.components.ToolBar.ToolBarPanelController;
import com.desktop.core.utils.interfaces.ComponentInterface;

public class UserNameTitleLabelComponent extends JLabel implements ComponentInterface {
  public ToolBarPanelController controller;

  public UserNameTitleLabelComponent(ToolBarPanelController controller) {
    this.controller = controller;
    _configureComponent();
    _listenerSizing();
  }

  @Override
  public void _configureComponent() {
    this.setBorder(new EmptyBorder(0, 10, 0, 0));
    this.setHorizontalAlignment(JLabel.LEFT);
    this.setVerticalAlignment(JLabel.CENTER);
    this.setBounds(10, 10, 400, controller.toolBarPanelComponent.getHeight() - 20);
    this.setFont(new Font("Arial", Font.PLAIN, 16));

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
    this.setBounds(10, 10, 400, controller.toolBarPanelComponent.getHeight() - 20);
    this.repaint();
    this.revalidate();
  }
}
