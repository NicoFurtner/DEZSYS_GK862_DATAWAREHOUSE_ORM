package org.example;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface WarehouseRepository extends CrudRepository<Warehouse, Long> {
    Optional<Warehouse> findByWarehouseID(Long warehouseID);
}
