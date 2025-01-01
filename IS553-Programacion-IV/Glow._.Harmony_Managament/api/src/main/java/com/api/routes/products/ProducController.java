package com.api.routes.products;

import java.util.List;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import com.api.routes.products.dto.*;
import com.api.routes.shared.models.product.ProductModel;

@RestController
@RequestMapping("products")
@Tag(name = "Productos")
public class ProducController {
  @Autowired
  private ProductService productService;

  @GetMapping("find-product")
  @Operation(summary = "Buscar productos por cualquier valor coincidiente")
  public List<ProductModel> findProduct(
      @RequestParam(required = false) Integer productId,
      @RequestParam(required = false) Integer productCategoryId,
      @RequestParam(required = false) Integer supplierId
  ) {
    GetProductDto getProductDto = new GetProductDto()
        .setProductId(productId)
        .setProductCategoryId(productCategoryId)
        .setSupplierId(supplierId)
        .build();
    return productService.findProduct(getProductDto);
  }

  @GetMapping("find-all-products")
  @Operation(summary = "Buscar todos los productos")
  public List<ProductModel> findAllProducts() {
    return productService.findAllProducts();
  }

  @PostMapping("create-product")
  @Operation(summary = "Crea un nuevo producto")
  public ProductModel createProduct(@RequestBody CreateProductDto createProductDto) {
    return productService.createProduct(createProductDto);
  }

  @PatchMapping("update-product/{productId}")
  @Operation(summary = "Actualizar producto")
  public ProductModel updateProduct(@RequestBody UpdateProductDto updateProductDto, @PathVariable int productId) {
    return productService.updateProduct(updateProductDto, productId);
  }

  @PatchMapping("activate-product/{productId}")
  @Operation(summary = "Activar producto")
  public int activateProduct(@PathVariable int productId) {
    return productService.activeProduct(productId);
  }

  @DeleteMapping("delete-product/{productId}")
  @Operation(summary = "Desactivar producto")
  public int deactivateProduct(@PathVariable int productId) {
    return productService.deleteProduct(productId);
  }
}
