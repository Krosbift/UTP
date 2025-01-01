package com.desktop.views.shared.models.index;

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

  public ProductCategoryModel setProductCategory(String productCategory) {
    this.productCategory = productCategory;
    return this;
  }

  public ProductCategoryModel setDescription(String description) {
    this.description = description;
    return this;
  }

  public ProductCategoryModel setActive(boolean active) {
    this.active = active;
    return this;
  }

  public ProductCategoryModel build() {
    return this;
  }
}
