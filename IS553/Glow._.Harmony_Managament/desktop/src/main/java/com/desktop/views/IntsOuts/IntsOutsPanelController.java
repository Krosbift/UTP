package com.desktop.views.IntsOuts;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import com.desktop.core.navigation.components.Content.ContentPanelController;
import com.desktop.core.utils.interfaces.ComponentInterface;
import com.desktop.core.utils.interfaces.ControllerInterface;
import com.desktop.views.IntsOuts.components.BottomPanelComponent;
import com.desktop.views.IntsOuts.components.TopPanelComponent;
import com.desktop.views.IntsOuts.model.CreateUpdateProductDto;
import com.desktop.views.IntsOuts.model.GetUpdateProductDto;
import com.desktop.views.IntsOuts.model.UpdateProductUpdateDto;
import com.desktop.views.IntsOuts.services.IntsOutsService;
import com.desktop.views.shared.models.ViewModel;
import com.desktop.views.shared.models.index.TransactionTypeModel;
import com.desktop.views.shared.models.inventory.ProductMovementModel;
import com.desktop.views.shared.models.product.ProductModel;

public class IntsOutsPanelController implements ControllerInterface {
  public ViewModel view;
  public IntsOutsService intsOutsService = new IntsOutsService();
  public ContentPanelController parentController;
  public IntsOutsPanelComponent intsOutsComponent;
  public Map<String, ControllerInterface> childControllers;
  public Map<String, ComponentInterface> childComponents;
  public List<ProductModel> productNames;
  public List<TransactionTypeModel> transactionTypes;

  public IntsOutsPanelController(ContentPanelController controller, ViewModel view) {
    this.parentController = controller;
    this.view = view;
    productNames = getProducts();
    transactionTypes = getTransactionTypes();
    _initComponent();
    _initChildControllers();
    _initChildComponents();
  }

  @Override
  public void _initComponent() {
    intsOutsComponent = new IntsOutsPanelComponent(this);
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

  public void setDataTable(GetUpdateProductDto getUpdateProductDto) {
    try {
      List<ProductMovementModel> movements = intsOutsService.getIntsOuts(getUpdateProductDto);
      ((BottomPanelComponent) childComponents.get("BottomPanelComponent")).createTable(movements);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public ProductMovementModel createIntsOut(CreateUpdateProductDto createUpdateProductDto) {
    return intsOutsService.createIntsOuts(createUpdateProductDto);
  }

  public ProductMovementModel updateIntsOut(UpdateProductUpdateDto updateProductUpdateDto, int intsOutsId) {
    return intsOutsService.updateIntsOuts(updateProductUpdateDto, intsOutsId);
  }

  public Integer deleteIntsOut(int intsOutId) {
    return intsOutsService.deleteIntsOuts(intsOutId);
  }

  public List<ProductModel> getProducts() {
    return intsOutsService.getProducts();
  }

  public List<TransactionTypeModel> getTransactionTypes() {
    return intsOutsService.getTransactionTypes();
  }
}
