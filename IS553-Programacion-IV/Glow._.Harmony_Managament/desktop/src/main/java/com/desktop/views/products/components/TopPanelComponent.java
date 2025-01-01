package com.desktop.views.products.components;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Font;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import com.desktop.core.utils.interfaces.ComponentInterface;
import com.desktop.views.products.ProductsPanelController;
import com.desktop.views.products.model.GetProductDto;
import com.desktop.views.shared.models.SupplierModel;
import com.desktop.views.shared.models.index.ProductCategoryModel;
import com.desktop.views.shared.models.product.ProductModel;

public class TopPanelComponent extends JPanel implements ComponentInterface {
  public String title;
  public ProductsPanelController controller;
  private JLabel titleLabel;
  private JPanel buttonPanel;
  private JButton button1;
  private JButton button2;

  public TopPanelComponent(ProductsPanelController controller, String title) {
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
    this.setBackground(controller.productsComponent.getBackground());
    controller.productsComponent.add(this);
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
    buttonPanel.setBackground(controller.productsComponent.getBackground());
    button2 = new JButton("Vista de datos");

    if (controller.parentController.user.getRoleType().getRoleTypeId() == 1) {
      button1 = new JButton("Vista Completa");
      buttonPanel.add(button1);
    }

    buttonPanel.add(button2);
    this.add(buttonPanel, gbc);
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
    JComboBox<String> products = new JComboBox<>();
    products.addItem("");
    for (ProductModel category : controller.products) {
      products.addItem(category.getProductName());
    }
    JComboBox<String> productCategoryComboBox = new JComboBox<>();
    productCategoryComboBox.addItem("");
    for (ProductCategoryModel category : controller.productCategories) {
      productCategoryComboBox.addItem(category.getProductCategory());
    }
    JComboBox<String> supplierComboBox = new JComboBox<>();
    supplierComboBox.addItem("");
    for (SupplierModel supplier : controller.suppliers) {
      supplierComboBox.addItem(supplier.getName());
    }

    JPanel panel = new JPanel(new GridBagLayout());
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.insets = new Insets(5, 5, 5, 5);
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.anchor = GridBagConstraints.WEST;

    gbc.gridx = 0;
    gbc.gridy++;
    panel.add(new JLabel("Nombre del producto:"), gbc);
    gbc.gridx++;
    panel.add(products, gbc);

    gbc.gridx = 0;
    gbc.gridy++;
    panel.add(new JLabel("Categoría de producto:"), gbc);
    gbc.gridx++;
    panel.add(productCategoryComboBox, gbc);

    gbc.gridx = 0;
    gbc.gridy++;
    panel.add(new JLabel("Nombre del proveedor:"), gbc);
    gbc.gridx++;
    panel.add(supplierComboBox, gbc);

    int result = JOptionPane.showConfirmDialog(null, panel, "Enter Product Details", JOptionPane.OK_CANCEL_OPTION,
        JOptionPane.PLAIN_MESSAGE);
    if (result == JOptionPane.OK_OPTION) {
      GetProductDto dto = new GetProductDto()
          .setProductId(products.getSelectedIndex() <= 0 ? null
              : controller.products.get(products.getSelectedIndex() - 1).getProductId())
          .setProductCategoryId(productCategoryComboBox.getSelectedIndex() <= 0 ? null
              : controller.productCategories.get(productCategoryComboBox.getSelectedIndex() - 1).getProductCategoryId())
          .setSupplierId(supplierComboBox.getSelectedIndex() <= 0 ? null
              : controller.suppliers.get(supplierComboBox.getSelectedIndex() - 1).getSupplierId())
          .build();

      controller.setDataTable(dto);
    }
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
    this.setBounds(0, 0, controller.productsComponent.getWidth(),
        (int) (controller.productsComponent.getHeight() * 0.1));
    this.revalidate();
    this.repaint();
  }
}