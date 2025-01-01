package com.api.routes.shared.mappers.index;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

import com.api.routes.shared.models.index.DocumentTypeModel;
import com.api.routes.shared.utils.methods.HasColumns;

public class DocumentTypeMapper {
  public static RowMapper<DocumentTypeModel> documentTypesRowMapper = new RowMapper<DocumentTypeModel>() {
    @Override
    public DocumentTypeModel mapRow(@SuppressWarnings("null") ResultSet rs, int rowNum) throws SQLException {
      DocumentTypeModel documentType = new DocumentTypeModel()
          .setDocumentTypeId(rs, HasColumns.verify(rs, "DOCUMENTTYPEID"))
          .setDocumentType(rs, HasColumns.verify(rs, "DOCUMENTTYPE"))
          .setDescription(rs, HasColumns.verify(rs, "DESCRIPTION"))
          .setActive(rs, HasColumns.verify(rs, "ACTIVE"))
          .build();
      return documentType;
    }
  };
}
