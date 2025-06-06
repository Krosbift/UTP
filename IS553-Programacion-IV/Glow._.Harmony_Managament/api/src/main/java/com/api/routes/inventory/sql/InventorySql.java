package com.api.routes.inventory.sql;

public enum InventorySql {
  FIND_UPDATEPRODUCT_BY_ID(
    "SELECT " +
      "IIT.UPDATEPRODUCTID, " +
      "IIT.REASON, " +
      "IIT.UPDATEDATE, " +
      "IIT.PRODUCTID, " +
      "IPT.NAME AS PRODUCTNAME, " +
      "IIT.TRANSACTIONTYPEID, " +
      "ITT.TRANSACTIONTYPE, " +
      "IIT.UPDATEAMOUNT, " +
      "IIT.PRICE, " +
      "IIT.EXPIRATIONDATE, " +
      "IIT.ACTIVE " +
    "FROM TB_IMS_UPDATEPRODUCTS IIT " +
      "JOIN TB_IMS_PRODUCTS IPT ON (IIT.PRODUCTID = IPT.PRODUCTID) " +
      "JOIN TB_IMS_TRANSACTIONTYPES ITT ON (IIT.TRANSACTIONTYPEID = ITT.TRANSACTIONTYPEID) " +
    "WHERE IIT.ACTIVE = 1 AND IIT.UPDATEPRODUCTID = ?"
  ),
  FIND_UPDATEPRODUCT(
    "SELECT " +
      "IIT.UPDATEPRODUCTID, " +
      "IIT.REASON, " +
      "IIT.UPDATEDATE, " +
      "IIT.PRODUCTID, " +
      "IPT.NAME AS PRODUCTNAME, " +
      "IIT.TRANSACTIONTYPEID, " +
      "ITT.TRANSACTIONTYPE, " +
      "IIT.UPDATEAMOUNT, " +
      "IIT.PRICE, " +
      "IIT.EXPIRATIONDATE, " +
      "IIT.ACTIVE " +
    "FROM TB_IMS_UPDATEPRODUCTS IIT " +
      "JOIN TB_IMS_PRODUCTS IPT ON (IIT.PRODUCTID = IPT.PRODUCTID) " +
      "JOIN TB_IMS_TRANSACTIONTYPES ITT ON (IIT.TRANSACTIONTYPEID = ITT.TRANSACTIONTYPEID) " +
    "WHERE IIT.ACTIVE = 1"
  ),
  FIND_ALL_UPDATEPRODUCT(
    "SELECT " +
      "IIT.UPDATEPRODUCTID, " +
      "IIT.REASON, " +
      "IIT.UPDATEDATE, " +
      "IIT.PRODUCTID, " +
      "IPT.NAME AS PRODUCTNAME, " +
      "IIT.TRANSACTIONTYPEID, " +
      "ITT.TRANSACTIONTYPE, " +
      "IIT.UPDATEAMOUNT, " +
      "IIT.PRICE, " +
      "IIT.EXPIRATIONDATE, " +
      "IIT.ACTIVE " +
    "FROM TB_IMS_UPDATEPRODUCTS IIT " +
      "JOIN TB_IMS_PRODUCTS IPT ON (IIT.PRODUCTID = IPT.PRODUCTID) " +
      "JOIN TB_IMS_TRANSACTIONTYPES ITT ON (IIT.TRANSACTIONTYPEID = ITT.TRANSACTIONTYPEID) "
  ),
  ACTIVE_UPDATEPRODUCT(
    "UPDATE TB_IMS_UPDATEPRODUCTS " +
    "SET ACTIVE = 1 " +
    "WHERE UPDATEPRODUCTID = ?"
  ),
  DELETE_UPDATEPRODUCT(
    "UPDATE TB_IMS_UPDATEPRODUCTS " +
    "SET ACTIVE = 0 " +
    "WHERE UPDATEPRODUCTID = ?"
  ),
  FIND_INVENTORY(
    "SELECT " +
      "IPT.UPDATEPRODUCTID, " +
      "IPT.REASON, " +
      "IPT.UPDATEDATE, " +
      "IPT.PRODUCTID, " +
      "IPT.PRODUCTNAME, " +
      "IPT.CATEGORYID, " +
      "IPC.NAME AS CATEGORYNAME, " +
      "IPT.SUPPLIERID, " +
      "ISP.NAME AS SUPPLIERNAME, " +
      "IPT.TRANSACTIONTYPEID, " +
      "IPT.TRANSACTIONTYPE, " +
      "IPT.UPDATEAMOUNT, " +
      "IPT.PRICE, " +
      "IPT.ACTIVE " +
    "FROM ( " +
      "SELECT " +
        "IUP.UPDATEPRODUCTID, " +
        "IUP.REASON, " +
        "IUP.UPDATEDATE, " +
        "IUP.PRODUCTID, " +
        "IPD.NAME AS PRODUCTNAME, " +
        "IPD.CATEGORYID, " +
        "IPD.SUPPLIERID, " +
        "IUP.TRANSACTIONTYPEID, " +
        "ITT.TRANSACTIONTYPE, " +
        "IUP.UPDATEAMOUNT, " +
        "IUP.PRICE, " +
        "IUP.ACTIVE " +
      "FROM TB_IMS_UPDATEPRODUCTS IUP " +
        "JOIN TB_IMS_PRODUCTS IPD ON (IUP.PRODUCTID = IPD.PRODUCTID) " +
        "JOIN TB_IMS_TRANSACTIONTYPES ITT ON (IUP.TRANSACTIONTYPEID = ITT.TRANSACTIONTYPEID) " +
      "WHERE IUP.ACTIVE = 1 " +
    ") IPT " +
      "JOIN TB_IMS_SUPPLIERS ISP ON (IPT.SUPPLIERID = ISP.SUPPLIERID) " +
      "JOIN TB_IMS_PRODUCTCATEGORIES IPC ON (IPT.CATEGORYID = IPC.PRODUCTCATEGORYID) " +
    "WHERE IPT.ACTIVE = 1"
  ),
  FIND_ALL_INVENTORY(
    "SELECT " +
      "IPT.UPDATEPRODUCTID, " +
      "IPT.REASON, " +
      "IPT.UPDATEDATE, " +
      "IPT.PRODUCTID, " +
      "IPT.PRODUCTNAME, " +
      "IPT.CATEGORYID, " +
      "IPC.NAME AS CATEGORYNAME, " +
      "IPT.SUPPLIERID, " +
      "ISP.NAME AS SUPPLIERNAME, " +
      "IPT.TRANSACTIONTYPEID, " +
      "IPT.TRANSACTIONTYPE, " +
      "IPT.UPDATEAMOUNT, " +
      "IUP.PRICE, " +
      "IPT.ACTIVE " +
    "FROM ( " +
      "SELECT " +
        "IUP.UPDATEPRODUCTID, " +
        "IUP.REASON, " +
        "IUP.UPDATEDATE, " +
        "IUP.PRODUCTID, " +
        "IPD.NAME AS PRODUCTNAME, " +
        "IPD.CATEGORYID, " +
        "IPD.SUPPLIERID, " +
        "IUP.TRANSACTIONTYPEID, " +
        "ITT.TRANSACTIONTYPE, " +
        "IUP.UPDATEAMOUNT, " +
        "IUP.PRICE, " +
        "IUP.ACTIVE " +
      "FROM TB_IMS_UPDATEPRODUCTS IUP " +
        "JOIN TB_IMS_PRODUCTS IPD ON (IUP.PRODUCTID = IPD.PRODUCTID) " +
        "JOIN TB_IMS_TRANSACTIONTYPES ITT ON (IUP.TRANSACTIONTYPEID = ITT.TRANSACTIONTYPEID) " +
    ") IPT " +
      "JOIN TB_IMS_SUPPLIERS ISP ON (IPT.SUPPLIERID = ISP.SUPPLIERID) " +
      "JOIN TB_IMS_PRODUCTCATEGORIES IPC ON (IPT.CATEGORYID = IPC.PRODUCTCATEGORYID) "
  );

  private final String sql;

  InventorySql(String sql) {
    this.sql = sql;
  }

  public String getQuery() {
    return sql;
  }
}
