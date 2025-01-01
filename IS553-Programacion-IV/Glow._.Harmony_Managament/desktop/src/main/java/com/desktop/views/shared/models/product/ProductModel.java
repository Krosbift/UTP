package com.desktop.views.shared.models.product;

import com.desktop.views.shared.models.SupplierModel;
import com.desktop.views.shared.models.index.ProductCategoryModel;

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

  public ProductModel setProductName(String productName) {
    this.productName = productName;
    return this;
  }

  public ProductModel setProductCategoryId(Integer productCategoryId) {
    this.productCategoryModel.setProductCategoryId(productCategoryId);
    return this;
  }

  public ProductModel setProductCategory(String productCategory) {
    this.productCategoryModel.setProductCategory(productCategory);
    return this;
  }

  public ProductModel setSupplierId(Integer supplierId) {
    this.supplierModel.setSupplierId(supplierId);
    return this;
  }

  public ProductModel setSupplierName(String supplierName) {
    this.supplierModel.setName(supplierName);
    return this;
  }

  public ProductModel setIsActive(boolean active) {
    this.active = active;
    return this;
  }

  public ProductModel build() {
    return this;
  }
}
