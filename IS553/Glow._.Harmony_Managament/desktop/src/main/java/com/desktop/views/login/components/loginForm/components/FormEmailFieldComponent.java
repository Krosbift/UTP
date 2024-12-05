package com.desktop.views.login.components.loginForm.components;

import java.awt.Font;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.Color;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.BorderFactory;
import com.desktop.core.utils.interfaces.ComponentInterface;
import com.desktop.views.login.components.loginForm.LoginFormPanelController;

public class FormEmailFieldComponent extends JTextField implements ComponentInterface {
  public LoginFormPanelController controller;
  public JLabel emailLabel;

  public FormEmailFieldComponent(LoginFormPanelController controller) {
    this.controller = controller;
    _configureComponent();
    _listenerSizing();
  }

  @Override
  public void _configureComponent() {
    this.setFont(new Font("Arial", Font.PLAIN, 14));
    this.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY));
    this.setBackground(Color.WHITE);
    this.setOpaque(true);
    subComponents();
    ((SubFormPanelComponent) controller.childComponents.get("SubFormPanelComponent")).add(this);
  }

  public void subComponents() {
    JPanel panel = new JPanel(new BorderLayout());
    panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    panel.add(this, BorderLayout.CENTER);
    panel.setBackground(Color.WHITE);
    panel.setOpaque(true);

    emailLabel = new JLabel();
    emailLabel.setText("Correo: ");
    emailLabel.setHorizontalAlignment(SwingConstants.CENTER);
    emailLabel.setFont(new Font("Arial", Font.BOLD, 14));
    ((SubFormPanelComponent) controller.childComponents.get("SubFormPanelComponent")).add(emailLabel);
  }

  /**
   * Adds a component listener to a child component that triggers the
   * resizeComponents method
   * whenever the component is resized.
   */
  private void _listenerSizing() {
    ((SubFormPanelComponent) controller.childComponents.get("SubFormPanelComponent")).addComponentListener(new ComponentAdapter() {
      @Override
      public void componentResized(ComponentEvent e) {
        resizeComponents();
      }
    });
  }

  /**
   * Adjusts the size and position of the components within the login form panel.
   * This method centers the component within the login form panel by calculating
   * the center coordinates and setting the bounds accordingly.
   */
  private void resizeComponents() {
    int centerX = ((SubFormPanelComponent) controller.childComponents.get("SubFormPanelComponent")).getWidth() / 2;
    int centerY = ((SubFormPanelComponent) controller.childComponents.get("SubFormPanelComponent")).getHeight() / 2;
    emailLabel.setBounds(centerX - 100, centerY, 200, 30);
    this.setBounds(centerX - 100, centerY + 30, 200, 30);
    this.repaint();
    this.revalidate();
  }
}
