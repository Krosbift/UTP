package com.desktop.views.login.components.loginForm;

import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.awt.Color;
import java.util.HashMap;
import javax.swing.BorderFactory;
import com.desktop.views.login.LoginPanelController;
import com.desktop.views.login.components.loginForm.components.SubFormPanelComponent;
import com.desktop.views.shared.models.user.UserModel;
import com.desktop.views.login.components.loginForm.components.FormLogoLabelComponent;
import com.desktop.views.login.components.loginForm.components.FormEmailFieldComponent;
import com.desktop.views.login.components.loginForm.components.FormInputButtonComponent;
import com.desktop.views.login.components.loginForm.components.FormPasswordFieldComponent;
import com.desktop.core.utils.interfaces.ComponentInterface;
import com.desktop.core.utils.interfaces.ControllerInterface;

public class LoginFormPanelController implements ControllerInterface {
  public LoginPanelController parentController;
  public LoginFormPanelComponent loginFormPanelComponent;
  public Map<String, ControllerInterface> childControllers;
  public Map<String, ComponentInterface> childComponents;

  public LoginFormPanelController(LoginPanelController controller) {
    this.parentController = controller;
    _initComponent();
    _initChildComponents();
    _initChildControllers();
  }

  @Override
  public void _initComponent() {
    loginFormPanelComponent = new LoginFormPanelComponent(this);
  }

  @Override
  public void _initChildControllers() {

  }

  public void _initChildComponents() {
    childComponents = new HashMap<>();
    childComponents.put("SubFormPanelComponent", new SubFormPanelComponent(this));
    childComponents.put("FormLogoLabelComponent", new FormLogoLabelComponent(this));
    childComponents.put("FormEmailFieldComponent", new FormEmailFieldComponent(this));
    childComponents.put("FormPasswordFieldComponent", new FormPasswordFieldComponent(this));
    childComponents.put("FormInputButtonComponent", new FormInputButtonComponent(this));
  }

  /**
   * Attempts to log in the user using the provided email and password.
   * Retrieves the email and password from the respective form components,
   * then calls the login service to authenticate the user.
   * Updates the login state based on the success of the authentication.
   *
   * @throws ExecutionException if an error occurs during the login process.
   */
  public void login() throws ExecutionException {
    String email = ((FormEmailFieldComponent) childComponents.get("FormEmailFieldComponent")).getText();
    String password = new String(((FormPasswordFieldComponent) childComponents.get("FormPasswordFieldComponent")).getPassword());
    UserModel user = parentController.loginService.login(email, password);
    if (stateLogin(user != null)) {
      parentController.parentController.switchComponent(user, parentController);
    }
  }

  /**
   * Updates the login form UI based on the login state.
   *
   * @param state A boolean indicating the login state.
   *              If true, the login is successful and the UI will reflect a
   *              successful login.
   *              If false, the login failed and the UI will reflect an error
   *              state.
   */
  public boolean stateLogin(boolean state) {
    if (state) {
      ((FormInputButtonComponent) childComponents.get("FormInputButtonComponent")).errorMessage
          .setText("¡Inicio de sesión exitoso!");
      ((FormInputButtonComponent) childComponents.get("FormInputButtonComponent")).errorMessage
          .setForeground(Color.decode("#00ff00"));
      ((FormEmailFieldComponent) childComponents.get("FormEmailFieldComponent")).emailLabel
          .setForeground(Color.decode("#000000"));
      ((FormEmailFieldComponent) childComponents.get("FormEmailFieldComponent"))
          .setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY));
      ((FormPasswordFieldComponent) childComponents.get("FormPasswordFieldComponent")).passwordLabel
          .setForeground(Color.decode("#000000"));
      ((FormPasswordFieldComponent) childComponents.get("FormPasswordFieldComponent"))
          .setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY));
    } else {
      ((FormInputButtonComponent) childComponents.get("FormInputButtonComponent")).errorMessage
          .setText("Correo o contraseña incorrecta.");
      ((FormInputButtonComponent) childComponents.get("FormInputButtonComponent")).errorMessage
          .setForeground(Color.decode("#ff0000"));
      ((FormInputButtonComponent) childComponents.get("FormInputButtonComponent")).errorMessage.setVisible(true);
      ((FormEmailFieldComponent) childComponents.get("FormEmailFieldComponent")).emailLabel
          .setForeground(Color.decode("#ff0000"));
      ((FormEmailFieldComponent) childComponents.get("FormEmailFieldComponent"))
          .setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.decode("#ff0000")));
      ((FormPasswordFieldComponent) childComponents.get("FormPasswordFieldComponent")).passwordLabel
          .setForeground(Color.decode("#ff0000"));
      ((FormPasswordFieldComponent) childComponents.get("FormPasswordFieldComponent"))
          .setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.decode("#ff0000")));
    }
    return state;
  }
}
