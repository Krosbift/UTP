package com.desktop.views.products.model;

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

  public GetProductDto setProductId(Integer productId, boolean setValue) {
    if (!setValue) {
      return this;
    }
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

  public GetProductDto setProductName(String productName, boolean setValue) {
    if (!setValue) {
      return this;
    }
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

  public GetProductDto setProductCategoryId(Integer productCategoryId, boolean setValue) {
    if (!setValue) {
      return this;
    }
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

  public GetProductDto setProductCategory(String productCategory, boolean setValue) {
    if (!setValue) {
      return this;
    }
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

  public GetProductDto setSupplierId(Integer supplierId, boolean setValue) {
    if (!setValue) {
      return this;
    }
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

  public GetProductDto setSupplierName(String supplierName, boolean setValue) {
    if (!setValue) {
      return this;
    }
    this.supplierName = supplierName;
    return this;
  }

  /**
   * Builds and returns the current instance of GetProductDto.
   *
   * @return the current instance of GetProductDto
   */
  public GetProductDto build() {
    return this;
  }
}
