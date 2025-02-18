package org.example;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final WarehouseRepository warehouseRepository;
    private final ProductRepository productRepository;

    public DataInitializer(WarehouseRepository warehouseRepository, ProductRepository productRepository) {
        this.warehouseRepository = warehouseRepository;
        this.productRepository = productRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (warehouseRepository.count() == 0) {
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

            System.out.println("2 Warehouses and 10 Products inserted successfully!");
        }
    }
}
