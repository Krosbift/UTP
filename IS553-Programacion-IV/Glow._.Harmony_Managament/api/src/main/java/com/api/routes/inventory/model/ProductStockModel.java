package com.api.routes.inventory.model;

public class ProductStockModel {
  private Integer productId;
  private String productName;
  private String productCategory;
  private Integer stock;
  private Double productPrice;
  private Double totalPrice;
  private String supplierName;

  public Integer getProductId() {
    return productId;
  }

  public String getProductName() {
    return productName;
  }

  public String getProductCategory() {
    return productCategory;
  }

  public Integer getStock() {
    return stock;
  }

  public Double getProductPrice() {
    return productPrice;
  }

  public Double getTotalPrice() {
    return totalPrice;
  }

  public String getSupplierName() {
    return supplierName;
  }

  public ProductStockModel setProductId(Integer productId) {
    this.productId = productId;
    return this;
  }

  public ProductStockModel setProductName(String productName) {
    this.productName = productName;
    return this;
  }

  public ProductStockModel setProductCategory(String productCategory) {
    this.productCategory = productCategory;
    return this;
  }

  public ProductStockModel setStock(Integer stock) {
    this.stock = stock;
    return this;
  }

  public ProductStockModel setProductPrice(Double productPrice) {
    this.productPrice = productPrice;
    return this;
  }

  public ProductStockModel setTotalPrice(Double totalPrice) {
    this.totalPrice = totalPrice;
    return this;
  }

  public ProductStockModel setSupplierName(String supplierName) {
    this.supplierName = supplierName;
    return this;
  }

  public ProductStockModel build() {
    return this;
  }
}
