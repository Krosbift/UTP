package com.api.routes.inventory.model;

public class ProductMinimalStockModel {
  private String productName;
  private String productCategory;
  private Integer stock;

  public String getProductName() {
    return productName;
  }

  public String getProductCategory() {
    return productCategory;
  }

  public Integer getStock() {
    return stock;
  }

  public ProductMinimalStockModel setProductName(String productName) {
    this.productName = productName;
    return this;
  }

  public ProductMinimalStockModel setProductCategory(String productCategory) {
    this.productCategory = productCategory;
    return this;
  }

  public ProductMinimalStockModel setStock(Integer stock) {
    this.stock = stock;
    return this;
  }
}
