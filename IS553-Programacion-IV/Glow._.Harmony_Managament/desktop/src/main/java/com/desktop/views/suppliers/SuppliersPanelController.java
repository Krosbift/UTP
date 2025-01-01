package com.desktop.views.suppliers;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import com.desktop.core.navigation.components.Content.ContentPanelController;
import com.desktop.core.utils.interfaces.ComponentInterface;
import com.desktop.core.utils.interfaces.ControllerInterface;
import com.desktop.views.shared.models.SupplierModel;
import com.desktop.views.shared.models.ViewModel;
import com.desktop.views.suppliers.components.BottomPanelComponent;
import com.desktop.views.suppliers.components.TopPanelComponent;
import com.desktop.views.suppliers.model.CreateSupplierDto;
import com.desktop.views.suppliers.model.GetSupplierDto;
import com.desktop.views.suppliers.model.UpdateSupplierDto;
import com.desktop.views.suppliers.services.SuppliersService;

public class SuppliersPanelController implements ControllerInterface {
  public ViewModel view;
  public SuppliersService suppliersService = new SuppliersService();
  public ContentPanelController parentController;
  public SuppliersPanelComponent suppliersComponent;
  public Map<String, ControllerInterface> childControllers;
  public Map<String, ComponentInterface> childComponents;

  public SuppliersPanelController(ContentPanelController controller, ViewModel view) {
    this.parentController = controller;
    this.view = view;
    _initComponent();
    _initChildControllers();
    _initChildComponents();
  }

  @Override
  public void _initComponent() {
    suppliersComponent = new SuppliersPanelComponent(this);
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

  public void setDataTable(GetSupplierDto getSupplierDto) {
    try {
      List<SupplierModel> suppliers = suppliersService.getSuppliers(getSupplierDto);
      ((BottomPanelComponent) childComponents.get("BottomPanelComponent")).createTable(suppliers);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public SupplierModel creaSupplier(CreateSupplierDto createSupplierDto) {
    return suppliersService.createSupplier(createSupplierDto);
  }

  public SupplierModel updateSupplier(UpdateSupplierDto sUpdateSupplierDto, int supplierId) {
    return suppliersService.updateSupplier(sUpdateSupplierDto, supplierId);
  }

  public Integer deleteSupplier(int productId) {
    return suppliersService.deactivateSupplier(productId);
  }
}
