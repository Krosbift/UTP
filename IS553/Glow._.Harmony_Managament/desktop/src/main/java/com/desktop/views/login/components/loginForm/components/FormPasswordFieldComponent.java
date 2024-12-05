package com.desktop.views.login.components.loginForm.components;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.BorderFactory;
import javax.swing.SwingConstants;
import com.desktop.core.utils.interfaces.ComponentInterface;
import com.desktop.views.login.components.loginForm.LoginFormPanelController;

public class FormPasswordFieldComponent extends JPasswordField implements ComponentInterface {
  public LoginFormPanelController controller;
  public JLabel passwordLabel;

  public FormPasswordFieldComponent(LoginFormPanelController controller) {
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

    passwordLabel = new JLabel();
    passwordLabel.setText("Contraseña: ");
    passwordLabel.setHorizontalAlignment(SwingConstants.CENTER);
    passwordLabel.setFont(new Font("Arial", Font.BOLD, 14));
    ((SubFormPanelComponent) controller.childComponents.get("SubFormPanelComponent")).add(passwordLabel);
  }

  /**
   * Adds a component listener to a child component that triggers the
   * resizeComponents method
   * whenever the component is resized.
   */
  private void _listenerSizing() {
    ((SubFormPanelComponent) controller.childComponents.get("SubFormPanelComponent"))
        .addComponentListener(new ComponentAdapter() {
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
    passwordLabel.setBounds(centerX - 100, centerY + 70, 200, 30);
    this.setBounds(centerX - 100, centerY + 100, 200, 30);
    this.repaint();
    this.revalidate();
  }
}
