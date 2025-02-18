# DEZSYS_GK81_WAREHOUSE_ORM

Nico Furtner

## Documentation

With:

```bash
docker run --name mysql-warehouse -e MYSQL_ROOT_PASSWORD=root MYSQL_DATABASE= warehouse -p 3306:3306
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
    

author: Nico Furtner
