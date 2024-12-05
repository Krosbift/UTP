package com.api.routes.inventory.dto;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GetInventoryDto {
  private Integer productId;
  private String productName;
  private Integer categoryId;
  private String categoryName;
  private Integer supplierId;
  private String supplierName;

  public Integer getProductId() {
    return productId;
  }

  public String getProductName() {
    return productName;
  }

  public Integer getCategoryId() {
    return categoryId;
  }

  public String getCategoryName() {
    return categoryName;
  }

  public Integer getSupplierId() {
    return supplierId;
  }

  public String getSupplierName() {
    return supplierName;
  }

  public GetInventoryDto setProductId(Integer productId) {
    this.productId = productId;
    return this;
  }

  public GetInventoryDto setProductId(ResultSet rs, boolean setValue) throws SQLException {
    if (!setValue) {
      return this;
    }
    this.productId = rs.getInt("PRODUCTID");
    return this;
  }

  public GetInventoryDto setProductName(String productName) {
    this.productName = productName;
    return this;
  }

  public GetInventoryDto setProductName(ResultSet rs, boolean setValue) throws SQLException {
    if (!setValue) {
      return this;
    }
    this.productName = rs.getString("PRODUCTNAME");
    return this;
  }

  public GetInventoryDto setCategoryId(Integer categoryId) {
    this.categoryId = categoryId;
    return this;
  }

  public GetInventoryDto setCategoryId(ResultSet rs, boolean setValue) throws SQLException {
    if (!setValue) {
      return this;
    }
    this.categoryId = rs.getInt("CATEGORYID");
    return this;
  }

  public GetInventoryDto setCategoryName(String categoryName) {
    this.categoryName = categoryName;
    return this;
  }

  public GetInventoryDto setCategoryName(ResultSet rs, boolean setValue) throws SQLException {
    if (!setValue) {
      return this;
    }
    this.categoryName = rs.getString("CATEGORYNAME");
    return this;
  }

  public GetInventoryDto setSupplierId(Integer supplierId) {
    this.supplierId = supplierId;
    return this;
  }

  public GetInventoryDto setSupplierId(ResultSet rs, boolean setValue) throws SQLException {
    if (!setValue) {
      return this;
    }
    this.supplierId = rs.getInt("SUPPLIERID");
    return this;
  }

  public GetInventoryDto setSupplierName(String supplierName) {
    this.supplierName = supplierName;
    return this;
  }

  public GetInventoryDto setSupplierName(ResultSet rs, boolean setValue) throws SQLException {
    if (!setValue) {
      return this;
    }
    this.supplierName = rs.getString("SUPPLIERNAME");
    return this;
  }

  public GetInventoryDto build() {
    return this;
  }
}
