package com.api.routes.inventory.dto;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GetProductMovementDto {
  private Integer productId;
  private String productName;
  private Integer transactionTypeId;
  private String transactionType;

  public Integer getProductId() {
    return productId;
  }

  public String getProductName() {
    return productName;
  }

  public Integer getTransactionTypeId() {
    return transactionTypeId;
  }

  public String getTransactionType() {
    return transactionType;
  }

  public GetProductMovementDto setProductId(Integer productId) {
    this.productId = productId;
    return this;
  }

  public GetProductMovementDto setProductId(ResultSet rs, boolean setValue) throws SQLException {
    if (!setValue) {
      return this;
    }
    this.productId = rs.getInt("PRODUCTID");
    return this;
  }

  public GetProductMovementDto setProductName(String productName) {
    this.productName = productName;
    return this;
  }

  public GetProductMovementDto setProductName(ResultSet rs, boolean setValue) throws SQLException {
    if (!setValue) {
      return this;
    }
    this.productName = rs.getString("PRODUCTNAME");
    return this;
  }

  public GetProductMovementDto setTransactionTypeId(Integer transactionTypeId) {
    this.transactionTypeId = transactionTypeId;
    return this;
  }

  public GetProductMovementDto setTransactionTypeId(ResultSet rs, boolean setValue) throws SQLException {
    if (!setValue) {
      return this;
    }
    this.transactionTypeId = rs.getInt("TRANSACTIONTYPEID");
    return this;
  }

  public GetProductMovementDto setTransactionType(String transactionTypeId) {
    this.transactionType = transactionTypeId;
    return this;
  }

  public GetProductMovementDto setTransactionType(ResultSet rs, boolean setValue) throws SQLException {
    if (!setValue) {
      return this;
    }
    this.transactionType = rs.getString("TRANSACTIONTYPE");
    return this;
  }

  public GetProductMovementDto build() {
    return this;
  }
}
