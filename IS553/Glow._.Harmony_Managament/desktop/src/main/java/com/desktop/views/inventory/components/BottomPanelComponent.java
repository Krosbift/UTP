package com.desktop.views.inventory.components;

import javax.swing.JPanel;
import com.desktop.core.utils.interfaces.ComponentInterface;
import com.desktop.views.inventory.InventoryPanelController;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.List;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import com.desktop.views.inventory.model.ProductStockModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;

public class BottomPanelComponent extends JPanel implements ComponentInterface {
  public InventoryPanelController controller;
  private JTable table;

  public BottomPanelComponent(InventoryPanelController inventoryPanelController) {
    this.controller = inventoryPanelController;
    _configureComponent();
    _listernerSizing();
  }

  @Override
  public void _configureComponent() {
    this.setLayout(new BorderLayout());
    this.setBounds(0, (int) (controller.inventoryComponent.getHeight() * 0.1), controller.inventoryComponent.getWidth(),
        (int) (controller.inventoryComponent.getHeight() * 0.9));
    this.setBackground(controller.inventoryComponent.getBackground());
    controller.inventoryComponent.add(this);
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
    this.setBounds(0, (int) (controller.inventoryComponent.getHeight() * 0.1), controller.inventoryComponent.getWidth(),
        (int) (controller.inventoryComponent.getHeight() * 0.9));
    this.revalidate();
    this.repaint();
  }

  public void createTable(List<ProductStockModel> products) {
    this.removeAll();

    String[] columnNames = { "ID", "Nombre Producto", "Categoria", "Stock", "Costo Promedio", "Precio Total",
        "Proveedor" };
    DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0) {
      @Override
      public boolean isCellEditable(int row, int column) {
        return false;
      }
    };

    for (ProductStockModel product : products) {
      Object[] rowData = {
          product.getProductId(),
          product.getProductName(),
          product.getProductCategory(),
          product.getStock(),
          product.getProductPrice(),
          product.getTotalPrice(),
          product.getSupplierName(),
      };
      tableModel.addRow(rowData);
    }

    table = new JTable(tableModel);
    table.setRowHeight(40);
    table.setBackground(controller.inventoryComponent.getBackground());
    table.getTableHeader().setBackground(controller.inventoryComponent.getBackground());

    table.getColumnModel().getColumn(0).setMinWidth(0);
    table.getColumnModel().getColumn(0).setMaxWidth(0);
    table.getColumnModel().getColumn(0).setWidth(0);

    JScrollPane scrollPane = new JScrollPane(table);
    scrollPane.setPreferredSize(new Dimension(this.getWidth() - 40, this.getHeight() - 80));
    scrollPane.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
    this.add(scrollPane, BorderLayout.CENTER);

    addExportButton();
    addTableClickListener();

    this.revalidate();
    this.repaint();
  }

  private void addExportButton() {
    JButton exportButton = new JButton("Exportar CSV");
    exportButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        exportTableToCSV();
      }
    });
    this.add(exportButton, BorderLayout.SOUTH);
  }

  private void exportTableToCSV() {
    JFileChooser fileChooser = new JFileChooser();
    if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
      try (FileWriter writer = new FileWriter(fileChooser.getSelectedFile() + ".csv", java.nio.charset.StandardCharsets.UTF_8)) {
        writer.write("Nombre Producto,Categoria,Stock,Precio Unitario,Costo Total,Proveedor\n");
        for (int i = 0; i < table.getRowCount(); i++) {
          for (int j = 1; j < table.getColumnCount(); j++) {
            writer.write(table.getValueAt(i, j).toString() + ",");
          }
          writer.write("\n");
        }
        JOptionPane.showMessageDialog(this, "Datos exportados exitosamente.");
      } catch (IOException ex) {
        JOptionPane.showMessageDialog(this, "Error al exportar datos: " + ex.getMessage());
      }
    }
  }

  private void addTableClickListener() {
    table.addMouseListener(new java.awt.event.MouseAdapter() {
      @Override
      public void mouseClicked(java.awt.event.MouseEvent evt) {
        if (evt.getClickCount() == 2) {
          int row = table.rowAtPoint(evt.getPoint());
          exportSingleRowToCSV(row);
        }
      }
    });
  }

  private void exportSingleRowToCSV(int row) {
    JFileChooser fileChooser = new JFileChooser();
    if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
      try (FileWriter writer = new FileWriter(fileChooser.getSelectedFile() + ".csv", java.nio.charset.StandardCharsets.UTF_8)) {
        writer.write("Nombre Producto,Categoria,Stock,Precio Unitario,Costo Total,Proveedor\n");
        for (int j = 1; j < table.getColumnCount(); j++) {
          writer.write(table.getValueAt(row, j).toString() + ",");
        }
        writer.write("\n");
        JOptionPane.showMessageDialog(this, "Datos exportados exitosamente.");
      } catch (IOException ex) {
        JOptionPane.showMessageDialog(this, "Error al exportar datos: " + ex.getMessage());
      }
    }
  }
}