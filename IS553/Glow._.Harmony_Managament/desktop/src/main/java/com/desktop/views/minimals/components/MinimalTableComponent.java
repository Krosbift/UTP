package com.desktop.views.minimals.components;

import com.desktop.core.utils.interfaces.ComponentInterface;
import com.desktop.views.minimals.MinimalPanelController;
import com.desktop.views.minimals.model.ProductMinimalStockModel;
import com.desktop.views.shared.models.inventory.ProductMovementModel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

public class MinimalTableComponent extends JPanel implements ComponentInterface {
  MinimalPanelController controller;
  private JTable table;
  private JDialog orderDialog;

  public MinimalTableComponent(MinimalPanelController controller) {
    this.controller = controller;
    _configureComponent();
    _listenerSizing();
    SwingUtilities.invokeLater(() -> controller.setDataTable());
  }

  @Override
  public void _configureComponent() {
    this.setLayout(new BorderLayout());
    this.setBounds(0, 0, controller.minimalPanelComponent.getWidth(), controller.minimalPanelComponent.getHeight());
    this.setBackground(controller.minimalPanelComponent.getBackground());
    controller.minimalPanelComponent.add(this);
  }

  private void addTableClickListener(JTable table) {
    table.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent evt) {
        int row = table.rowAtPoint(evt.getPoint());
        if (row >= 0 && (orderDialog == null || !orderDialog.isVisible())) {
          DefaultTableModel model = (DefaultTableModel) table.getModel();
          String productName = (String) model.getValueAt(row, 0);
          String productCategory = (String) model.getValueAt(row, 1);
          int stock = (int) model.getValueAt(row, 2);
          ProductMinimalStockModel selectedProduct = new ProductMinimalStockModel()
              .setProductName(productName)
              .setProductCategory(productCategory)
              .setStock(stock);
          showOrderDialog(selectedProduct, row);
        }
      }
    });
  }

  @SuppressWarnings("unused")
  private void showOrderDialog(ProductMinimalStockModel product, int rowIndex) {
    SwingUtilities.invokeLater(() -> {
      if (orderDialog != null && orderDialog.isVisible()) {
        return;
      }

      orderDialog = new JDialog();
      orderDialog.setTitle("Hacer Pedido Máximo");
      orderDialog.setSize(300, 200);
      orderDialog.setLayout(new BorderLayout());
      orderDialog.setUndecorated(true);
      orderDialog.setBackground(controller.minimalPanelComponent.getBackground());
      orderDialog.getRootPane().setBorder(javax.swing.BorderFactory.createLineBorder(Color.PINK, 2));

      JLabel messageLabel = new JLabel("¿Desea hacer un pedido máximo para " + product.getProductName() + "?");
      messageLabel.setHorizontalAlignment(JLabel.CENTER);
      orderDialog.add(messageLabel, BorderLayout.CENTER);

      JButton orderButton = new JButton("Hacer Pedido Máximo Instantáneamente");
      orderButton.setBackground(Color.PINK);
      orderButton.setForeground(Color.WHITE);
      orderButton.addActionListener(e -> {
        ProductMovementModel movement = controller.minimalService.updateProductMinimalStock(product.getProductName());
        if (movement.getReason() != null) {
          ((DefaultTableModel) table.getModel()).removeRow(rowIndex);
        }
        orderDialog.dispose();
      });
      orderDialog.add(orderButton, BorderLayout.SOUTH);

      orderDialog.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
          if (!orderDialog.getBounds().contains(e.getPoint())) {
            orderDialog.dispose();
          }
        }
      });
      orderDialog.setLocationRelativeTo(this);
      orderDialog.setVisible(true);
    });
  }

  public void createTable(List<ProductMinimalStockModel> minimals) {
    this.removeAll();

    String[] columnNames = { "Nombre Producto", "Categoria", "Stock" };
    DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0) {
      @Override
      public boolean isCellEditable(int row, int column) {
        return false;
      }
    };

    for (ProductMinimalStockModel product : minimals) {
      Object[] rowData = {
          product.getProductName(),
          product.getProductCategory(),
          product.getStock(),
      };
      tableModel.addRow(rowData);
    }

    table = new JTable(tableModel);
    table.setRowHeight(40);
    table.setBackground(controller.minimalPanelComponent.getBackground());
    table.getTableHeader().setBackground(controller.minimalPanelComponent.getBackground());

    addTableClickListener(table);

    JScrollPane scrollPane = new JScrollPane(table);
    scrollPane.setPreferredSize(new Dimension(this.getWidth() - 40, this.getHeight() - 80));
    scrollPane.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
    this.add(scrollPane, BorderLayout.CENTER);

    this.revalidate();
    this.repaint();
  }

  private void _listenerSizing() {
    this.addComponentListener(new ComponentAdapter() {
      @Override
      public void componentResized(ComponentEvent e) {
        resizeComponents();
      }
    });
    resizeComponents();
  }

  private void resizeComponents() {
    this.setBounds(0, 0, controller.minimalPanelComponent.getWidth(), controller.minimalPanelComponent.getHeight());
    this.revalidate();
    this.repaint();
  }
}
