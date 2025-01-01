package com.desktop.views.inventory.model;

public class GetInventoryDto {
  private String reason;
  private String updateDate;
  private Integer productId;
  private String productName;
  private Integer categoryId;
  private String categoryName;
  private Integer supplierId;
  private String supplierName;
  private Integer transactionTypeId;
  private String transactionType;

  public String getReason() {
    return reason;
  }

  public GetInventoryDto setReason(String reason) {
    this.reason = reason;
    return this;
  }

  public String getUpdateDate() {
    return updateDate;
  }

  public GetInventoryDto setUpdateDate(String updateDate) {
    this.updateDate = updateDate;
    return this;
  }

  public Integer getProductId() {
    return productId;
  }

  public GetInventoryDto setProductId(Integer productId) {
    this.productId = productId;
    return this;
  }

  public String getProductName() {
    return productName;
  }

  public GetInventoryDto setProductName(String productName) {
    this.productName = productName;
    return this;
  }

  public Integer getCategoryId() {
    return categoryId;
  }

  public GetInventoryDto setCategoryId(Integer categoryId) {
    this.categoryId = categoryId;
    return this;
  }

  public String getCategoryName() {
    return categoryName;
  }

  public GetInventoryDto setCategoryName(String categoryName) {
    this.categoryName = categoryName;
    return this;
  }

  public Integer getSupplierId() {
    return supplierId;
  }

  public GetInventoryDto setSupplierId(Integer supplierId) {
    this.supplierId = supplierId;
    return this;
  }

  public String getSupplierName() {
    return supplierName;
  }

  public GetInventoryDto setSupplierName(String supplierName) {
    this.supplierName = supplierName;
    return this;
  }

  public Integer getTransactionTypeId() {
    return transactionTypeId;
  }

  public GetInventoryDto setTransactionTypeId(Integer transactionTypeId) {
    this.transactionTypeId = transactionTypeId;
    return this;
  }

  public String getTransactionType() {
    return transactionType;
  }

  public GetInventoryDto setTransactionType(String transactionType) {
    this.transactionType = transactionType;
    return this;
  }

  /**
   * Builds and returns the current instance of GetInventoryDto.
   *
   * @return the current instance of GetInventoryDto
   */
  public GetInventoryDto build() {
    return this;
  }
}
