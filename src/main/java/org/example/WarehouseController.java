package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping(path = "/warehouses")
public class WarehouseController {

    @Autowired
    private WarehouseRepository warehouseRepository;
    @PostMapping(path = "/add")
    public @ResponseBody String addNewWarehouse(@RequestParam String warehouseName,
                                                @RequestParam String warehouseAddress,
                                                @RequestParam String warehousePostalCode,
                                                @RequestParam String warehouseCity,
                                                @RequestParam String warehouseCountry,
                                                @RequestParam String timestamp) {

        Warehouse warehouse = new Warehouse();
        warehouse.setWarehouseName(warehouseName);
        warehouse.setWarehouseAddress(warehouseAddress);
        warehouse.setWarehousePostalCode(warehousePostalCode);
        warehouse.setWarehouseCity(warehouseCity);
        warehouse.setWarehouseCountry(warehouseCountry);
        warehouse.setTimestamp(timestamp);

        warehouseRepository.save(warehouse);

        return "Warehouse added successfully";
    }
    @GetMapping("/{id}")
    public @ResponseBody Optional<Warehouse> getWarehouse(@PathVariable Long id) {
        return warehouseRepository.findById(id);
    }
}
