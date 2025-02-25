package org.example;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
public class Warehouse {

    @Id
    private String id; // MongoDB _id

    private String warehouseName;
    private String timestamp;
    private int warehousePostalCode;
    private String warehouseCity;
    private String warehouseCountry;
    private List<Product> products;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWarehouseName() {
        return warehouseName;
    }

    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public int getWarehousePostalCode() {
        return warehousePostalCode;
    }

    public void setWarehousePostalCode(int warehousePostalCode) {
        this.warehousePostalCode = warehousePostalCode;
    }

    public String getWarehouseCity() {
        return warehouseCity;
    }

    public void setWarehouseCity(String warehouseCity) {
        this.warehouseCity = warehouseCity;
    }

    public String getWarehouseCountry() {
        return warehouseCountry;
    }

    public void setWarehouseCountry(String warehouseCountry) {
        this.warehouseCountry = warehouseCountry;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
