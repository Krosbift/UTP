package com.desktop.views.IntsOuts.model;

public class GetUpdateProductDto {
  private Integer productId;
  private String productName;
  private Integer transactionTypeId;
  private String transactionType;

  public Integer getProductId() {
    return productId;
  }

  public GetUpdateProductDto setProductId(Integer productId) {
    this.productId = productId;
    return this;
  }

  public String getProductName() {
    return productName;
  }

  public GetUpdateProductDto setProductName(String productName) {
    this.productName = productName;
    return this;
  }

  public Integer getTransactionTypeId() {
    return transactionTypeId;
  }

  public GetUpdateProductDto setTransactionTypeId(Integer transactionTypeId) {
    this.transactionTypeId = transactionTypeId;
    return this;
  }

  public String getTransactionType() {
    return transactionType;
  }

  public GetUpdateProductDto setTransactionType(String transactionTypeId) {
    this.transactionType = transactionTypeId;
    return this;
  }

  /**
   * Builds and returns the current instance of GetUpdateProductDto.
   *
   * @return the current instance of GetUpdateProductDto
   */
  public GetUpdateProductDto build() {
    return this;
  }
}
