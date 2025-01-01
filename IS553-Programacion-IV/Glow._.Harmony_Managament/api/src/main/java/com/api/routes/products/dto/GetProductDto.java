package com.api.routes.products.dto;

public class GetProductDto {
  private Integer productId;
  private String productName;
  private Integer productCategoryId;
  private String productCategory;
  private Integer supplierId;
  private String supplierName;

  public Integer getProductId() {
    return productId;
  }

  public GetProductDto setProductId(Integer productId) {
    this.productId = productId;
    return this;
  }

  public String getProductName() {
    return productName;
  }

  public GetProductDto setProductName(String productName) {
    this.productName = productName;
    return this;
  }

  public Integer getProductCategoryId() {
    return productCategoryId;
  }

  public GetProductDto setProductCategoryId(Integer productCategoryId) {
    this.productCategoryId = productCategoryId;
    return this;
  }

  public String getProductCategory() {
    return productCategory;
  }

  public GetProductDto setProductCategory(String productCategory) {
    this.productCategory = productCategory;
    return this;
  }

  public Integer getSupplierId() {
    return supplierId;
  }

  public GetProductDto setSupplierId(Integer supplierId) {
    this.supplierId = supplierId;
    return this;
  }

  public String getSupplierName() {
    return supplierName;
  }

  public GetProductDto setSupplierName(String supplierName) {
    this.supplierName = supplierName;
    return this;
  }

  public GetProductDto build() {
    return this;
  }
}
