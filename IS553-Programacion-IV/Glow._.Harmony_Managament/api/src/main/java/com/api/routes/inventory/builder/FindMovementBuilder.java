package com.api.routes.inventory.builder;

import java.util.ArrayList;
import java.util.List;
import com.api.routes.inventory.dto.GetProductMovementDto;
import com.api.routes.inventory.sql.InventorySql;
import com.api.routes.shared.utils.query.Binds;

public class FindMovementBuilder {
  public static Binds buildFindUpdateProduct(GetProductMovementDto getProductMovementDto) {
    List<Object> binds = new ArrayList<>();
    StringBuilder query = new StringBuilder(InventorySql.FIND_UPDATEPRODUCT.getQuery());
    if (getProductMovementDto.getProductId() != null) {
      query.append(" AND IPT.PRODUCTID = ?");
      binds.add(getProductMovementDto.getProductId());
    }
    if (getProductMovementDto.getTransactionTypeId() != null) {
      query.append(" AND IIT.TRANSACTIONTYPEID = ?");
      binds.add(getProductMovementDto.getTransactionTypeId());
    }
    return new Binds(query.toString(), binds.toArray());
  }
}
