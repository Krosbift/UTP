package com.api.routes.shared.models.product;

import java.sql.ResultSet;
import java.sql.SQLException;
import com.api.routes.shared.models.SupplierModel;
import com.api.routes.shared.models.index.ProductCategoryModel;

public class ProductModel {
  private Integer productId;
  private String productName;
  private ProductCategoryModel productCategoryModel;
  private SupplierModel supplierModel;
  private boolean active;

  public Integer getProductId() {
    return productId;
  }

  public String getProductName() {
    return productName;
  }

  public ProductCategoryModel getProductCategoryModel() {
    return productCategoryModel;
  }

  public SupplierModel getSupplierModel() {
    return supplierModel;
  }

  public boolean getIsActive() {
    return active;
  }

  public ProductModel setProductCategoryModel() {
    this.productCategoryModel = new ProductCategoryModel();
    return this;
  }

  public ProductModel setSupplierModel() {
    this.supplierModel = new SupplierModel();
    return this;
  }

  public ProductModel setProductId(Integer productId) {
    this.productId = productId;
    return this;
  }

  public ProductModel setProductId(ResultSet rs, boolean setValue) throws SQLException {
    if (setValue) {
      this.productId = rs.getInt("PRODUCTID");
    }
    return this;
  }

  public ProductModel setProductName(String productName) {
    this.productName = productName;
    return this;
  }

  public ProductModel setProductName(ResultSet rs, boolean setValue) throws SQLException {
    if (setValue) {
      this.productName = rs.getString("PRODUCTNAME");
    }
    return this;
  }

  public ProductModel setProductCategoryId(Integer productCategoryId) {
    this.productCategoryModel.setProductCategoryId(productCategoryId);
    return this;
  }

  public ProductModel setProductCategoryId(ResultSet rs, boolean setValue) throws SQLException {
    if (setValue) {
      this.productCategoryModel.setProductCategoryId(rs.getInt("CATEGORYID"));
    }
    return this;
  }

  public ProductModel setProductCategory(String productCategory) {
    this.productCategoryModel.setProductCategory(productCategory);
    return this;
  }

  public ProductModel setProductCategory(ResultSet rs, boolean setValue) throws SQLException {
    if (setValue) {
      this.productCategoryModel.setProductCategory(rs.getString("CATEGORYNAME"));
    }
    return this;
  }

  public ProductModel setSupplierId(Integer supplierId) {
    this.supplierModel.setSupplierId(supplierId);
    return this;
  }

  public ProductModel setSupplierId(ResultSet rs, boolean setValue) throws SQLException {
    if (setValue) {
      this.supplierModel.setSupplierId(rs.getInt("SUPPLIERID"));
    }
    return this;
  }

  public ProductModel setSupplierName(String supplierName) {
    this.supplierModel.setName(supplierName);
    return this;
  }

  public ProductModel setSupplierName(ResultSet rs, boolean setValue) throws SQLException {
    if (setValue) {
      this.supplierModel.setName(rs.getString("SUPPLIERNAME"));
    }
    return this;
  }

  public ProductModel setIsActive(boolean active) {
    this.active = active;
    return this;
  }

  public ProductModel setIsActive(ResultSet rs, boolean setValue) throws SQLException {
    if (setValue) {
      this.active = rs.getBoolean("ACTIVE");
    }
    return this;
  }

  public ProductModel build() {
    return this;
  }
}
