package com.desktop.views.products.model;

public class CreateProductDto {
  private String productName;
  private Integer productCategoryId;
  private Integer productPrice;
  private Integer supplierId;

  public String getProductName() {
    return productName;
  }

  public CreateProductDto setProductName(String productName) {
    this.productName = productName;
    return this;
  }

  public CreateProductDto setProductName(String productName, boolean setValue) {
    if (!setValue) {
      return this;
    }
    this.productName = productName;
    return this;
  }

  public Integer getProductCategoryId() {
    return productCategoryId;
  }

  public CreateProductDto setProductCategoryId(Integer productCategoryId) {
    this.productCategoryId = productCategoryId;
    return this;
  }

  public CreateProductDto setProductCategoryId(Integer productCategoryId, boolean setValue) {
    if (!setValue) {
      return this;
    }
    this.productCategoryId = productCategoryId;
    return this;
  }

  public Integer getProductPrice() {
    return productPrice;
  }

  public CreateProductDto setProductPrice(Integer productPrice) {
    this.productPrice = productPrice;
    return this;
  }

  public CreateProductDto setProductPrice(Integer productPrice, boolean setValue) {
    if (!setValue) {
      return this;
    }
    this.productPrice = productPrice;
    return this;
  }

  public Integer getSupplierId() {
    return supplierId;
  }

  public CreateProductDto setSupplierId(Integer supplierId) {
    this.supplierId = supplierId;
    return this;
  }

  public CreateProductDto setSupplierId(Integer supplierId, boolean setValue) {
    if (!setValue) {
      return this;
    }
    this.supplierId = supplierId;
    return this;
  }

  /**
   * Builds and returns the current instance of CreateProductDto.
   *
   * @return the current instance of CreateProductDto
   */
  public CreateProductDto build() {
    return this;
  }
}
