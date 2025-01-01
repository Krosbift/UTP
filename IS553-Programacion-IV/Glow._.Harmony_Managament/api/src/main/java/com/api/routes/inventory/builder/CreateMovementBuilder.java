package com.api.routes.inventory.builder;

import java.util.ArrayList;
import java.util.List;
import com.api.routes.inventory.dto.CreateProductMovementDto;
import com.api.routes.shared.utils.query.Binds;

public class CreateMovementBuilder {
  public static Binds buildCreateUpdateProduct(CreateProductMovementDto createProductMovementDto) {
    StringBuilder sql = new StringBuilder("INSERT INTO TB_IMS_UPDATEPRODUCTS ");
    StringBuilder columns = new StringBuilder("(");
    StringBuilder values = new StringBuilder("VALUES (");
    List<Object> params = new ArrayList<>();
    if (createProductMovementDto.getReason() != null) {
      columns.append("REASON, ");
      values.append("?, ");
      params.add(createProductMovementDto.getReason());
    }
    if (createProductMovementDto.getProductId() != null) {
      columns.append("PRODUCTID, ");
      values.append("?, ");
      params.add(createProductMovementDto.getProductId());
    }
    if (createProductMovementDto.getTransactionTypeId() != null) {
      columns.append("TRANSACTIONTYPEID, ");
      values.append("?, ");
      params.add(createProductMovementDto.getTransactionTypeId());
    }
    if (createProductMovementDto.getExpirationDate() != null) {
      columns.append("EXPIRATIONDATE, ");
      values.append("?, ");
      params.add(createProductMovementDto.getExpirationDate());
    }
    if (createProductMovementDto.getUpdateAmount() != null) {
      columns.append("UPDATEAMOUNT, ");
      values.append("?, ");
      params.add(createProductMovementDto.getUpdateAmount());
    }
    if (createProductMovementDto.getUpdateAmount() != null) {
      columns.append("PRICE, ");
      values.append("?, ");
      params.add(createProductMovementDto.getProductPrice());
    }
    if (columns.charAt(columns.length() - 2) == ',') {
      columns.setLength(columns.length() - 2);
      values.setLength(values.length() - 2);
    }
    columns.append(") ");
    values.append(") ");
    sql.append(columns).append(values);
    return new Binds(sql.toString(), params.toArray());
  }
}
