package com.desktop.views.shared.models.index;

public class TransactionTypeModel {
  private Integer transactionTypeId;
  private String transactionType;
  private String description;
  private boolean active;

  public Integer getTransactionTypeId() {
    return transactionTypeId;
  }

  public String getTransactionType() {
    return transactionType;
  }

  public String getDescription() {
    return description;
  }

  public boolean getActive() {
    return active;
  }

  public TransactionTypeModel setTransactionTypeId(Integer transactionTypeId) {
    this.transactionTypeId = transactionTypeId;
    return this;
  }

  public TransactionTypeModel setTransactionType(String transactionType) {
    this.transactionType = transactionType;
    return this;
  }

  public TransactionTypeModel setDescription(String description) {
    this.description = description;
    return this;
  }

  public TransactionTypeModel setActive(boolean active) {
    this.active = active;
    return this;
  }

  public TransactionTypeModel build() {
    return this;
  }
}
