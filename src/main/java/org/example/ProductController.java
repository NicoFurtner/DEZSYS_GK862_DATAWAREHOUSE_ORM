package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(path = "/products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private WarehouseRepository warehouseRepository;

    @PostMapping(path = "/add")
    public @ResponseBody String addNewProduct(@RequestParam String productName,
                                              @RequestParam String productCategory,
                                              @RequestParam int productQuantity,
                                              @RequestParam String productUnit,
                                              @RequestParam Long warehouseID) {

        Optional<Warehouse> warehouseOptional = warehouseRepository.findById(warehouseID);

        if (warehouseOptional.isEmpty()) {
            return "Warehouse not found";
        }

        Warehouse warehouse = warehouseOptional.get();

        Product product = new Product();
        product.setProductName(productName);
        product.setProductCategory(productCategory);
        product.setProductQuantity(productQuantity);
        product.setProductUnit(productUnit);
        product.setWarehouse(warehouse);

        productRepository.save(product);

        return "Product added successfully";
    }
    @GetMapping("/all")
    public @ResponseBody List<Product> getAllProducts() {
        return (List<Product>) productRepository.findAll();
    }
    @GetMapping("/{id}")
    public @ResponseBody String getProduct(@PathVariable Long id) {
        Optional<Product> productOptional = productRepository.findById(id);

        if (productOptional.isEmpty()) {
            return "Product not found";
        }

        Product product = productOptional.get();
        return "Product found: " + product.getProductName();
    }
}
