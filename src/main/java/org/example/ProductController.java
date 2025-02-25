package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private WarehouseRepository warehouseRepository;

    @PostMapping("/add")
    public String addNewProduct(@RequestBody Product product) {
        Optional<Warehouse> warehouseOptional = warehouseRepository.findById(product.getWarehouseID());

        if (warehouseOptional.isEmpty()) {
            return "Warehouse not found!";
        }

        productRepository.save(product);

        Warehouse warehouse = warehouseOptional.get();
        List<Product> products = warehouse.getProducts();
        if (products == null) {
            products = new ArrayList<>();
        }
        products.add(product);
        warehouse.setProducts(products);

        warehouseRepository.save(warehouse);

        return "Product added successfully to warehouse";
    }


    @GetMapping
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Product> getProductById(@PathVariable String id) {
        return productRepository.findById(id);
    }

    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable String id) {
        Optional<Product> productOptional = productRepository.findById(id);

        if (productOptional.isEmpty()) {
            return "Product not found!";
        }

        productRepository.deleteById(id);
        return "Product deleted successfully";
    }
}
