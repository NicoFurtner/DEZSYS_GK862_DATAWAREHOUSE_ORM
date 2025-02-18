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
    public List<Product> getProductData() {
        return productData;
    }
    public void setProductData(List<Product> productData) {
        this.productData = productData;
    }
}
