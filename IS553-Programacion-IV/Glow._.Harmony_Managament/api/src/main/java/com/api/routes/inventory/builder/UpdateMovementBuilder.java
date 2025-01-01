package com.api.routes.inventory.builder;

import java.util.ArrayList;
import java.util.List;
import com.api.routes.shared.utils.query.Binds;
import com.api.routes.inventory.dto.UpdateProductMovementDto;

public class UpdateMovementBuilder {
  public static Binds buildUpdateUpdateProducts(UpdateProductMovementDto updateProductMovementDto, int updateProductId) {
    StringBuilder sql = new StringBuilder("UPDATE TB_IMS_UPDATEPRODUCTS SET ");
    List<Object> params = new ArrayList<>();
    if (updateProductMovementDto.getReason() != null) {
      sql.append("REASON = ?, ");
      params.add(updateProductMovementDto.getReason());
    }
    if (updateProductMovementDto.getProductId() != null) {
      sql.append("PRODUCTID = ?, ");
      params.add(updateProductMovementDto.getProductId());
    }
    if (updateProductMovementDto.getTransactionTypeId() != null) {
      sql.append("TRANSACTIONTYPEID = ?, ");
      params.add(updateProductMovementDto.getTransactionTypeId());
    }
    if (updateProductMovementDto.getExpirationDate() != null) {
      sql.append("EXPIRATIONDATE = ?, ");
      params.add(updateProductMovementDto.getExpirationDate());
    }
    if (updateProductMovementDto.getUpdateAmount() != null) {
      sql.append("UPDATEAMOUNT = ?, ");
      params.add(updateProductMovementDto.getUpdateAmount());
    }
    if (updateProductMovementDto.getUpdateAmount() != null) {
      sql.append("PRICE = ?, ");
      params.add(updateProductMovementDto.getProductPrice());
    }
    if (sql.charAt(sql.length() - 2) == ',') {
      sql.setLength(sql.length() - 2);
    }
    sql.append(" WHERE UPDATEPRODUCTID = ?");
    params.add(updateProductId);
    return new Binds(sql.toString(), params.toArray());
  }
}
