package com.desktop.views.suppliers.components;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Font;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import com.desktop.core.utils.interfaces.ComponentInterface;
import com.desktop.views.suppliers.SuppliersPanelController;
import com.desktop.views.suppliers.model.GetSupplierDto;

public class TopPanelComponent extends JPanel implements ComponentInterface {
  public String title;
  public SuppliersPanelController controller;
  private JLabel titleLabel;
  private JPanel buttonPanel;
  private JButton button1;
  private JButton button2;

  public TopPanelComponent(SuppliersPanelController controller, String title) {
    this.controller = controller;
    this.title = title;
    _configureComponent();
    addChildComponents();
    _listernerSizing();
    addEventListeners();
  }

  @Override
  public void _configureComponent() {
    this.setLayout(new GridBagLayout());
    this.setBackground(controller.suppliersComponent.getBackground());
    controller.suppliersComponent.add(this);
  }

  private void addChildComponents() {
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.insets = new Insets(5, 5, 5, 5);
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.anchor = GridBagConstraints.WEST;
    gbc.weightx = 1.0;
    gbc.fill = GridBagConstraints.HORIZONTAL;

    titleLabel = new JLabel(title.toUpperCase());
    titleLabel.setFont(new Font(titleLabel.getFont().getName(), Font.PLAIN, 20));
    this.add(titleLabel, gbc);

    gbc.gridx = 1;
    gbc.anchor = GridBagConstraints.EAST;
    gbc.weightx = 0;
    buttonPanel = new JPanel();
    buttonPanel.setBackground(controller.suppliersComponent.getBackground());
    button2 = new JButton("Vista de datos");

    if (controller.parentController.user.getRoleType().getRoleTypeId() == 1) {
      button1 = new JButton("Vista Completa");
      buttonPanel.add(button1);
    }

    buttonPanel.add(button2);
    this.add(buttonPanel, gbc);
  }

  private void _listernerSizing() {
    controller.parentController.contentPanelComponent.addComponentListener(new ComponentAdapter() {
      @Override
      public void componentResized(ComponentEvent e) {
        resizeComponents();
      }
    });
    resizeComponents();
  }

  private void resizeComponents() {
    this.setBounds(0, 0, controller.suppliersComponent.getWidth(),
        (int) (controller.suppliersComponent.getHeight() * 0.1));
    this.revalidate();
    this.repaint();
  }

  @SuppressWarnings("unused")
  private void addEventListeners() {
    if (controller.parentController.user.getRoleType().getRoleTypeId() == 1) {
      button1.addActionListener(e -> {

      });
    }

    button2.addActionListener(e -> {
      showDialog();
    });
  }

  private void showDialog() {
    JTextField phoneField = new JTextField(20);

    JPanel panel = new JPanel(new GridBagLayout());
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.insets = new Insets(5, 5, 5, 5);
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.anchor = GridBagConstraints.WEST;

    panel.add(new JLabel("Teléfono del proveedor:"), gbc);
    gbc.gridx++;
    panel.add(phoneField, gbc);

    int result = JOptionPane.showConfirmDialog(null, panel, "Enter Supplier Details", JOptionPane.OK_CANCEL_OPTION,
        JOptionPane.PLAIN_MESSAGE);
    if (result == JOptionPane.OK_OPTION) {
      GetSupplierDto dto = new GetSupplierDto()
          .setPhone(phoneField.getText().isEmpty() ? null : phoneField.getText())
          .build();

      controller.setDataTable(dto);
    }
  }
}