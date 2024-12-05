package com.api.routes.shared.models.index;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductCategoryModel {
  private Integer productCategoryId;
  private String productCategory;
  private String description;
  private boolean active;

  public Integer getProductCategoryId() {
    return productCategoryId;
  }

  public String getProductCategory() {
    return productCategory;
  }

  public String getDescription() {
    return description;
  }

  public boolean getActive() {
    return active;
  }

  public ProductCategoryModel setProductCategoryId(Integer productCategoryId) {
    this.productCategoryId = productCategoryId;
    return this;
  }

  public ProductCategoryModel setProductCategoryId(ResultSet rs, boolean setValue) throws SQLException {
    if (setValue) {
      this.productCategoryId = rs.getInt("PRODUCTCATEGORYID");
    }
    return this;
  }

  public ProductCategoryModel setProductCategory(String productCategory) {
    this.productCategory = productCategory;
    return this;
  }

  public ProductCategoryModel setProductCategory(ResultSet rs, boolean setValue) throws SQLException {
    if (setValue) {
      this.productCategory = rs.getString("NAME");
    }
    return this;
  }

  public ProductCategoryModel setDescription(String description) {
    this.description = description;
    return this;
  }

  public ProductCategoryModel setDescription(ResultSet rs, boolean setValue) throws SQLException {
    if (setValue) {
      this.description = rs.getString("DESCRIPTION");
    }
    return this;
  }

  public ProductCategoryModel setActive(boolean active) {
    this.active = active;
    return this;
  }

  public ProductCategoryModel setActive(ResultSet rs, boolean setValue) throws SQLException {
    if (setValue) {
      this.active = rs.getBoolean("ACTIVE");
    }
    return this;
  }

  public ProductCategoryModel build() {
    return this;
  }
}
