package com.desktop.views.login.components.loginForm.components;

import java.awt.Font;
import java.awt.Color;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.concurrent.ExecutionException;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.UIManager;
import javax.swing.BorderFactory;
import javax.swing.plaf.ColorUIResource;
import com.desktop.core.utils.interfaces.ComponentInterface;
import com.desktop.views.login.components.loginForm.LoginFormPanelController;

public class FormInputButtonComponent extends JButton implements ComponentInterface {
  public LoginFormPanelController controller;
  public JLabel errorMessage;

  public FormInputButtonComponent(LoginFormPanelController controller) {
    this.controller = controller;
    _configureComponent();
    _listenerSizing();
    _listenerTextFields();
  }

  @Override
  public void _configureComponent() {
    this.setText("Iniciar Sesión");
    this.setFont(new Font("Arial", Font.BOLD, 14));
    this.setBackground(Color.decode("#4CAF50"));
    this.setForeground(Color.WHITE);
    this.setFocusPainted(false);
    this.setBorder(BorderFactory.createCompoundBorder(
        BorderFactory.createLineBorder(Color.decode("#4CAF50"), 2),
        BorderFactory.createEmptyBorder(5, 15, 5, 15)));
    this.setMargin(new Insets(10, 20, 10, 20));
    this.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    UIManager.put("Button.select", new ColorUIResource(Color.decode("#388E3C")));
    subComponents();
    ((SubFormPanelComponent) controller.childComponents.get("SubFormPanelComponent")).add(this);
  }

  public void subComponents() {
    errorMessage = new JLabel();
    errorMessage.setText("Correo o contraseña incorrectos");
    errorMessage.setForeground(Color.decode("#FF0000"));
    errorMessage.setFont(new Font("Arial", Font.BOLD, 14));
    errorMessage.setVisible(false);
    ((SubFormPanelComponent) controller.childComponents.get("SubFormPanelComponent")).add(errorMessage);
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
    this.setBounds(centerX - 90, centerY + 160, 180, 30);
    errorMessage.setBounds(centerX - 110, centerY + 190, 240, 30);
    this.repaint();
    this.revalidate();
  }

  /**
   * Adds an ActionListener to the FormInputButtonComponent that retrieves the
   * text
   * from the FormEmailFieldComponent and FormPasswordFieldComponent when the
   * button
   * is clicked. The email and password are then printed to the console.
   */
  private void _listenerTextFields() {
    this.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        try {
          controller.login();
        } catch (ExecutionException e1) {
          e1.printStackTrace();
        }
      }
    });
  }

  /**
   * Displays the error message by setting its visibility to true.
   */
  public void showErrorMessage() {
    errorMessage.setVisible(true);
  }

  /**
   * Hides the error message by setting its visibility to false.
   */
  public void hideErrorMessage() {
    errorMessage.setVisible(false);
  }
}
