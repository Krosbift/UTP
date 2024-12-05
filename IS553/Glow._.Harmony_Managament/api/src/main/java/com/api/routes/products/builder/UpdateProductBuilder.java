package com.api.routes.products.builder;

import java.util.ArrayList;
import java.util.List;
import com.api.routes.shared.utils.query.Binds;
import com.api.routes.products.dto.UpdateProductDto;

public class UpdateProductBuilder {
  public static Binds buildUpdateProduc(UpdateProductDto product, int productId) {
    StringBuilder sql = new StringBuilder("UPDATE TB_IMS_PRODUCTS SET ");
    List<Object> params = new ArrayList<>();
    if (product.getProductName() != null) {
      sql.append("NAME = ?, ");
      params.add(product.getProductName());
    }
    if (product.getProductCategoryId() != null) {
      sql.append("CATEGORYID = ?, ");
      params.add(product.getProductCategoryId());
    }
    if (product.getSupplierId() != null) {
      sql.append("SUPPLIERID = ?, ");
      params.add(product.getSupplierId());
    }
    if (sql.charAt(sql.length() - 2) == ',') {
      sql.setLength(sql.length() - 2);
    }
    sql.append(" WHERE PRODUCTID = ?");
    params.add(productId);
    return new Binds(sql.toString(), params.toArray());
  }
}
