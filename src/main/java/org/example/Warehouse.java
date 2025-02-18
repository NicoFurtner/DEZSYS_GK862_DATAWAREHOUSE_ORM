package org.example;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Warehouse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long warehouseID;
    private String warehouseName;
    private String warehouseAddress;
    private String warehousePostalCode;
    private String warehouseCity;
    private String warehouseCountry;
    private String timestamp;

    @OneToMany(mappedBy = "warehouse", cascade = CascadeType.ALL)
    private List<Product> productData;
    public Warehouse() {}
    public Long getWarehouseID() {
        return warehouseID;
    }
    public void setWarehouseID(Long warehouseID) {
        this.warehouseID = warehouseID;
    }
    public String getWarehouseName() {
        return warehouseName;
    }
    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName;
    }
    public String getWarehouseAddress() {
        return warehouseAddress;
    }
    public void setWarehouseAddress(String warehouseAddress) {
        this.warehouseAddress = warehouseAddress;
    }
    public String getWarehousePostalCode() {
        return warehousePostalCode;
    }
    public void setWarehousePostalCode(String warehousePostalCode) {
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
    public String getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
    public List<Product> getProductData() {
        return productData;
    }
    public void setProductData(List<Product> productData) {
        this.productData = productData;
    }
}
