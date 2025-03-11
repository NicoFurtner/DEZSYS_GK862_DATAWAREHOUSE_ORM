# DEZSYS_GK81_WAREHOUSE_ORM

Nico Furtner

## Documentation

With:

```bash
docker run --name mysql-warehouse -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=warehouse -p 3306:3306 mysql
```

we can run the docker mysql and create the database and:

```bash
docker exec -it mysql-warehouse mysql -u root -p -h 172.17.0.2
```

connects with SQL Monitor, where we now are able to input commands for sql

With  ```SHOW DATABASES;``` we can see the database, so it was created succesfully.

New Project from existing Version Control [GitHub - ThomasMicheler/DEZSYS_GK862_DATAWAREHOUSE_ORM](https://github.com/ThomasMicheler/DEZSYS_GK862_DATAWAREHOUSE_ORM)

### Warehouse Entity

```java
@Entity

public class Warehouse {
```

Here we define the class as a JPA Entity and define as wareshouse

```java
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long warehouseID; 
```

The Annotation ``@Id`` marks the warehouseID as primary key for this Entity. `@GeneratedValue` warehouseID should be generated automatically by the database when new Warehouse object is inserted

```java
@OneToMany(mappedBy = „warehouse“, cascade = CascadeType.ALL)

private List<Product> productData;
```

``@OneToMany`` means that one warehouse is connected to many products, mappedBy means that the relation is managed by warehouse and cascade = CascadeType.ALL, means that all operations performed on the parent entity (`Warehouse`) will automatically be applied to the associated child entities (`Product`).

```java
@ManyToOne
@JoinColumn(name = "warehouse_id")
private Warehouse warehouse;
```

in the other Entity its `@ManyToOne` , so many products to one warehouse and ``@JoinColumn`` specifies that the `Warehouse` entity is referenced by a foreign key column called `warehouse_id` in the database table of the current entity

### Warehouse Controller

```java
@Controller
@RequestMapping("/warehouse")
public class WarehouseController {
```

``@Controller``: Marks the class as a Spring MVC controller.

`@RequestMapping("/warehouse")`: handler methods in this class are mapped under the `/warehouse` URL path.

```java
@Autowired 
private WarehouseRepository warehouseRepository;
```

`@Autowired`: Automatically injects dependencies into the class.

```java
@PostMapping("/add")
public String addWarehouse(@RequestBody Warehouse warehouse) {
    warehouseRepository.save(warehouse);
}
```

Post request to warehouse and saving the warehouse object

```java
@GetMapping("/{id}")
public Optional<Warehouse> getWarehouse(@PathVariable Long id) {
    return warehouseRepository.findbyId(id)
}
```

get request and gets the warehouseid

### Warehouse Repository

```java
public interface WarehouseRepository exstends CrudRepository<Warehouse, long> {   
}
```

``CrudRepository<Warehouse, Long>`` for basic CRUD operations, with methods like save().

We shouldn’t forget to build gradle, and make sure everything is implemented
right, and the application.properties for Spring Boot’s existing and also
implementing everything we need.

``USE warehouse`` und ``SHOW tables;`` -> Tables were created right

and with ``DESCRIBE <tablename>`` we can also see the data types and keys

### Inserting the Data

Now we want to Insert the Data, 2 Warehouses and 10 Products --> for that i'll create an own class that implements the `CommandLineRunner`, which will automaticly run the run method after the application starts

```java
@Component  
public class DataInitializer implements CommandLineRunner {
```

```java
@Override
public void run(String... args) {
```

`@Component` -> marks this class as a Spring-managed "bean", meaning Spring will automatically detect and run it

- **ORM & JPA***: ORM helps turn database tables into Java objects; JPA is a way to do that in Java, often using Hibernate.
  
- **application.properties**: settings for your app (like database info) and goes in src/main/resources.
  
- **Entity annotations**: @Entity, @Id, @Table, @GeneratedValue, @Column: used to link Java classes to database tables.
  
  - @Entity: class marked as entity and is linked to the table
    
  - @Id: primary key
    
  - @Table: table name
    
  - @GeneratedValue: generation strategy for primary key
    
  - @Column: details about a table column
    
- **CRUD methods**: save(), findById(), findAll(), deleteById(), delete(), and sometimes update().
  
  - save(): insert or update entity
    
  - findById(): gets the entity by the primary key
    
  - findAll(): gets all entities
    
  - deleteById(): delete entity by the primary key
    
  - delete(): delete specific entity
    
# Nicht relationale Datenbank MongoDB

### Dokumenation

To work with MongoDB, we add the configuration for MongoDB in the `application.properties` file:

`spring.data.mongodb.uri=mongodb://localhost:27017/warehouse_db`

Here we set the localhost for MongoDB. I am using MongoDB Compass, where you create a new connection. When you run the code (explanation coming shortly) with `bootRun`, the database is automatically added. When you upload data via a POST request or through the MongoDB shell, the data will be displayed afterward.

![MongoDBCompasspng](file://C:\Users\Nico%20IO\OneDrive%20-%20tgm%20-%20Die%20Schule%20der%20Technik\4.%20Jahrgang\Systemtechnik\DEZSYS\MongoDBCompass.png?msec=1741695517126)

### Changes to Entity Classes for MongoDB:

The Entity annotation has now changed to Document

- `@Document`: Maps the class to a MongoDB collection.
- `@Getter`: Automatically generates getter methods for all fields.
- `@Setter`: Automatically generates setter methods for all fields.
- `@NoArgsConstructor`: Generates a no-argument constructor.
- `@AllArgsConstructor`: Generates a constructor with arguments for all fields.

**Repository**: Transitioned from `JpaRepository` to `MongoRepository` for MongoDB support.

public interface WarehouseRepository extends MongoRepository<Warehouse, String>

**Service Methods**: Methods now work with MongoDB's CRUD operations and document-based storage.

**Data Model**: Products are embedded directly inside the warehouse documents, instead of being stored separately with foreign key relationships.

For the REST API we still need the controller class for post get delete requests

![PostManpng](file://C:\Users\Nico%20IO\OneDrive%20-%20tgm%20-%20Die%20Schule%20der%20Technik\4.%20Jahrgang\Systemtechnik\DEZSYS\PostMan.png?msec=1741696262836)

### **CRUD Operations in MongoDB Shell**

**Add a Product**

```bash
db.warehouses.update(
    { _id: "warehouse1" }, 
    { $push: { products: { productId: "00-123456", name: "Bio Orange Juice", category: "Drink", quantity: 1500, unit: "1L Pack" } } }
)
```

**Delete a Product**

```bash
db.warehouses.update(
    { _id: "warehouse1" },
    { $pull: { products: { productId: "00-123456" } } }
)
```

**Update Product's Quantity**

```bash
db.warehouses.update(
    { _id: "warehouse1", "products.productId": "00-123456" },
    { $set: { "products.$.quantity": 2000 } }
)
```

**Find All Products in a Warehouse**

```bash
db.warehouses.find({ _id: "warehouse1" }, { products: 1 })
```

**Find a Product by ID in All Warehouses**

```bash
db.warehouses.find({
  "products.productId": "4"
}, { "products.$": 1 }).pretty()

```

Here I first had problems because he didn't find the db and added it to something else, because I forgot to connect with the database in the shell `use warehouse_db` and then everything worked.

Example with adding a product

![shellmongodbpng](file://C:\Users\Nico%20IO\OneDrive%20-%20tgm%20-%20Die%20Schule%20der%20Technik\4.%20Jahrgang\Systemtechnik\DEZSYS\shellmongodb.png?msec=1741697727106)

### Advantages of NoSQL over Relational DBMS:

1. **Scalability**: Easy horizontal scaling.
2. **Flexibility**: No fixed schema, supports various data structures.
3. **Availability**: High availability, even in case of node failures.
4. **Performance**: Fast processing of large amounts of data.

### Disadvantages of NoSQL over Relational DBMS:

1. **Lack of ACID Transactions**: No full transaction security.
2. **Complex Queries**: Less expressive than SQL.
3. **Lack of Standardization**: No unified model.
4. **Data Inconsistency**: Potential issues with data quality.

### Challenges when Merging Data:

- **Incompatible Data Formats** and **Inconsistencies** between different sources.

### Types of NoSQL Databases and Examples:

1. **Document-Oriented**: MongoDB
2. **Key-Value**: Redis
3. **Column-Oriented**: Cassandra
4. **Graph Database**: Neo4j

### Abbreviations in the CAP Theorem:

- **C**: Consistency (Data is the same everywhere)
- **A**: Availability (Data is available at all times)
- **P**: Partition Tolerance (System works despite network failures)

### SQL Commands:

- **Stock of all Locations**: `SELECT * FROM stock;`
- **Stock of a Specific Location**: `SELECT * FROM stock WHERE location = 'XYZ';`
author: Nico Furtner
