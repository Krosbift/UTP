package com.desktop.views.IntsOuts.components;

import javax.swing.JPanel;
import com.desktop.core.utils.interfaces.ComponentInterface;
import com.desktop.views.IntsOuts.IntsOutsPanelController;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import com.desktop.views.IntsOuts.model.UpdateProductUpdateDto;
import com.desktop.views.shared.models.index.TransactionTypeModel;
import com.desktop.views.shared.models.inventory.ProductMovementModel;
import com.desktop.views.shared.models.product.ProductModel;
import com.desktop.views.IntsOuts.model.CreateUpdateProductDto;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

public class BottomPanelComponent extends JPanel implements ComponentInterface {
  public IntsOutsPanelController controller;
  public List<ProductMovementModel> originalIntsOuts;
  private JDialog intsOutsDialog;

  public BottomPanelComponent(IntsOutsPanelController controller) {
    this.controller = controller;
    _configureComponent();
    _listenerSizing();
  }

  @Override
  public void _configureComponent() {
    this.setLayout(new BorderLayout());
    this.setBounds(0, (int) (controller.intsOutsComponent.getHeight() * 0.1), controller.intsOutsComponent.getWidth(),
        (int) (controller.intsOutsComponent.getHeight() * 0.9));
    this.setBackground(controller.intsOutsComponent.getBackground());
    controller.intsOutsComponent.add(this);
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
    this.setBounds(0, (int) (controller.intsOutsComponent.getHeight() * 0.1), controller.intsOutsComponent.getWidth(),
        (int) (controller.intsOutsComponent.getHeight() * 0.9));
    this.revalidate();
    this.repaint();
  }

  public void createTable(List<ProductMovementModel> movements) {
    this.removeAll();
    this.originalIntsOuts = movements;

    String[] columnNames = { "ID", "Producto", "Razón", "Fecha", "Cantidad", "Costo", "Tipo de Transacción",
        "Fecha de expiración" };
    DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0) {
      @Override
      public boolean isCellEditable(int row, int column) {
        return false;
      }
    };

    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    for (ProductMovementModel movement : movements) {
      String formattedUpdateDate = movement.getUpdateDate() != null
          ? dateFormat.format(movement.getUpdateDate())
          : "";
      String formattedExpirationDate = movement.getExpirationDate() != null
          ? dateFormat.format(movement.getExpirationDate())
          : "";

      Object[] rowData = {
          movement.getUpdateProductId(),
          movement.getProductModel().getProductName(),
          movement.getReason(),
          formattedUpdateDate,
          movement.getUpdateAmount(),
          movement.getProductPrice(),
          movement.getTransactionTypeModel().getTransactionType(),
          formattedExpirationDate
      };
      tableModel.addRow(rowData);
    }

    JTable table = new JTable(tableModel);
    table.setRowHeight(40);
    table.setBackground(controller.intsOutsComponent.getBackground());
    table.getTableHeader().setBackground(controller.intsOutsComponent.getBackground());

    table.getColumnModel().getColumn(0).setMinWidth(0);
    table.getColumnModel().getColumn(0).setMaxWidth(0);
    table.getColumnModel().getColumn(0).setWidth(0);
    table.getColumnModel().getColumn(2).setPreferredWidth(200);

    if (controller.parentController.user.getRoleType().getRoleTypeId() == 1) {
      table.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
          int row = table.getSelectedRow();
          if (row != -1) {
            int updateProductId = (int) table.getValueAt(row, 0);
            ProductMovementModel selectedIntsOut = originalIntsOuts.stream()
                .filter(p -> p.getUpdateProductId() == updateProductId)
                .findFirst().get();
            showIntsOutsDialog(selectedIntsOut);
          }
        }
      });
    }

    JScrollPane scrollPane = new JScrollPane(table);
    scrollPane.setPreferredSize(new Dimension(this.getWidth() - 40, this.getHeight() - 80));
    scrollPane.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
    this.add(scrollPane, BorderLayout.CENTER);

    addCreateButtonRow();

    this.revalidate();
    this.repaint();
  }

  @SuppressWarnings("unused")
  private void showIntsOutsDialog(ProductMovementModel movements) {
    if (intsOutsDialog != null) {
      intsOutsDialog.dispose();
    }

    intsOutsDialog = new JDialog();
    intsOutsDialog.setTitle("Detalles de la entrada/salida");
    intsOutsDialog.setLayout(new BorderLayout());
    intsOutsDialog.setSize(400, 300);
    intsOutsDialog.setLocationRelativeTo(this);

    JPanel fieldsPanel = new JPanel(new GridLayout(6, 2, 10, 10));
    fieldsPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
    fieldsPanel.setBackground(controller.intsOutsComponent.getBackground());

    fieldsPanel.add(new JLabel("Producto:"));
    JComboBox<String> productNameComboBox = new JComboBox<>();
    for (ProductModel product : controller.productNames) {
      productNameComboBox.addItem(product.getProductName());
    }
    productNameComboBox.setSelectedItem(movements.getProductModel().getProductName());
    fieldsPanel.add(productNameComboBox);

    fieldsPanel.add(new JLabel("Razón:"));
    JTextField reasonField = new JTextField(movements.getReason());
    fieldsPanel.add(reasonField);

    fieldsPanel.add(new JLabel("Cantidad:"));
    JTextField amountField = new JTextField(String.valueOf(movements.getUpdateAmount()));
    fieldsPanel.add(amountField);

    fieldsPanel.add(new JLabel("Costo:"));
    JTextField costField = new JTextField(String.valueOf(movements.getProductPrice()));
    fieldsPanel.add(costField);

    fieldsPanel.add(new JLabel("Tipo de Transacción:"));
    JComboBox<String> transactionTypeComboBox = new JComboBox<>();
    for (TransactionTypeModel transactionType : controller.transactionTypes) {
      transactionTypeComboBox.addItem(transactionType.getTransactionType());
    }
    transactionTypeComboBox.setSelectedItem(movements.getTransactionTypeModel().getTransactionType());
    fieldsPanel.add(transactionTypeComboBox);

    fieldsPanel.add(new JLabel("Fecha de Expiración:"));
    UtilDateModel model = new UtilDateModel();
    if (movements.getExpirationDate() == null) {
      model.setValue(new java.util.Date());
    }
    if (movements.getExpirationDate() != null) {
      model.setValue(movements.getExpirationDate());
    }
    Properties p = new Properties();
    p.put("text.today", "Today");
    p.put("text.month", "Month");
    p.put("text.year", "Year");
    JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
    JDatePickerImpl expirationDatePicker = new JDatePickerImpl(datePanel, null);
    fieldsPanel.add(expirationDatePicker);

    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    expirationDatePicker.addActionListener(e -> {
      Date selectedDate = (Date) expirationDatePicker.getModel().getValue();
      if (selectedDate != null) {
        String formattedDate = dateFormat.format(selectedDate);
        expirationDatePicker.getJFormattedTextField().setText(formattedDate);
      }
    });

    intsOutsDialog.add(fieldsPanel, BorderLayout.CENTER);

    JPanel buttonsPanel = new JPanel();
    buttonsPanel.setBackground(controller.intsOutsComponent.getBackground());
    JButton editButton = new JButton("Editar");
    JButton deleteButton = new JButton("Eliminar");

    editButton.addActionListener(e -> {
      Calendar calendar = Calendar.getInstance();
      Date expirationDate = (Date) expirationDatePicker.getModel().getValue();
      if (expirationDate != null) {
        calendar.setTime(expirationDate);
        calendar.add(Calendar.DAY_OF_MONTH, 1);
      }
      Date newExpirationDate = expirationDate != null ? calendar.getTime() : null;
      UpdateProductUpdateDto updatedIntsOut = (UpdateProductUpdateDto) new UpdateProductUpdateDto()
          .setProductId(controller.productNames.get(productNameComboBox.getSelectedIndex()).getProductId())
          .setReason(reasonField.getText())
          .setUpdateAmount(Integer.parseInt(amountField.getText()))
          .setProductPrice(Integer.parseInt(costField.getText()))
          .setTransactionTypeId(
              controller.transactionTypes.get(transactionTypeComboBox.getSelectedIndex()).getTransactionTypeId())
          .setExpirationDate(newExpirationDate)
          .build();

      ProductMovementModel updatedModel = controller.updateIntsOut(updatedIntsOut, movements.getUpdateProductId());
      if (updatedModel != null) {
        originalIntsOuts.set(originalIntsOuts.indexOf(movements), updatedModel);
        updateTable(null);
      }
      intsOutsDialog.dispose();
    });

    deleteButton.addActionListener(e -> {
      int result = JOptionPane.showConfirmDialog(this, "¿Estás seguro de eliminar esta entrada/salida?",
          "Eliminar Entrada/Salida",
          JOptionPane.YES_NO_OPTION);
      if (result == JOptionPane.YES_OPTION) {
        Integer deletedIntsOutId = controller.deleteIntsOut(movements.getUpdateProductId());
        if (deletedIntsOutId != null) {
          originalIntsOuts.remove(movements);
          updateTable(null);
        }
      }
      intsOutsDialog.dispose();
    });

    buttonsPanel.add(editButton);
    buttonsPanel.add(deleteButton);
    intsOutsDialog.add(buttonsPanel, BorderLayout.SOUTH);

    intsOutsDialog.addWindowListener(new java.awt.event.WindowAdapter() {
      @Override
      public void windowClosing(java.awt.event.WindowEvent windowEvent) {
        intsOutsDialog = null;
      }
    });

    intsOutsDialog.setVisible(true);
  }

  @SuppressWarnings("unused")
  private void showCreateIntsOutsDialog() {
    JDialog createDialog = new JDialog();
    createDialog.setTitle("Crear nueva entrada/salida");
    createDialog.setLayout(new BorderLayout());
    createDialog.setSize(400, 300);
    createDialog.setLocationRelativeTo(this);

    JPanel fieldsPanel = new JPanel(new GridLayout(6, 2, 10, 10));
    fieldsPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
    fieldsPanel.setBackground(controller.intsOutsComponent.getBackground());

    fieldsPanel.add(new JLabel("Producto:"));
    JComboBox<String> productNameComboBox = new JComboBox<>();
    for (ProductModel product : controller.productNames) {
      productNameComboBox.addItem(product.getProductName());
    }
    fieldsPanel.add(productNameComboBox);

    fieldsPanel.add(new JLabel("Razón:"));
    JTextField reasonField = new JTextField();
    fieldsPanel.add(reasonField);

    fieldsPanel.add(new JLabel("Cantidad:"));
    JTextField amountField = new JTextField();
    fieldsPanel.add(amountField);

    fieldsPanel.add(new JLabel("Costo:"));
    JTextField costField = new JTextField();
    fieldsPanel.add(costField);

    fieldsPanel.add(new JLabel("Tipo de Transacción:"));
    JComboBox<String> transactionTypeComboBox = new JComboBox<>();
    for (TransactionTypeModel transactionType : controller.transactionTypes) {
      transactionTypeComboBox.addItem(transactionType.getTransactionType());
    }
    fieldsPanel.add(transactionTypeComboBox);

    fieldsPanel.add(new JLabel("Fecha de Expiración:"));
    UtilDateModel model = new UtilDateModel();
    Properties p = new Properties();
    p.put("text.today", "Today");
    p.put("text.month", "Month");
    p.put("text.year", "Year");
    JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
    JDatePickerImpl expirationDatePicker = new JDatePickerImpl(datePanel, null);
    fieldsPanel.add(expirationDatePicker);

    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    expirationDatePicker.addActionListener(e -> {
      Date selectedDate = (Date) expirationDatePicker.getModel().getValue();
      if (selectedDate != null) {
        String formattedDate = dateFormat.format(selectedDate);
        expirationDatePicker.getJFormattedTextField().setText(formattedDate);
      }
    });

    createDialog.add(fieldsPanel, BorderLayout.CENTER);

    JPanel buttonsPanel = new JPanel();
    buttonsPanel.setBackground(controller.intsOutsComponent.getBackground());
    JButton saveButton = new JButton("Guardar");
    JButton cancelButton = new JButton("Cancelar");

    saveButton.addActionListener(e -> {
      if (validateCreateIntsOutsFields(reasonField, amountField, costField)) {
        Calendar calendar = Calendar.getInstance();
        Date expirationDate = (Date) expirationDatePicker.getModel().getValue();
        if (expirationDate != null) {
          calendar.setTime(expirationDate);
          calendar.add(Calendar.DAY_OF_MONTH, 1);
        }
        Date newExpirationDate = expirationDate != null ? calendar.getTime() : null;
        CreateUpdateProductDto newIntsOut = new CreateUpdateProductDto()
            .setProductId(controller.productNames.get(productNameComboBox.getSelectedIndex()).getProductId())
            .setReason(reasonField.getText())
            .setUpdateAmount(Integer.parseInt(amountField.getText()))
            .setProductPrice(Integer.parseInt(costField.getText()))
            .setTransactionTypeId(
                controller.transactionTypes.get(transactionTypeComboBox.getSelectedIndex()).getTransactionTypeId())
            .setExpirationDate(newExpirationDate)
            .build();

        ProductMovementModel createdIntsOut = controller.createIntsOut(newIntsOut);
        if (createdIntsOut != null) {
          updateTable(createdIntsOut);
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

  private boolean validateCreateIntsOutsFields(JTextField reasonField, JTextField amountField, JTextField costField) {
    if (reasonField.getText().trim().isEmpty()) {
      JOptionPane.showMessageDialog(this, "La razón no debe estar vacía", "Error",
          JOptionPane.ERROR_MESSAGE);
      return false;
    }
    if (amountField.getText().trim().isEmpty()) {
      JOptionPane.showMessageDialog(this, "La cantidad debe ser especificada", "Error", JOptionPane.ERROR_MESSAGE);
      return false;
    }
    try {
      Integer.parseInt(amountField.getText().trim());
    } catch (NumberFormatException e) {
      JOptionPane.showMessageDialog(this, "La cantidad debe ser un número entero", "Error",
          JOptionPane.ERROR_MESSAGE);
      return false;
    }
    if (costField.getText().trim().isEmpty()) {
      JOptionPane.showMessageDialog(this, "El costo debe ser especificado", "Error", JOptionPane.ERROR_MESSAGE);
      return false;
    }
    try {
      Double.parseDouble(costField.getText().trim());
    } catch (NumberFormatException e) {
      JOptionPane.showMessageDialog(this, "El costo debe ser un número válido", "Error",
          JOptionPane.ERROR_MESSAGE);
      return false;
    }
    return true;
  }

  public void updateTable(ProductMovementModel newIntsOut) {
    if (newIntsOut != null) {
      originalIntsOuts.add(newIntsOut);
    }
    createTable(originalIntsOuts);
  }

  @SuppressWarnings("unused")
  public void addCreateButtonRow() {
    JButton createButton = new JButton("Añadir nueva entrada/salida");
    createButton.addActionListener(e -> showCreateIntsOutsDialog());

    JPanel buttonPanel = new JPanel(new BorderLayout());
    buttonPanel.add(createButton, BorderLayout.CENTER);
    this.add(buttonPanel, BorderLayout.NORTH);
  }
}
