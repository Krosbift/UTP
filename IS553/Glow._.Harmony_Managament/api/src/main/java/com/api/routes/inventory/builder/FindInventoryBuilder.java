package com.api.routes.inventory.builder;

import java.util.ArrayList;
import java.util.List;
import com.api.routes.inventory.dto.GetInventoryDto;
import com.api.routes.inventory.sql.InventorySql;
import com.api.routes.shared.utils.query.Binds;

public class FindInventoryBuilder {
  public static Binds buildFindInventory(GetInventoryDto inventory) {
    List<Object> binds = new ArrayList<>();
    StringBuilder query = new StringBuilder(InventorySql.FIND_INVENTORY.getQuery());
    if (inventory.getProductId() != null) {
      query.append(" AND IPT.PRODUCTID = ?");
      binds.add(inventory.getProductId());
    }
    if (inventory.getCategoryId() != null) {
      query.append(" AND IPT.CATEGORYID = ?");
      binds.add(inventory.getCategoryId());
    }
    if (inventory.getSupplierId() != null) {
      query.append(" AND IPT.SUPPLIERID = ?");
      binds.add(inventory.getSupplierId());
    }
    return new Binds(query.toString(), binds.toArray());
  }
}
