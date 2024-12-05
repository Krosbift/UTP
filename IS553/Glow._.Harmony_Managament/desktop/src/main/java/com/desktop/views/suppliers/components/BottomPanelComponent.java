package com.desktop.views.suppliers.components;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import com.desktop.core.utils.interfaces.ComponentInterface;
import com.desktop.views.shared.models.SupplierModel;
import com.desktop.views.suppliers.SuppliersPanelController;
import com.desktop.views.suppliers.model.CreateSupplierDto;
import com.desktop.views.suppliers.model.UpdateSupplierDto;

public class BottomPanelComponent extends JPanel implements ComponentInterface {
  private SuppliersPanelController controller;
  private List<SupplierModel> suppliers;
  private JDialog supplierDialog;

  public BottomPanelComponent(SuppliersPanelController controller) {
    this.controller = controller;
    _configureComponent();
    _listenerSizing();
  }

  @Override
  public void _configureComponent() {
    this.setLayout(new BorderLayout());
    this.setBounds(0, (int) (controller.suppliersComponent.getHeight() * 0.1), controller.suppliersComponent.getWidth(),
        (int) (controller.suppliersComponent.getHeight() * 0.9));
    this.setBackground(controller.suppliersComponent.getBackground());
    controller.suppliersComponent.add(this);
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
    this.setBounds(0, (int) (controller.suppliersComponent.getHeight() * 0.1), controller.suppliersComponent.getWidth(),
        (int) (controller.suppliersComponent.getHeight() * 0.9));
    this.revalidate();
    this.repaint();
  }

  public void createTable(List<SupplierModel> suppliers) {
    this.removeAll();
    this.suppliers = suppliers;
    
    addCreateButtonRow();

    String[] columnNames = { "ID", "Nombre", "Dirección", "Teléfono" };
    DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0) {
      @Override
      public boolean isCellEditable(int row, int column) {
        return false;
      }
    };

    for (SupplierModel supplier : suppliers) {
      Object[] rowData = {
          supplier.getSupplierId(),
          supplier.getName(),
          supplier.getAddress(),
          supplier.getPhone()
      };
      tableModel.addRow(rowData);
    }

    JTable table = new JTable(tableModel);
    table.setRowHeight(40);
    table.setBackground(controller.suppliersComponent.getBackground());
    table.getTableHeader().setBackground(controller.suppliersComponent.getBackground());

    table.getColumnModel().getColumn(0).setMinWidth(0);
    table.getColumnModel().getColumn(0).setMaxWidth(0);
    table.getColumnModel().getColumn(0).setWidth(0);

    if (controller.parentController.user.getRoleType().getRoleTypeId() == 1) {
      table.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
          int row = table.getSelectedRow();
          if (row != -1) {
            int supplierId = (int) table.getValueAt(row, 0);
            SupplierModel selectedSupplier = suppliers.stream().filter(s -> s.getSupplierId() == supplierId)
                .findFirst().get();
            showSupplierDialog(selectedSupplier);
          }
        }
      });
    }

    JScrollPane scrollPane = new JScrollPane(table);
    scrollPane.setPreferredSize(new Dimension(this.getWidth() - 40, this.getHeight() - 80));
    scrollPane.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
    this.add(scrollPane, BorderLayout.CENTER);

    this.revalidate();
    this.repaint();
  }

  @SuppressWarnings("unused")
  private void showSupplierDialog(SupplierModel supplier) {
    if (supplierDialog != null) {
      supplierDialog.dispose();
    }

    supplierDialog = new JDialog();
    supplierDialog.setTitle("Detalles del proveedor");
    supplierDialog.setLayout(new BorderLayout());
    supplierDialog.setSize(400, 300);
    supplierDialog.setLocationRelativeTo(this);

    JPanel fieldsPanel = new JPanel(new GridLayout(4, 2, 10, 10));
    fieldsPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
    fieldsPanel.setBackground(controller.suppliersComponent.getBackground());

    fieldsPanel.add(new JLabel("Nombre:"));
    JTextField nameField = new JTextField(supplier.getName());
    fieldsPanel.add(nameField);

    fieldsPanel.add(new JLabel("Dirección:"));
    JTextField addressField = new JTextField(supplier.getAddress());
    fieldsPanel.add(addressField);

    fieldsPanel.add(new JLabel("Teléfono:"));
    JTextField phoneField = new JTextField(supplier.getPhone());
    fieldsPanel.add(phoneField);

    supplierDialog.add(fieldsPanel, BorderLayout.CENTER);

    JPanel buttonsPanel = new JPanel();
    buttonsPanel.setBackground(controller.suppliersComponent.getBackground());
    JButton editButton = new JButton("Editar");
    JButton deleteButton = new JButton("Eliminar");

    editButton.addActionListener(e -> {
      UpdateSupplierDto updatedSupplier = (UpdateSupplierDto) new UpdateSupplierDto()
          .setName(nameField.getText())
          .setAddress(addressField.getText())
          .setPhone(phoneField.getText())
          .build();

      SupplierModel result = controller.updateSupplier(updatedSupplier, supplier.getSupplierId());
      if (result != null) {
        suppliers.set(suppliers.indexOf(supplier), result);
        updateTable(null);
      }
      supplierDialog.dispose();
    });

    deleteButton.addActionListener(e -> {
      int result = JOptionPane.showConfirmDialog(this, "¿Estás seguro de eliminar este proveedor?",
          "Eliminar Proveedor",
          JOptionPane.YES_NO_OPTION);
      if (result == JOptionPane.YES_OPTION) {
        Integer deletedSupplierId = controller.deleteSupplier(supplier.getSupplierId());
        if (deletedSupplierId != null) {
          suppliers.remove(supplier);
          updateTable(null);
        }
      }
      supplierDialog.dispose();
    });

    buttonsPanel.add(editButton);
    buttonsPanel.add(deleteButton);
    supplierDialog.add(buttonsPanel, BorderLayout.SOUTH);

    supplierDialog.addWindowListener(new java.awt.event.WindowAdapter() {
      @Override
      public void windowClosing(java.awt.event.WindowEvent windowEvent) {
        supplierDialog = null;
      }
    });

    supplierDialog.setVisible(true);
  }

  @SuppressWarnings("unused")
  public void addCreateButtonRow() {
    JButton createButton = new JButton("Añadir nuevo proveedor");
    createButton.addActionListener(e -> showCreateSupplierDialog());

    JPanel buttonPanel = new JPanel(new BorderLayout());
    buttonPanel.add(createButton, BorderLayout.CENTER);
    this.add(buttonPanel, BorderLayout.NORTH);
  }

  @SuppressWarnings("unused")
  private void showCreateSupplierDialog() {
    JDialog createDialog = new JDialog();
    createDialog.setTitle("Crear nuevo proveedor");
    createDialog.setLayout(new BorderLayout());
    createDialog.setSize(400, 300);
    createDialog.setLocationRelativeTo(this);

    JPanel fieldsPanel = new JPanel(new GridLayout(4, 2, 10, 10));
    fieldsPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
    fieldsPanel.setBackground(controller.suppliersComponent.getBackground());

    fieldsPanel.add(new JLabel("Nombre:"));
    JTextField nameField = new JTextField();
    fieldsPanel.add(nameField);

    fieldsPanel.add(new JLabel("Dirección:"));
    JTextField addressField = new JTextField();
    fieldsPanel.add(addressField);

    fieldsPanel.add(new JLabel("Teléfono:"));
    JTextField phoneField = new JTextField();
    fieldsPanel.add(phoneField);

    createDialog.add(fieldsPanel, BorderLayout.CENTER);

    JPanel buttonsPanel = new JPanel();
    buttonsPanel.setBackground(controller.suppliersComponent.getBackground());
    JButton saveButton = new JButton("Guardar");
    JButton cancelButton = new JButton("Cancelar");

    saveButton.addActionListener(e -> {
      if (validateCreateSupplierFields(nameField, addressField, phoneField)) {
        CreateSupplierDto newSupplier = new CreateSupplierDto()
            .setName(nameField.getText())
            .setAddress(addressField.getText())
            .setPhone(phoneField.getText())
            .build();

        SupplierModel createdSupplier = controller.creaSupplier(newSupplier);
        if (createdSupplier != null) {
          updateTable(createdSupplier);
        }
        createDialog.dispose();
      }
    });

    cancelButton.addActionListener(e -> createDialog.dispose());

    buttonsPanel.add(saveButton);
    buttonsPanel.add(cancelButton);
    createDialog.add(buttonsPanel, BorderLayout.SOUTH);

    createDialog.setVisible(true);
  }

  private boolean validateCreateSupplierFields(JTextField nameField, JTextField addressField, JTextField phoneField) {
    if (nameField.getText().trim().isEmpty()) {
      JOptionPane.showMessageDialog(this, "El nombre del proveedor no debe estar vacío", "Error",
          JOptionPane.ERROR_MESSAGE);
      return false;
    }
    if (addressField.getText().trim().isEmpty()) {
      JOptionPane.showMessageDialog(this, "La dirección del proveedor no debe estar vacía", "Error",
          JOptionPane.ERROR_MESSAGE);
      return false;
    }
    if (phoneField.getText().trim().isEmpty()) {
      JOptionPane.showMessageDialog(this, "El teléfono del proveedor no debe estar vacío", "Error",
          JOptionPane.ERROR_MESSAGE);
      return false;
    }
    return true;
  }

  public void updateTable(SupplierModel newSupplier) {
    if (newSupplier != null) {
      suppliers.add(newSupplier);
    }
    createTable(suppliers);
  }
}
