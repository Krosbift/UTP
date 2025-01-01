package com.api.routes.shared.mappers.index;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

import com.api.routes.shared.models.index.ProductCategoryModel;
import com.api.routes.shared.utils.methods.HasColumns;

public class ProductCategoryMapper {
  public static RowMapper<ProductCategoryModel> productCategoriesRowMapper = new RowMapper<ProductCategoryModel>() {
    @Override
    public ProductCategoryModel mapRow(@SuppressWarnings("null") ResultSet rs, int rowNum) throws SQLException {
      ProductCategoryModel productCategory = new ProductCategoryModel()
          .setProductCategoryId(rs, HasColumns.verify(rs, "PRODUCTCATEGORYID"))
          .setProductCategory(rs, HasColumns.verify(rs, "NAME"))
          .setDescription(rs, HasColumns.verify(rs, "DESCRIPTION"))
          .setActive(rs, HasColumns.verify(rs, "ACTIVE"))
          .build();
      return productCategory;
    }
  };
}
