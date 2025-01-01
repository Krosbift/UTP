package com.api.routes.suppliers;

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

import com.api.routes.shared.models.SupplierModel;
import com.api.routes.suppliers.dto.CreateSupplierDto;
import com.api.routes.suppliers.dto.GetSupplierDto;
import com.api.routes.suppliers.dto.UpdateSupplierDto;

@RestController
@RequestMapping("suppliers")
@Tag(name = "Proveedores")
public class SuppliersController {
  @Autowired
  private SuppliersService suppliersService;

  @GetMapping("find-supplier")
  @Operation(summary = "Buscar proveedores por cualquier valor coincidiente")
  public List<SupplierModel> findSupplier(@RequestParam(required = false) String name,
      @RequestParam(required = false) String address, @RequestParam(required = false) String phone) {
    GetSupplierDto getSupplierDto = new GetSupplierDto()
        .setName(name)
        .setAddress(address)
        .setPhone(phone)
        .build();
    return suppliersService.findSupplier(getSupplierDto);
  }

  @GetMapping("find-all-suppliers")
  @Operation(summary = "Buscar todos los proveedores")
  public List<SupplierModel> findAllSuppliers() {
    return suppliersService.findAllSuppliers();
  }

  @PostMapping("create-supplier")
  @Operation(summary = "Crea un nuevo proveedor")
  public SupplierModel createSupplier(@RequestBody CreateSupplierDto createSupplierDto) {
    return suppliersService.createSupplier(createSupplierDto);
  }

  @PatchMapping("update-supplier/{supplierId}")
  @Operation(summary = "Actualizar proveedor")
  public SupplierModel updateSupplier(@RequestBody UpdateSupplierDto updateSupplierDto, @PathVariable int supplierId) {
    return suppliersService.updateSupplier(updateSupplierDto, supplierId);
  }

  @PatchMapping("activate-supplier/{supplierId}")
  @Operation(summary = "Activar proveedor")
  public int activateUser(@PathVariable int supplierId) {
    return suppliersService.activateSupplier(supplierId);
  }

  @DeleteMapping("delete-supplier/{supplierId}")
  @Operation(summary = "Desactivar proveedor")
  public int deleteSupplier(@PathVariable int supplierId) {
    return suppliersService.deleteSupplier(supplierId);
  }
}
