package com.api.routes.shared.models.index;

import java.sql.ResultSet;
import java.sql.SQLException;

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

  public TransactionTypeModel setTransactionTypeId(ResultSet rs, boolean setValue) throws SQLException {
    if (setValue) {
      this.transactionTypeId = rs.getInt("TRANSACTIONTYPEID");
    }
    return this;
  }

  public TransactionTypeModel setTransactionType(String transactionType) {
    this.transactionType = transactionType;
    return this;
  }

  public TransactionTypeModel setTransactionType(ResultSet rs, boolean setValue) throws SQLException {
    if (setValue) {
      this.transactionType = rs.getString("TRANSACTIONTYPE");
    }
    return this;
  }

  public TransactionTypeModel setDescription(String description) {
    this.description = description;
    return this;
  }

  public TransactionTypeModel setDescription(ResultSet rs, boolean setValue) throws SQLException {
    if (setValue) {
      this.description = rs.getString("DESCRIPTION");
    }
    return this;
  }

  public TransactionTypeModel setActive(boolean active) {
    this.active = active;
    return this;
  }

  public TransactionTypeModel setActive(ResultSet rs, boolean setValue) throws SQLException {
    if (setValue) {
      this.active = rs.getBoolean("ACTIVE");
    }
    return this;
  }

  public TransactionTypeModel build() {
    return this;
  }
}
