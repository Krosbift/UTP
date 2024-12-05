package com.api.routes.shared.models.index;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DocumentTypeModel {
  private Integer documentTypeId;
  private String documentType;
  private String description;
  private boolean active;

  public Integer getDocumentTypeId() {
    return documentTypeId;
  }

  public String getDocumentType() {
    return documentType;
  }

  public String getDescription() {
    return description;
  }

  public boolean getActive() {
    return active;
  }

  public DocumentTypeModel setDocumentTypeId(Integer documentTypeId) {
    this.documentTypeId = documentTypeId;
    return this;
  }

  public DocumentTypeModel setDocumentTypeId(ResultSet rs, boolean setValue) throws SQLException {
    if (setValue) {
      this.documentTypeId = rs.getInt("DOCUMENTTYPEID");
    }
    return this;
  }

  public DocumentTypeModel setDocumentType(String documentType) {
    this.documentType = documentType;
    return this;
  }

  public DocumentTypeModel setDocumentType(ResultSet rs, boolean setValue) throws SQLException {
    if (setValue) {
      this.documentType = rs.getString("DOCUMENTTYPE");
    }
    return this;
  }

  public DocumentTypeModel setDescription(String description) {
    this.description = description;
    return this;
  }

  public DocumentTypeModel setDescription(ResultSet rs, boolean setValue) throws SQLException {
    if (setValue) {
      this.description = rs.getString("DESCRIPTION");
    }
    return this;
  }

  public DocumentTypeModel setActive(boolean active) {
    this.active = active;
    return this;
  }

  public DocumentTypeModel setActive(ResultSet rs, boolean setValue) throws SQLException {
    if (setValue) {
      this.active = rs.getBoolean("ACTIVE");
    }
    return this;
  }

  public DocumentTypeModel build() {
    return this;
  }
}
