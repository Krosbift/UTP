package com.api.routes.products.builder;

import java.util.ArrayList;
import java.util.List;
import com.api.routes.products.dto.CreateProductDto;
import com.api.routes.shared.utils.query.Binds;

public class CreateProductBuilder {
  public static Binds buildCreateProduct(CreateProductDto product) {
    StringBuilder sql = new StringBuilder("INSERT INTO TB_IMS_PRODUCTS ");
    StringBuilder columns = new StringBuilder("(");
    StringBuilder values = new StringBuilder("VALUES (");
    List<Object> params = new ArrayList<>();
    if (product.getProductName() != null) {
      columns.append("NAME, ");
      values.append("?, ");
      params.add(product.getProductName());
    }
    if (product.getProductCategoryId() != null) {
      columns.append("CATEGORYID, ");
      values.append("?, ");
      params.add(product.getProductCategoryId());
    }
    if (product.getSupplierId() != null) {
      columns.append("SUPPLIERID, ");
      values.append("?, ");
      params.add(product.getSupplierId());
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
