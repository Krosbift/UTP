package com.api.routes.index;

import java.util.List;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.api.routes.shared.models.*;
import com.api.routes.shared.models.index.*;

@RestController
@RequestMapping("index")
@Tag(name = "index")
public class IndexController {
  @Autowired
  private IndexService indexService;

  @GetMapping("views")
  @Operation(summary = "Obtener todas las vistas")
  public List<ViewModel> findAllViews() {
    return indexService.findAllViews();
  }

  @GetMapping("document-types")
  @Operation(summary = "Obtener todos los tipos de documentos")
  public List<DocumentTypeModel> findAllDocumentTypes() {
    return indexService.findAllDocumentTypes();
  }

  @GetMapping("product-categories")
  @Operation(summary = "Obtener todas las categorías de productos")
  public List<ProductCategoryModel> findAllProductCategories() {
    return indexService.findAllProductCategories();
  }

  @GetMapping("role-types")
  @Operation(summary = "Obtener todos los tipos de roles")
  public List<RoleTypeModel> findAllRoleTypes() {
    return indexService.findAllRoleTypes();
  }

  @GetMapping("transaction-types")
  @Operation(summary = "Obtener todos los tipos de transacciones")
  public List<TransactionTypeModel> findAllTransactionTypes() {
    return indexService.findAllTransactionTypes();
  }
}
