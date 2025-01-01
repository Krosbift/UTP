package com.desktop.views.shared.models.index;

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

  public DocumentTypeModel setDocumentType(String documentType) {
    this.documentType = documentType;
    return this;
  }

  public DocumentTypeModel setDescription(String description) {
    this.description = description;
    return this;
  }

  public DocumentTypeModel setActive(boolean active) {
    this.active = active;
    return this;
  }

  public DocumentTypeModel build() {
    return this;
  }
}
