package com.desktop.views.users.components;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import com.desktop.core.utils.interfaces.ComponentInterface;
import com.desktop.views.shared.models.index.DocumentTypeModel;
import com.desktop.views.shared.models.index.RoleTypeModel;
import com.desktop.views.users.UsersPanelController;
import com.desktop.views.users.model.RegisterUserDto;

public class RegisterUserComponent extends JPanel implements ComponentInterface {
  public UsersPanelController controller;
  private JTextField namesField;
  private JTextField surNamesField;
  private JTextField documentNumberField;
  private JTextField emailField;
  private JPasswordField passwordField;
  private JTextField phoneField;
  private JTextField addressField;
  private JButton registerButton;

  public RegisterUserComponent(UsersPanelController controller, String title) {
    this.controller = controller;
    _configureComponent();
    _listenerSizing();
  }

  @Override
  public void _configureComponent() {
    this.setLayout(new BorderLayout());
    this.setBounds(0, 0, controller.usersPanelComponent.getWidth(), controller.usersPanelComponent.getHeight());
    this.setBackground(controller.usersPanelComponent.getBackground());
    controller.usersPanelComponent.add(this);

    JLabel titleLabel = new JLabel("Registrar un Usuario en el Sistema", JLabel.CENTER);
    titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
    this.add(titleLabel, BorderLayout.NORTH);

    JPanel fieldsPanel = new JPanel(new GridLayout(10, 2, 10, 10));
    fieldsPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
    fieldsPanel.setBackground(controller.usersPanelComponent.getBackground());

    fieldsPanel.add(new JLabel("Names:"));
    namesField = new JTextField();
    fieldsPanel.add(namesField);

    fieldsPanel.add(new JLabel("Surnames:"));
    surNamesField = new JTextField();
    fieldsPanel.add(surNamesField);

    fieldsPanel.add(new JLabel("Document Type:"));
    JComboBox<String> documentTypeIdField = new JComboBox<>();
    for (DocumentTypeModel documentType : controller.documentTypes) {
      documentTypeIdField.addItem(documentType.getDocumentType());
    }
    fieldsPanel.add(documentTypeIdField);

    fieldsPanel.add(new JLabel("Document Number:"));
    documentNumberField = new JTextField();
    fieldsPanel.add(documentNumberField);

    fieldsPanel.add(new JLabel("Email:"));
    emailField = new JTextField();
    fieldsPanel.add(emailField);

    fieldsPanel.add(new JLabel("Password:"));
    passwordField = new JPasswordField();
    fieldsPanel.add(passwordField);

    fieldsPanel.add(new JLabel("Phone:"));
    phoneField = new JTextField();
    fieldsPanel.add(phoneField);

    fieldsPanel.add(new JLabel("Role Type:"));
    JComboBox<String> roleTypeIdField = new JComboBox<>();
    for (RoleTypeModel role : controller.roleTypes) {
      roleTypeIdField.addItem(role.getRoleType());
    }
    fieldsPanel.add(roleTypeIdField);

    fieldsPanel.add(new JLabel("Address:"));
    addressField = new JTextField();
    fieldsPanel.add(addressField);

    registerButton = new JButton("Register");
    fieldsPanel.add(registerButton);

    this.add(fieldsPanel, BorderLayout.CENTER);

    registerButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        if (namesField.getText().isEmpty() || surNamesField.getText().isEmpty() ||
            documentTypeIdField.getSelectedItem() == null || documentNumberField.getText().isEmpty() ||
            emailField.getText().isEmpty() || passwordField.getPassword().length == 0 ||
            phoneField.getText().isEmpty() || roleTypeIdField.getSelectedItem() == null ||
            addressField.getText().isEmpty()) {
          JOptionPane.showMessageDialog(RegisterUserComponent.this, "Todos los campos son obligatorios.", "Error",
              JOptionPane.ERROR_MESSAGE);
          return;
        }

        RegisterUserDto user = new RegisterUserDto()
            .setNames(namesField.getText())
            .setSurNames(surNamesField.getText())
            .setDocumentTypeId(controller.documentTypes.get(documentTypeIdField.getSelectedIndex()).getDocumentTypeId())
            .setDocumentNumber(documentNumberField.getText())
            .setEmail(emailField.getText())
            .setPassword(new String(passwordField.getPassword()))
            .setPhone(phoneField.getText())
            .setRoleTypeId(controller.roleTypes.get(roleTypeIdField.getSelectedIndex()).getRoleTypeId())
            .setAddress(addressField.getText());

        try {
          controller.userService.registerUser(user);
          JOptionPane.showMessageDialog(RegisterUserComponent.this, "Usuario creado correctamente.", "Éxito",
              JOptionPane.INFORMATION_MESSAGE);
          clearForm();
        } catch (Exception ex) {
          JOptionPane.showMessageDialog(RegisterUserComponent.this, "Error al crear el usuario: " + ex.getMessage(),
              "Error",
              JOptionPane.ERROR_MESSAGE);
        }
      }
    });

  }

  private void clearForm() {
    namesField.setText("");
    surNamesField.setText("");
    documentNumberField.setText("");
    emailField.setText("");
    passwordField.setText("");
    phoneField.setText("");
    addressField.setText("");
  }

  private void _listenerSizing() {
    controller.parentController.contentPanelComponent.addComponentListener(new ComponentAdapter() {
      @Override
      public void componentResized(ComponentEvent e) {
        resizeComponents();
      }
    });
    resizeComponents();
  }

  private void resizeComponents() {
    this.setBounds(0, (int) (controller.usersPanelComponent.getHeight() * 0.1),
        controller.usersPanelComponent.getWidth(),
        (int) (controller.usersPanelComponent.getHeight() * 0.9));
    this.revalidate();
    this.repaint();
  }
}
