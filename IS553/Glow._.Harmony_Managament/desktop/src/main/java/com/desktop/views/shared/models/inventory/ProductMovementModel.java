package com.desktop.views.shared.models.inventory;

import java.sql.Date;
import com.desktop.views.shared.models.product.ProductModel;
import com.desktop.views.shared.models.index.TransactionTypeModel;

public class ProductMovementModel {
  private Integer updateProductId;
  private String reason;
  private Date updateDate;
  private ProductModel productModel;
  private TransactionTypeModel transactionTypeModel;
  private Integer updateAmount;
  private Integer productPrice;
  private Date expirationDate;
  private boolean active;

  public Integer getUpdateProductId() {
    return updateProductId;
  }

  public String getReason() {
    return reason;
  }

  public Date getUpdateDate() {
    return updateDate;
  }

  public ProductModel getProductModel() {
    return productModel;
  }

  public TransactionTypeModel getTransactionTypeModel() {
    return transactionTypeModel;
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

  public boolean getActive() {
    return active;
  }

  public ProductMovementModel setProductModel() {
    this.productModel = new ProductModel();
    return this;
  }

  public ProductMovementModel setTransactionTypeModel() {
    this.transactionTypeModel = new TransactionTypeModel();
    return this;
  }

  public ProductMovementModel setUpdateProductId(Integer updateProductId) {
    this.updateProductId = updateProductId;
    return this;
  }

  public ProductMovementModel setReason(String reason) {
    this.reason = reason;
    return this;
  }

  public ProductMovementModel setUpdateDate(Date updateDate) {
    this.updateDate = updateDate;
    return this;
  }

  public ProductMovementModel setProductId(Integer productId) {
    this.productModel.setProductId(productId);
    return this;
  }

  public ProductMovementModel setProductName(String productName) {
    this.productModel.setProductName(productName);
    return this;
  }

  public ProductMovementModel setTransactionTypeId(Integer productCategory) {
    this.transactionTypeModel.setTransactionTypeId(productCategory);
    return this;
  }

  public ProductMovementModel setTransactionType(String transactionType) {
    this.transactionTypeModel.setTransactionType(transactionType);
    return this;
  }

  public ProductMovementModel setUpdateAmount(Integer updateAmount) {
    this.updateAmount = updateAmount;
    return this;
  }

  public ProductMovementModel setProductPrice(Integer productPrice) {
    this.productPrice = productPrice;
    return this;
  }

  public ProductMovementModel setExpirationDate(Date expirationDate) {
    this.expirationDate = expirationDate;
    return this;
  }

  public ProductMovementModel setActive(boolean active) {
    this.active = active;
    return this;
  }

  public ProductMovementModel build() {
    return this;
  }
}
