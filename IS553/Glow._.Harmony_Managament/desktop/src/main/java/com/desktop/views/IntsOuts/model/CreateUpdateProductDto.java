package com.desktop.views.IntsOuts.model;

import java.util.Date;

public class CreateUpdateProductDto {
  private String reason;
  private Integer productId;
  private Integer transactionTypeId;
  private Integer updateAmount;
  private Integer productPrice;
  private Date expirationDate;

  public String getReason() {
    return reason;
  }

  public CreateUpdateProductDto setReason(String reason) {
    this.reason = reason;
    return this;
  }

  public Integer getProductId() {
    return productId;
  }

  public CreateUpdateProductDto setProductId(Integer productId) {
    this.productId = productId;
    return this;
  }

  public Integer getTransactionTypeId() {
    return transactionTypeId;
  }

  public CreateUpdateProductDto setTransactionTypeId(Integer transactionTypeId) {
    this.transactionTypeId = transactionTypeId;
    return this;
  }

  public Integer getUpdateAmount() {
    return updateAmount;
  }

  public CreateUpdateProductDto setUpdateAmount(Integer updateAmount) {
    this.updateAmount = updateAmount;
    return this;
  }

  public Integer getProductPrice() {
    return productPrice;
  }

  public CreateUpdateProductDto setProductPrice(Integer productPrice) {
    this.productPrice = productPrice;
    return this;
  }

  public Date getExpirationDate() {
    return expirationDate;
  }

  public CreateUpdateProductDto setExpirationDate(Date date) {
    this.expirationDate = date;
    return this;
  }

  /**
   * Builds and returns the current instance of CreateUpdateProductDto.
   *
   * @return the current instance of CreateUpdateProductDto
   */
  public CreateUpdateProductDto build() {
    return this;
  }
}
