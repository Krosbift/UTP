package com.api.routes.inventory.usecases;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import com.api.routes.inventory.model.ProductStockModel;
import com.api.routes.shared.models.inventory.InventoryModel;

public class InvetoryManagmentUseCase {
  /**
   * Calculates the stock for a list of inventory updates.
   *
   * This method processes a list of inventory updates and calculates the stock
   * for each product.
   * It maintains a map of product IDs to their corresponding stock models,
   * processes each update,
   * and then calculates the total prices for the products.
   *
   * @param updates A list of InventoryModel objects representing the inventory
   *                updates.
   * @return A list of ProductStockModel objects representing the calculated stock
   *         for each product.
   */
  public static List<ProductStockModel> calculateStock(List<InventoryModel> updates) {
    Map<Integer, ProductStockModel> productMap = new HashMap<>();
    for (InventoryModel update : updates) {
      processUpdate(update, productMap);
    }
    calculateTotalCost(productMap);
    return productMap.values().stream().collect(Collectors.toList());
  }

  /**
   * Processes an update to the inventory by updating the product stock based on
   * the transaction type.
   *
   * @param update     The InventoryModel object containing the update details.
   * @param productMap A map of product IDs to ProductStockModel objects
   *                   representing the current stock of products.
   */
  private static void processUpdate(InventoryModel update, Map<Integer, ProductStockModel> productMap) {
    int productId = update.getProductModel().getProductId();
    int transactionTypeId = update.getTransactionTypeModel().getTransactionTypeId();
    int updateAmount = update.getProductMovementModel().getUpdateAmount();
    Double productPrice = update.getProductMovementModel().getProductPrice();
    if (!productMap.containsKey(productId)) {
      initializeProductDetails(update, productMap, productId);
    }
    updateStockAndAverageCost(productMap.get(productId), transactionTypeId, updateAmount, productPrice);
  }

  /**
   * Initializes the product details and adds them to the provided product map.
   *
   * @param update       The InventoryModel object containing the product details
   *                     to be updated.
   * @param productMap   The map where the product details will be stored, with
   *                     the product ID as the key.
   * @param productId    The ID of the product to be initialized.
   * @param productPrice The price of the product to be set.
   */
  private static void initializeProductDetails(InventoryModel update, Map<Integer, ProductStockModel> productMap,
      int productId) {
    ProductStockModel productDetails = new ProductStockModel()
        .setProductId(update.getProductModel().getProductId())
        .setProductName(update.getProductModel().getProductName())
        .setProductCategory(update.getProductCategoryModel().getProductCategory())
        .setSupplierName(update.getSupplierModel().getName())
        .setStock(0)
        .setProductPrice(0.0)
        .setTotalPrice(0.0);
    productMap.put(productId, productDetails);
  }

  /**
   * Updates the stock of a product based on the transaction type and update
   * amount.
   *
   * @param productDetails    The product details containing the current stock
   *                          information.
   * @param transactionTypeId The type of transaction to be performed:
   *                          1 - Add to stock
   *                          2 - Subtract from stock
   *                          3 - Set stock to a specific amount
   * @param updateAmount      The amount by which the stock should be updated.
   */
  private static void updateStockAndAverageCost(ProductStockModel productDetails, int transactionTypeId, int updateAmount, Double productPrice) {
    int currentStock = productDetails.getStock();
    Double currentTotalStock = productDetails.getTotalPrice();
    Double currentProductPrice = productDetails.getProductPrice();
    if (transactionTypeId == 3) {
      productDetails.setStock(updateAmount);
      productDetails.setTotalPrice(currentTotalStock);
      productDetails.setProductPrice(productPrice * updateAmount);
    } else if (transactionTypeId == 1) {
      productDetails.setStock(currentStock + updateAmount);
      productDetails.setTotalPrice(currentTotalStock + updateAmount);
      productDetails.setProductPrice(currentProductPrice + (productPrice * updateAmount));
    } else if (transactionTypeId == 2) {
      productDetails.setStock(currentStock - updateAmount);
    }
  }

  /**
   * Calculates and sets the total price for each product in the provided product
   * map.
   * The total price is computed as the product of the stock and the product
   * price.
   *
   * @param productMap a map where the key is the product ID and the value is the
   *                   ProductStockModel
   *                   containing details about the product, including its price
   *                   and stock.
   */
  private static void calculateTotalCost(Map<Integer, ProductStockModel> productMap) {
    for (ProductStockModel productDetails : productMap.values()) {
      Double stockFullInts = productDetails.getTotalPrice();
      Double productPrice = productDetails.getProductPrice();
      productDetails.setProductPrice(productPrice / stockFullInts);
      int stock = productDetails.getStock();
      Double averagePrice = productDetails.getProductPrice();
      productDetails.setTotalPrice(averagePrice * stock);
    }
  }
}
