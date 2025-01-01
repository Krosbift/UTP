package com.desktop.views.IntsOuts.components;

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
import com.desktop.views.IntsOuts.IntsOutsPanelController;
import com.desktop.views.IntsOuts.model.GetUpdateProductDto;
import com.desktop.views.shared.models.index.TransactionTypeModel;
import com.desktop.views.shared.models.product.ProductModel;

public class TopPanelComponent extends JPanel implements ComponentInterface {
  public String title;
  public IntsOutsPanelController controller;
  private JLabel titleLabel;
  private JPanel buttonPanel;
  private JButton button1;
  private JButton button2;

  public TopPanelComponent(IntsOutsPanelController controller, String title) {
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
    this.setBackground(controller.intsOutsComponent.getBackground());
    controller.intsOutsComponent.add(this);
  }

  /**
   * Adds child components to the panel with specific layout constraints.
   * 
   * This method initializes and adds a title label and a button panel to the
   * panel.
   * The title label is displayed on the left side, and the button panel is
   * displayed on the right side.
   * If the user's role type ID is 1, an additional button is added to the button
   * panel.
   * 
   * Components added:
   * - JLabel: Displays the title in uppercase with a specific font size.
   * - JPanel: Contains buttons for different views.
   * - JButton: "Vista Completa" (added if the user's role type ID is 1).
   * - JButton: "Vista de datos".
   */
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
    buttonPanel.setBackground(controller.intsOutsComponent.getBackground());
    button2 = new JButton("Vista de datos");

    if (controller.parentController.user.getRoleType().getRoleTypeId() == 1) {
      button1 = new JButton("Vista Completa");
      buttonPanel.add(button1);
    }

    buttonPanel.add(button2);
    this.add(buttonPanel, gbc);
  }

  /**
   * Adds event listeners to the buttons in the TopPanelComponent.
   * 
   * <p>
   * If the user's role type ID is 1, an action listener is added to button1
   * that prints a message to the console when the button is pressed.
   * </p>
   * 
   * <p>
   * An action listener is always added to button2 that triggers the
   * showDialog() method when the button is pressed.
   * </p>
   */
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

  /**
   * Displays a dialog for entering product details, including product name and
   * transaction type.
   * The dialog contains two combo boxes for selecting a product name and a
   * transaction type.
   * If the user confirms the dialog, a GetUpdateProductDto object is created with
   * the selected
   * product ID and transaction type ID, and the data table is updated with this
   * DTO.
   * 
   * The dialog includes:
   * - A combo box for selecting a product name from the list of product names.
   * - A combo box for selecting a transaction type from the list of transaction
   * types.
   * 
   * The method performs the following steps:
   * 1. Initializes the combo boxes with product names and transaction types.
   * 2. Creates a panel with labels and combo boxes arranged using GridBagLayout.
   * 3. Displays a confirmation dialog with the panel.
   * 4. If the user confirms, creates a GetUpdateProductDto with the selected
   * values and updates the data table.
   */
  private void showDialog() {
    JComboBox<String> productNameComboBox = new JComboBox<>();
    productNameComboBox.addItem("");
    for (ProductModel product : controller.productNames) {
      productNameComboBox.addItem(product.getProductName());
    }
    JComboBox<String> transactionTypeComboBox = new JComboBox<>();
    transactionTypeComboBox.addItem("");
    for (TransactionTypeModel transaction : controller.transactionTypes) {
      transactionTypeComboBox.addItem(transaction.getTransactionType());
    }

    JPanel panel = new JPanel(new GridBagLayout());
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.insets = new Insets(5, 5, 5, 5);
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.anchor = GridBagConstraints.WEST;

    gbc.gridx = 0;
    gbc.gridy++;
    panel.add(new JLabel("Nombre de producto:"), gbc);
    gbc.gridx++;
    panel.add(productNameComboBox, gbc);

    gbc.gridx = 0;
    gbc.gridy++;
    panel.add(new JLabel("Tipo de transacción:"), gbc);
    gbc.gridx++;
    panel.add(transactionTypeComboBox, gbc);

    int result = JOptionPane.showConfirmDialog(null, panel, "Enter Product Details", JOptionPane.OK_CANCEL_OPTION,
        JOptionPane.PLAIN_MESSAGE);
    if (result == JOptionPane.OK_OPTION) {
      GetUpdateProductDto dto = new GetUpdateProductDto()
          .setProductId(
              productNameComboBox.getSelectedIndex() <= 0 ? null
                  : controller.productNames.get(productNameComboBox.getSelectedIndex() - 1).getProductId())
          .setTransactionTypeId(transactionTypeComboBox.getSelectedIndex() <= 0 ? null
              : controller.transactionTypes.get(transactionTypeComboBox.getSelectedIndex() - 1).getTransactionTypeId())
          .build();

      controller.setDataTable(dto);
    }
  }

  /**
   * Adds a component listener to the content panel component of the parent
   * controller.
   * The listener triggers the resizeComponents method whenever the component is
   * resized.
   * Also calls resizeComponents initially to ensure components are sized
   * correctly.
   */
  private void _listernerSizing() {
    controller.parentController.contentPanelComponent.addComponentListener(new ComponentAdapter() {
      @Override
      public void componentResized(ComponentEvent e) {
        resizeComponents();
      }
    });
    resizeComponents();
  }

  /**
   * Resizes the components of the TopPanelComponent.
   * This method sets the bounds of the component to match the width of the
   * intsOutsComponent and 10% of its height. It then revalidates and repaints
   * the component to reflect the changes.
   */
  private void resizeComponents() {
    this.setBounds(0, 0, controller.intsOutsComponent.getWidth(),
        (int) (controller.intsOutsComponent.getHeight() * 0.1));
    this.revalidate();
    this.repaint();
  }
}
