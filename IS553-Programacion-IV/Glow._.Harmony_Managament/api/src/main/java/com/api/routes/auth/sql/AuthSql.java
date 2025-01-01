package com.api.routes.auth.sql;

public enum AuthSql {
  LOGIN(
    "SELECT " +
      "IUS.USERID, " +
      "IUS.NAMES, " + 
      "IUS.SURNAMES, " +
      "IUS.DOCUMENTTYPEID, " +
      "IDT.DOCUMENTTYPE, " +
      "IUS.DOCUMENTNUMBER, " +
      "IUS.EMAIL, " + 
      "IUS.PASSWORD, " +
      "IUS.PHONENUMBER, " +
      "IUS.ROLETYPEID, " +
      "IRT.ROLETYPE, " +
      "IUS.ADDRESS, " +
      "IUS.ACTIVE " +
    "FROM TB_IMS_USERS IUS " +
      "JOIN TB_IMS_ROLETYPES IRT ON IUS.ROLETYPEID = IRT.ROLETYPEID " +
      "JOIN TB_IMS_DOCUMENTTYPES IDT ON IUS.DOCUMENTTYPEID = IDT.DOCUMENTTYPEID " +
    "WHERE IUS.ACTIVE = 1"
  );

  private final String query;

  AuthSql(String query) {
    this.query = query;
  }

  public String getQuery() {
    return query;
  }
}
