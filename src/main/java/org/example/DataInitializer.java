package org.example;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {

    private final WarehouseRepository warehouseRepository;

    public DataInitializer(WarehouseRepository warehouseRepository) {
        this.warehouseRepository = warehouseRepository;
    }

    @Override
    public void run(String... args) {
        warehouseRepository.deleteAll();

        List<Product> products = Arrays.asList(
                new Product("1", "Tablet", "Technologie", 75),
                new Product("2", "Smartwatch", "Technologie", 110),
                new Product("3", "Bluetooth Lautsprecher", "Technologie", 65),
                new Product("4", "Gaming Maus", "Zubehör", 180),
                new Product("5", "LED Monitor", "Zubehör", 140),
                new Product("6", "Roman", "Bücher", 300),
                new Product("7", "Notizbuch", "Bürobedarf", 450),
                new Product("8", "Tintenpatrone", "Bürobedarf", 200),
                new Product("9", "Esstisch", "Möbel", 120),
                new Product("10", "Beistellstuhl", "Möbel", 90)
        );

        Warehouse warehouse1 = new Warehouse("warehouse1", "Lager West", "Klagenfurt", products.subList(0, 5));
        Warehouse warehouse2 = new Warehouse("warehouse2", "Lager Süd", "Graz", products.subList(5, 10));

        warehouseRepository.saveAll(Arrays.asList(warehouse1, warehouse2));

        System.out.println("Daten wurden erfolgreich in 2 Lagerhäusern abgelegt.");
    }
}

