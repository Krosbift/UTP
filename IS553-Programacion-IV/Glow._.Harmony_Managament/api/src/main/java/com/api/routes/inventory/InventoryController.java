package com.api.routes.inventory;

import java.util.List;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import com.api.routes.inventory.dto.CreateProductMovementDto;
import com.api.routes.inventory.dto.GetInventoryDto;
import com.api.routes.inventory.dto.GetProductMovementDto;
import com.api.routes.inventory.dto.UpdateProductMovementDto;
import com.api.routes.inventory.model.ProductMinimalStockModel;
import com.api.routes.inventory.model.ProductStockModel;
import com.api.routes.shared.models.inventory.InventoryModel;
import com.api.routes.shared.models.inventory.ProductMovementModel;

@RestController
@RequestMapping("inventory")
@Tag(name = "Inventario")
public class InventoryController {
  @Autowired
  private InventoryService inventoryService;

  @GetMapping("find-movement-product")
  @Operation(summary = "Buscar productos actualizados por cualquier valor coincidiente")
  public List<ProductMovementModel> findUpdateProduct(@RequestParam(required = false) Integer productId,
      @RequestParam(required = false) Integer transactionTypeId) {
    GetProductMovementDto getUpdateProductDto = new GetProductMovementDto()
        .setProductId(productId)
        .setTransactionTypeId(transactionTypeId)
        .build();
    return inventoryService.findUpdateProduct(getUpdateProductDto);
  }

  @GetMapping("find-all-movement-products")
  @Operation(summary = "Buscar todos los productos actualizados")
  public List<ProductMovementModel> findAllUpdateProducts() {
    return inventoryService.findAllUpdateProducts();
  }

  @PostMapping("create-movement-product")
  @Operation(summary = "Crea un nuevo producto actualizado")
  public ProductMovementModel createUpdateProduct(@RequestBody CreateProductMovementDto createProductMovementDto) {
    return inventoryService.createUpdateProduct(createProductMovementDto);
  }

  @PatchMapping("update-movement-product/{updateProductId}")
  @Operation(summary = "Actualizar producto actualizado")
  public ProductMovementModel updateUpdateProduct(@RequestBody UpdateProductMovementDto updateProductMovementDto,
      @PathVariable int updateProductId) {
    return inventoryService.updateUpdateProduct(updateProductMovementDto, updateProductId);
  }

  @PatchMapping("activate-movement-product/{productId}")
  @Operation(summary = "Activar registro de producto")
  public int activateProduct(@PathVariable int productId) {
    return inventoryService.activeUpdateProduct(productId);
  }

  @DeleteMapping("delete-movement-product/{updateProductId}")
  @Operation(summary = "Eliminar producto actualizado")
  public int deleteUpdateProduct(@PathVariable int updateProductId) {
    return inventoryService.deleteUpdateProduct(updateProductId);
  }

  @GetMapping("find-inventory")
  @Operation(summary = "Buscar inventario por cualquier valor coincidiente")
  public List<ProductStockModel> findInventory(
      @RequestParam(required = false) Integer productId,
      @RequestParam(required = false) Integer categoryId,
      @RequestParam(required = false) Integer supplierId) {
    GetInventoryDto getInventoryDto = new GetInventoryDto()
        .setProductId(productId)
        .setCategoryId(categoryId)
        .setSupplierId(supplierId)
        .build();
    return inventoryService.findInventory(getInventoryDto);
  }

  @GetMapping("find-all-inventory")
  @Operation(summary = "Buscar todo el inventario")
  public List<InventoryModel> findAllInventory() {
    return inventoryService.findAllInventory();
  }

  @GetMapping("find-minimal-products")
  @Operation(summary = "Buscar productos con stock por debajo del minimo")
  public List<ProductMinimalStockModel> findProductsMinimals(
      @RequestParam(required = false) Integer productId,
      @RequestParam(required = false) Integer categoryId,
      @RequestParam(required = false) Integer supplierId) {
    GetInventoryDto getInventoryDto = new GetInventoryDto()
        .setProductId(productId)
        .setCategoryId(categoryId)
        .setSupplierId(supplierId)
        .build();
    return inventoryService.findProductsMinimals(getInventoryDto);
  }

  @PostMapping("order-product")
  public ProductMovementModel orderProduct(@RequestParam(required = true) String productName) {
    return inventoryService.orderProduct(productName);
  }
}
