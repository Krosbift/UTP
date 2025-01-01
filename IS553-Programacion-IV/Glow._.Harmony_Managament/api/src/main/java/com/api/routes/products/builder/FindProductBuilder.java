package com.api.routes.products.builder;

import java.util.ArrayList;
import java.util.List;
import com.api.routes.products.dto.GetProductDto;
import com.api.routes.products.sql.ProducSql;
import com.api.routes.shared.utils.query.Binds;

public class FindProductBuilder {
  public static Binds buildFindProduct(GetProductDto product) {
    List<Object> binds = new ArrayList<>();
    StringBuilder query = new StringBuilder(ProducSql.FIND_PRODUCT.getQuery());
    if (product.getProductId() != null) {
      query.append(" AND IPT.PRODUCTID = ?");
      binds.add(product.getProductId());
    }
    if (product.getProductCategoryId() != null) {
      query.append(" AND IPT.CATEGORYID = ?");
      binds.add(product.getProductCategoryId());
    }
    if (product.getSupplierId() != null) {
      query.append(" AND IPT.SUPPLIERID = ?");
      binds.add(product.getSupplierId());
    }
    return new Binds(query.toString(), binds.toArray());
  }
}
