package com.api.routes.inventory.usecases;

import java.util.Objects;
import com.api.routes.inventory.dto.UpdateProductMovementDto;
import com.api.routes.shared.models.inventory.ProductMovementModel;

public class ValidateModifyUseCase {
  /**
   * Validates if there is enough stock available for a purchase.
   *
   * @param stock the current stock available
   * @param amountToBuy the amount of stock to be bought
   * @param isBuy a boolean indicating if the operation is a purchase
   * @return true if the stock is sufficient for the purchase, false otherwise
   */
  public static boolean validateStockInCreate(int stock, int amountToBuy, boolean isBuy) {
    return isBuy ? stock >= amountToBuy : false;
  }

  /**
   * Validates if the stock can be modified based on the given product movement details.
   *
   * @param changeMovement the details of the product movement to be updated
   * @param actualMovement the current product movement details
   * @param stock the current stock level
   * @return true if the stock can be modified, false otherwise
   */
  public static boolean validateStockInModify(UpdateProductMovementDto changeMovement, ProductMovementModel actualMovement, int stock) {
    if (!Objects.equals(changeMovement.getTransactionTypeId(), 2)) {
      return false;
    }
    if (actualMovement.getTransactionTypeModel().getTransactionTypeId() != 1) {
      return false;
    }
    int updateAmount = changeMovement.getUpdateAmount() != null ? changeMovement.getUpdateAmount() : actualMovement.getUpdateAmount();
    return stock - updateAmount * 2 < 0;
  }
}