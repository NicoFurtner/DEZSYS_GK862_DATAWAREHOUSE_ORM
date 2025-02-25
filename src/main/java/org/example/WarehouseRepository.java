package org.example;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface WarehouseRepository extends MongoRepository<Warehouse, String> {
}
