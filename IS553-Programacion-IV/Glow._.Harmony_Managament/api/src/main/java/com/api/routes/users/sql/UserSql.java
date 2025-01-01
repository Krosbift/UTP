package com.api.routes.users.sql;

public enum UserSql {
  FIND_USER_BY_ID(
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
    "WHERE IUS.USERID = ?"
  ),
  FIND_USER(
    "SELECT " +
      "IUS.USERID, " +
      "IUS.NAMES, " + 
      "IUS.SURNAMES, " +
      "IUS.DOCUMENTTYPEID, " +
      "IDT.DOCUMENTTYPE, " +
      "IUS.DOCUMENTNUMBER, " +
      "IUS.EMAIL, " + 
      "IUS.PHONENUMBER, " +
      "IUS.ROLETYPEID, " +
      "IRT.ROLETYPE, " +
      "IUS.ADDRESS, " +
      "IUS.ACTIVE " +
    "FROM TB_IMS_USERS IUS " +
    "JOIN TB_IMS_ROLETYPES IRT ON IUS.ROLETYPEID = IRT.ROLETYPEID " +
    "JOIN TB_IMS_DOCUMENTTYPES IDT ON IUS.DOCUMENTTYPEID = IDT.DOCUMENTTYPEID " +
    "WHERE IUS.ACTIVE = 1"
  ),
  FIND_ALL_USERS(
    "SELECT " +
      "IUS.USERID, " +
      "IUS.NAMES, " + 
      "IUS.SURNAMES, " +
      "IUS.DOCUMENTTYPEID, " +
      "IDT.DOCUMENTTYPE, " +
      "IUS.DOCUMENTNUMBER, " +
      "IUS.EMAIL, " + 
      "IUS.PHONENUMBER, " +
      "IUS.ROLETYPEID, " +
      "IRT.ROLETYPE, " +
      "IUS.ADDRESS, " +
      "IUS.ACTIVE " +
    "FROM TB_IMS_USERS IUS " +
    "JOIN TB_IMS_ROLETYPES IRT ON IUS.ROLETYPEID = IRT.ROLETYPEID " +
    "JOIN TB_IMS_DOCUMENTTYPES IDT ON IUS.DOCUMENTTYPEID = IDT.DOCUMENTTYPEID"
  ),
  FIND_LOGIN_USER(
    "SELECT " +
      "IUS.EMAIL, " + 
      "IUS.PASSWORD " +
    "FROM TB_IMS_USERS IUS " +
    "WHERE IUS.ACTIVE = 1"
  ),
  ACTIVE_USER(
    "UPDATE TB_IMS_USERS " +
    "SET ACTIVE = 1 " +
    "WHERE USERID = ?"
  ),
  DELETE_USER(
    "UPDATE TB_IMS_USERS " +
    "SET ACTIVE = 0 " +
    "WHERE USERID = ?"
  );

  private final String query;

  UserSql(String query) {
    this.query = query;
  }
  
  public String getQuery() {
    return query;
  }
}
