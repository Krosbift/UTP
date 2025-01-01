package com.api.routes.shared.mappers.product;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import com.api.routes.shared.utils.methods.HasColumns;
import com.api.routes.shared.models.product.ProductModel;

public class ProductMapper {
  public static RowMapper<ProductModel> productRowMapper = new RowMapper<ProductModel>() {
    @Override
    public ProductModel mapRow(@SuppressWarnings("null") ResultSet rs, int rowNum) throws SQLException {
      ProductModel product = new ProductModel()
          .setProductId(rs, HasColumns.verify(rs, "PRODUCTID"))
          .setProductName(rs, HasColumns.verify(rs, "PRODUCTNAME"))
          .setProductCategoryModel()
          .setProductCategoryId(rs, HasColumns.verify(rs, "CATEGORYID"))
          .setProductCategory(rs, HasColumns.verify(rs, "CATEGORYNAME"))
          .setSupplierModel()
          .setSupplierId(rs, HasColumns.verify(rs, "SUPPLIERID"))
          .setSupplierName(rs, HasColumns.verify(rs, "SUPPLIERNAME"))
          .setIsActive(rs, HasColumns.verify(rs, "ACTIVE"))
          .build();
      return product;
    }
  };
}
