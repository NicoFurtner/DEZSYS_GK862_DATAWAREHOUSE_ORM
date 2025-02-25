package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/warehouse")
public class WarehouseController {

    @Autowired
    private WarehouseRepository warehouseRepository;

    @PostMapping("/add")
    public String addNewWarehouse(@RequestBody Warehouse warehouse) {
        warehouseRepository.save(warehouse);
        return "Warehouse added successfully";
    }

    @GetMapping
    public List<Warehouse> getAllWarehouses() {
        return warehouseRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Warehouse> getWarehouseById(@PathVariable String id) {
        return warehouseRepository.findById(id);
    }

    @DeleteMapping("/{id}")
    public String deleteWarehouse(@PathVariable String id) {
        warehouseRepository.deleteById(id);
        return "Warehouse deleted successfully";
    }
}
