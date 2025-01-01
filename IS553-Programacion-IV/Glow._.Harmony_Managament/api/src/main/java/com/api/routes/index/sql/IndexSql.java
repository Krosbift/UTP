package com.api.routes.index.sql;

public enum IndexSql {
  FIND_VIEWS(
    "SELECT " +
      "IV.VIEWID, " +
      "IV.NAME, " +
      "IV.DESCRIPTION, " +
      "IV.ACTIVE " +
    "FROM TB_IMS_VIEWS IV " +
    "WHERE ACTIVE = 1"
  ),
  FIND_DOCUMENT_TYPES(
    "SELECT " +
      "IDT.DOCUMENTTYPEID, " +
      "IDT.DOCUMENTTYPE, " +
      "IDT.DESCRIPTION, " +
      "IDT.ACTIVE " +
    "FROM TB_IMS_DOCUMENTTYPES IDT " +
    "WHERE ACTIVE = 1"
  ),
  FIND_PRODUCT_CATEGORIES(
    "SELECT " +
      "IPC.PRODUCTCATEGORYID, " +
      "IPC.NAME, " +
      "IPC.DESCRIPTION, " +
      "IPC.ACTIVE " +
    "FROM TB_IMS_PRODUCTCATEGORIES IPC " +
    "WHERE ACTIVE = 1"
  ),
  FIND_ROLE_TYPES(
    "SELECT " +
      "IRT.ROLETYPEID, " +
      "IRT.ROLETYPE, " +
      "IRT.DESCRIPTION, " +
      "IRT.ACTIVE " +
    "FROM TB_IMS_ROLETYPES IRT " +
    "WHERE ACTIVE = 1"
  ),
  FIND_TRANSACTION_TYPES(
    "SELECT " +
      "ITT.TRANSACTIONTYPEID, " +
      "ITT.TRANSACTIONTYPE, " +
      "ITT.DESCRIPTION, " +
      "ITT.ACTIVE " +
    "FROM TB_IMS_TRANSACTIONTYPES ITT " +
    "WHERE ACTIVE = 1"
  );

  private String sql;

  IndexSql(String sql) {
    this.sql = sql;
  }
  
  public String getSql() {
    return sql;
  }
}
