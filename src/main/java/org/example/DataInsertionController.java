package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path = "/demo")
public class DataInsertionController {

    @Autowired
    private WarehouseRepository warehouseRepository;

    @Autowired
    private ProductRepository productRepository;

    @PostMapping(path = "/insert-data")
    public @ResponseBody String insertData() {

        Warehouse warehouse1 = new Warehouse();
        warehouse1.setWarehouseName("Linz Bahnhof");
        warehouse1.setWarehouseAddress("Bahnhofsstrasse 27/9");
        warehouse1.setWarehousePostalCode("4020");
        warehouse1.setWarehouseCity("Linz");
        warehouse1.setWarehouseCountry("Austria");
        warehouseRepository.save(warehouse1);

        Warehouse warehouse2 = new Warehouse();
        warehouse2.setWarehouseName("Graz City Warehouse");
        warehouse2.setWarehouseAddress("Griesplatz 15");
        warehouse2.setWarehousePostalCode("8020");
        warehouse2.setWarehouseCity("Graz");
        warehouse2.setWarehouseCountry("Austria");
        warehouseRepository.save(warehouse2);

        for (int i = 1; i <= 10; i++) {
            Product product = new Product();
            product.setProductName("Product " + i);
            product.setProductCategory("Category " + i);
            product.setProductQuantity(1000 + i * 10);
            product.setProductUnit("Pack " + i + "L");

            if (i % 2 == 0) {
                product.setWarehouse(warehouse2);
            } else {
                product.setWarehouse(warehouse1);
            }

            productRepository.save(product);
        }

        return "2 Warehouses and 10 Products inserted successfully!";
    }
}
