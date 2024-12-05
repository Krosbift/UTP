package com.desktop.views.products;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import com.desktop.core.navigation.components.Content.ContentPanelController;
import com.desktop.core.utils.interfaces.ComponentInterface;
import com.desktop.core.utils.interfaces.ControllerInterface;
import com.desktop.views.products.components.BottomPanelComponent;
import com.desktop.views.products.components.TopPanelComponent;
import com.desktop.views.products.model.CreateProductDto;
import com.desktop.views.products.model.GetProductDto;
import com.desktop.views.products.model.UpdateProductDto;
import com.desktop.views.products.services.ProductService;
import com.desktop.views.shared.models.SupplierModel;
import com.desktop.views.shared.models.ViewModel;
import com.desktop.views.shared.models.index.ProductCategoryModel;
import com.desktop.views.shared.models.product.ProductModel;

public class ProductsPanelController implements ControllerInterface {
  public ViewModel view;
  public ProductService productService = new ProductService();
  public ContentPanelController parentController;
  public ProductsPanelComponent productsComponent;
  public Map<String, ControllerInterface> childControllers;
  public Map<String, ComponentInterface> childComponents;
  public List<ProductModel> products;
  public List<ProductCategoryModel> productCategories;
  public List<SupplierModel> suppliers;

  public ProductsPanelController(ContentPanelController controller, ViewModel view) {
    this.parentController = controller;
    this.view = view;
    this.products = productService.getProducts(new GetProductDto());
    this.productCategories = findCategories();
    this.suppliers = findSuppliers();
    _initComponent();
    _initChildControllers();
    _initChildComponents();
  }

  @Override
  public void _initComponent() {
    productsComponent = new ProductsPanelComponent(this);
  }

  @Override
  public void _initChildControllers() {
    childControllers = new HashMap<>();
  }

  public void _initChildComponents() {
    childComponents = new HashMap<>();
    childComponents.put("TopPanelComponent", new TopPanelComponent(this, view.getViewName()));
    childComponents.put("BottomPanelComponent", new BottomPanelComponent(this));
  }

  public void setDataTable(GetProductDto getProductDto) {
    try {
      List<ProductModel> products = productService.getProducts(getProductDto);
      ((BottomPanelComponent) childComponents.get("BottomPanelComponent")).createTable(products);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public ProductModel createProduct(CreateProductDto createProductDto) {
    return productService.createProduct(createProductDto);
  }

  public ProductModel updateProduct(UpdateProductDto updateProductDto, Integer productId) {
    return productService.updateProduct(updateProductDto, productId);
  }

  public Integer deleteProduct(Integer productId) {
    return productService.deleteProduct(productId);
  }

  public List<ProductCategoryModel> findCategories() {
    return productService.getAllProductCategories();
  }

  public List<SupplierModel> findSuppliers() {
    return productService.getAllSuppliers();
  }
}
