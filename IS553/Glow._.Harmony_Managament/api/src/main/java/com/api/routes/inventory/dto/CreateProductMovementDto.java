package com.api.routes.inventory.dto;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CreateProductMovementDto {
  private String reason;
  private Integer productId;
  private Integer transactionTypeId;
  private Integer updateAmount;
  private Integer productPrice;
  private Date expirationDate;

  public String getReason() {
    return reason;
  }

  public Integer getProductId() {
    return productId;
  }

  public Integer getTransactionTypeId() {
    return transactionTypeId;
  }

  public Integer getUpdateAmount() {
    return updateAmount;
  }

  public Integer getProductPrice() {
    return productPrice;
  }

  public Date getExpirationDate() {
    return expirationDate;
  }

  public CreateProductMovementDto setReason(String reason) {
    this.reason = reason;
    return this;
  }

  public CreateProductMovementDto setReason(ResultSet rs, boolean setValue) throws SQLException {
    if (!setValue) {
      return this;
    }
    this.reason = rs.getString("REASON");
    return this;
  }

  public CreateProductMovementDto setProductId(Integer productId) {
    this.productId = productId;
    return this;
  }

  public CreateProductMovementDto setProductId(ResultSet rs, boolean setValue) throws SQLException {
    if (!setValue) {
      return this;
    }
    this.productId = rs.getInt("PRODUCTID");
    return this;
  }

  public CreateProductMovementDto setTransactionTypeId(Integer transactionTypeId) {
    this.transactionTypeId = transactionTypeId;
    return this;
  }

  public CreateProductMovementDto setTransactionTypeId(ResultSet rs, boolean setValue) throws SQLException {
    if (!setValue) {
      return this;
    }
    this.transactionTypeId = rs.getInt("TRANSACTIONTYPEID");
    return this;
  }

  public CreateProductMovementDto setUpdateAmount(Integer updateAmount) {
    this.updateAmount = updateAmount;
    return this;
  }

  public CreateProductMovementDto setUpdateAmount(ResultSet rs, boolean setValue) throws SQLException {
    if (!setValue) {
      return this;
    }
    this.updateAmount = rs.getInt("UPDATEAMOUNT");
    return this;
  }

  public CreateProductMovementDto setProductPrice(Integer productPrice) {
    this.productPrice = productPrice;
    return this;
  }

  public CreateProductMovementDto setProductPrice(ResultSet rs, boolean setValue) throws SQLException {
    if (!setValue) {
      return this;
    }
    this.productPrice = rs.getInt("PRICE");
    return this;
  }

  public CreateProductMovementDto setExpirationDate(Date expirationDate) {
    this.expirationDate = expirationDate;
    return this;
  }

  public CreateProductMovementDto setExpirationDate(ResultSet rs, boolean setValue) throws SQLException {
    if (!setValue) {
      return this;
    }
    this.expirationDate = rs.getDate("EXPIRATIONDATE");
    return this;
  }

  public CreateProductMovementDto build() {
    return this;
  }
}
