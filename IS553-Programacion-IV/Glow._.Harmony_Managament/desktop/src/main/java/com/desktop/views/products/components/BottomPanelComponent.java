package com.desktop.views.products.components;

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
import javax.swing.JComboBox;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import com.desktop.core.utils.interfaces.ComponentInterface;
import com.desktop.views.products.ProductsPanelController;
import com.desktop.views.products.model.CreateProductDto;
import com.desktop.views.products.model.UpdateProductDto;
import com.desktop.views.shared.models.SupplierModel;
import com.desktop.views.shared.models.index.ProductCategoryModel;
import com.desktop.views.shared.models.product.ProductModel;

public class BottomPanelComponent extends JPanel implements ComponentInterface {
  public ProductsPanelController controller;
  public List<ProductModel> originalProducts;
  private JDialog productDialog;

  public BottomPanelComponent(ProductsPanelController controller) {
    this.controller = controller;
    _configureComponent();
    _listernerSizing();
  }

  @Override
  public void _configureComponent() {
    this.setLayout(new BorderLayout());
    this.setBounds(0, (int) (controller.productsComponent.getHeight() * 0.1), controller.productsComponent.getWidth(),
        (int) (controller.productsComponent.getHeight() * 0.9));
    this.setBackground(controller.productsComponent.getBackground());
    controller.productsComponent.add(this);
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
    this.setBounds(0, (int) (controller.productsComponent.getHeight() * 0.1), controller.productsComponent.getWidth(),
        (int) (controller.productsComponent.getHeight() * 0.9));
    this.revalidate();
    this.repaint();
  }

  public void createTable(List<ProductModel> products) {
    this.removeAll();
    this.originalProducts = products;

    String[] columnNames = { "ID", "Nombre Producto", "Categoria", "Proveedor" };
    DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0) {
      @Override
      public boolean isCellEditable(int row, int column) {
        return false;
      }
    };

    for (ProductModel product : products) {
      Object[] rowData = {
          product.getProductId(),
          product.getProductName(),
          product.getProductCategoryModel().getProductCategory(),
          product.getSupplierModel().getName()
      };
      tableModel.addRow(rowData);
    }

    JTable table = new JTable(tableModel);
    table.setRowHeight(40);
    table.setBackground(controller.productsComponent.getBackground());
    table.getTableHeader().setBackground(controller.productsComponent.getBackground());

    table.getColumnModel().getColumn(0).setMinWidth(0);
    table.getColumnModel().getColumn(0).setMaxWidth(0);
    table.getColumnModel().getColumn(0).setWidth(0);

    if (controller.parentController.user.getRoleType().getRoleTypeId() == 1) {
      table.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
          int row = table.getSelectedRow();
          if (row != -1) {
            int productId = (int) table.getValueAt(row, 0);
            ProductModel selectedProduct = originalProducts.stream().filter(p -> p.getProductId() == productId)
                .findFirst().get();
            showProductDialog(selectedProduct);
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
  private void showProductDialog(ProductModel product) {
    if (productDialog != null) {
      productDialog.dispose();
    }

    productDialog = new JDialog();
    productDialog.setTitle("Detalles del producto");
    productDialog.setLayout(new BorderLayout());
    productDialog.setSize(400, 300);
    productDialog.setLocationRelativeTo(this);

    JPanel fieldsPanel = new JPanel(new GridLayout(4, 2, 10, 10));
    fieldsPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
    fieldsPanel.setBackground(controller.productsComponent.getBackground());

    fieldsPanel.add(new JLabel("Nombre del producto:"));
    JTextField productNameField = new JTextField(product.getProductName());
    fieldsPanel.add(productNameField);

    fieldsPanel.add(new JLabel("Categoria:"));
    JComboBox<String> categoryComboBox = new JComboBox<>();
    for (ProductCategoryModel category : controller.productCategories) {
      categoryComboBox.addItem(category.getProductCategory());
    }
    categoryComboBox.setSelectedItem(product.getProductCategoryModel().getProductCategory());
    fieldsPanel.add(categoryComboBox);

    fieldsPanel.add(new JLabel("proveedor:"));
    JComboBox<String> supplierComboBox = new JComboBox<>();
    for (SupplierModel supplier : controller.suppliers) {
      supplierComboBox.addItem(supplier.getName());
    }
    supplierComboBox.setSelectedItem(product.getSupplierModel().getName());
    fieldsPanel.add(supplierComboBox);

    productDialog.add(fieldsPanel, BorderLayout.CENTER);

    JPanel buttonsPanel = new JPanel();
    buttonsPanel.setBackground(controller.productsComponent.getBackground());
    JButton editButton = new JButton("Editar");
    JButton deleteButton = new JButton("Eliminar");

    editButton.addActionListener(e -> {
      UpdateProductDto updateProduct = (UpdateProductDto) new UpdateProductDto()
          .setProductName(productNameField.getText())
          .setProductCategoryId(
              controller.productCategories.get(categoryComboBox.getSelectedIndex()).getProductCategoryId())
          .setSupplierId(controller.suppliers.get(supplierComboBox.getSelectedIndex()).getSupplierId())
          .build();

      ProductModel updatedProduct = controller.updateProduct(updateProduct, product.getProductId());
      if (updatedProduct != null) {
        originalProducts.set(originalProducts.indexOf(product), updatedProduct);
        updateTable(null);
      }
      productDialog.dispose();
    });

    deleteButton.addActionListener(e -> {
      int result = JOptionPane.showConfirmDialog(this, "¿Estás seguro de eliminar este producto?",
          "Eliminar Producto",
          JOptionPane.YES_NO_OPTION);
      if (result == JOptionPane.YES_OPTION) {
        Integer deletedProductId = controller.deleteProduct(product.getProductId());
        if (deletedProductId != null) {
          originalProducts.remove(product);
          updateTable(null);
        }
      }
      productDialog.dispose();
    });

    buttonsPanel.add(editButton);
    buttonsPanel.add(deleteButton);
    productDialog.add(buttonsPanel, BorderLayout.SOUTH);

    productDialog.addWindowListener(new java.awt.event.WindowAdapter() {
      @Override
      public void windowClosing(java.awt.event.WindowEvent windowEvent) {
        productDialog = null;
      }
    });

    productDialog.setVisible(true);
  }

  @SuppressWarnings("unused")
  public void addCreateButtonRow() {
    JButton createButton = new JButton("Añadir nuevo producto");
    createButton.addActionListener(e -> showCreateProductDialog());

    JPanel buttonPanel = new JPanel(new BorderLayout());
    buttonPanel.add(createButton, BorderLayout.CENTER);
    this.add(buttonPanel, BorderLayout.NORTH);
  }

  @SuppressWarnings("unused")
  private void showCreateProductDialog() {
    JDialog createDialog = new JDialog();
    createDialog.setTitle("Crear nuevo producto");
    createDialog.setLayout(new BorderLayout());
    createDialog.setSize(400, 300);
    createDialog.setLocationRelativeTo(this);

    JPanel fieldsPanel = new JPanel(new GridLayout(4, 2, 10, 10));
    fieldsPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
    fieldsPanel.setBackground(controller.productsComponent.getBackground());

    fieldsPanel.add(new JLabel("Nombre de producto:"));
    JTextField productNameField = new JTextField();
    fieldsPanel.add(productNameField);

    fieldsPanel.add(new JLabel("Categoria:"));
    JComboBox<String> categoryComboBox = new JComboBox<>();
    for (ProductCategoryModel category : controller.productCategories) {
      categoryComboBox.addItem(category.getProductCategory());
    }
    fieldsPanel.add(categoryComboBox);

    fieldsPanel.add(new JLabel("Proveedor:"));
    JComboBox<String> supplierComboBox = new JComboBox<>();
    for (SupplierModel supplier : controller.suppliers) {
      supplierComboBox.addItem(supplier.getName());
    }
    fieldsPanel.add(supplierComboBox);

    createDialog.add(fieldsPanel, BorderLayout.CENTER);

    JPanel buttonsPanel = new JPanel();
    buttonsPanel.setBackground(controller.productsComponent.getBackground());
    JButton saveButton = new JButton("Guardar");
    JButton cancelButton = new JButton("Cancel");

    saveButton.addActionListener(e -> {
      if (validateCreateProductFields(productNameField)) {
        CreateProductDto newProduct = new CreateProductDto()
            .setProductName(productNameField.getText())
            .setProductCategoryId(
                controller.productCategories.get(categoryComboBox.getSelectedIndex()).getProductCategoryId())
            .setSupplierId(controller.suppliers.get(supplierComboBox.getSelectedIndex()).getSupplierId())
            .build();

        ProductModel createdProduct = controller.createProduct(newProduct);
        if (createdProduct != null) {
          updateTable(createdProduct);
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

  private boolean validateCreateProductFields(JTextField productNameField) {
    if (productNameField.getText().trim().isEmpty()) {
      JOptionPane.showMessageDialog(this, "Nombre del producto no debe estar vacio", "Error",
          JOptionPane.ERROR_MESSAGE);
      return false;
    }
    return true;
  }

  public void updateTable(ProductModel newProduct) {
    if (newProduct != null) {
      originalProducts.add(newProduct);
    }
    createTable(originalProducts);
  }
}
