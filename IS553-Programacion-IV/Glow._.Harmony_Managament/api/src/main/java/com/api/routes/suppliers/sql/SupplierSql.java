package com.api.routes.suppliers.sql;

public enum SupplierSql {
  FIND_SUPPLIER_BY_ID(
    "SELECT " +
      "ISL.SUPPLIERID, " +
      "ISL.NAME, " +
      "ISL.ADDRESS, " +
      "ISL.PHONENUMBER, " +
      "ISL.ACTIVE " +
    "FROM TB_IMS_SUPPLIERS ISL " +
    "WHERE ISL.SUPPLIERID = ?"
  ),
  FIND_SUPPLIER(
    "SELECT " +
      "ISL.SUPPLIERID, " +
      "ISL.NAME, " +
      "ISL.ADDRESS, " +
      "ISL.PHONENUMBER, " +
      "ISL.ACTIVE " +
    "FROM TB_IMS_SUPPLIERS ISL " +
    "WHERE ISL.ACTIVE = 1"
  ),
  FIND_ALL_SUPPLIERS(
    "SELECT " +
      "ISL.SUPPLIERID, " +
      "ISL.NAME, " +
      "ISL.ADDRESS, " +
      "ISL.PHONENUMBER, " +
      "ISL.ACTIVE " +
    "FROM TB_IMS_SUPPLIERS ISL"
  ),
  ACTIVE_SUPPLIER(
    "UPDATE TB_IMS_SUPPLIERS " +
    "SET ACTIVE = 1 " +
    "WHERE SUPPLIERID = ?"
  ),
  DELETE_SUPPLIER(
    "UPDATE TB_IMS_SUPPLIERS " +
    "SET ACTIVE = 0 " +
    "WHERE SUPPLIERID = ?"
  );

  private final String query;

  SupplierSql(String query) {
    this.query = query;
  }

  public String getQuery() {
    return query;
  }
}
