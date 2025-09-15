# Low Stock Alert API

A Spring Boot REST API that provides low-stock alerts for company inventory management across multiple warehouses. This service helps businesses proactively manage their inventory by identifying products that are running low on stock and need reordering.

![Java](https://img.shields.io/badge/Java-21-orange)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.5-green)

## 📋 Features

- **Product-Type Based Thresholds**: Different thresholds for different product categories
- **Sales Activity Filtering**: Only alerts for products with recent sales activity
- **Multi-Warehouse Support**: Handles inventory across multiple warehouses per company
- **Supplier Information**: Includes complete supplier details for easy reordering
- **Stockout Prediction**: Calculates days until stockout based on usage patterns
- **RESTful API**: Clean, well-documented endpoints following REST best practices

## 🔧 Technology Stack

- **Java 21**: Leveraging the latest language features
- **Spring Boot 3.5.5**: Modern application framework
- **Spring Data JPA**: Simplified data access layer
- **H2 Database**: In-memory database for development
- **Lombok**: Reduces boilerplate code
- **Maven**: Dependency management and build tool

## ⚙️ Prerequisites

- Java Development Kit (JDK) 21
- Maven 3.6+
- Git

## 🚀 Installation & Setup

1. **Clone the repository**
   ```bash
   git clone https://github.com/dnyaneshagale/low-stock-alert-api.git
   cd low-stock-alert-api
   
2. **Build the application**
   ```bash
   mvn clean install
   ```

3. **Run the application**
   ```bash
   mvn spring-boot:run
   ```

4. **Access the API**
    - The application will be available at `http://localhost:8080`
    - H2 Console is available at `http://localhost:8080/h2-console` (use JDBC URL: `jdbc:h2:mem:inventorydb`)

## 📝 API Documentation

### Endpoints

#### Get Low Stock Alerts

```
GET /api/companies/{company_id}/alerts/low-stock
```

**Description**: Returns a list of products that are below their stock threshold and have recent sales activity.

**Path Parameters**:
- `company_id` - ID of the company

**Response Example**:
```json
{
  "alerts": [
    {
      "productId": 1,
      "productName": "Laptop",
      "sku": "LT-001",
      "warehouseId": 1,
      "warehouseName": "Main Warehouse",
      "currentStock": 10,
      "threshold": 30,
      "daysUntilStockout": 4,
      "supplier": {
        "id": 1,
        "name": "TechSupplier Inc.",
        "contactEmail": "orders@techsupplier.com"
      }
    },
    {
      "productId": 2,
      "productName": "Printer Paper",
      "sku": "PP-100",
      "warehouseId": 2,
      "warehouseName": "Secondary Warehouse",
      "currentStock": 25,
      "threshold": 50,
      "daysUntilStockout": 2,
      "supplier": {
        "id": 2,
        "name": "Office Essentials",
        "contactEmail": "sales@officeessentials.com"
      }
    }
  ],
  "totalAlerts": 2
}
```

## 🗄️ Database Schema

### Entity Relationship Diagram

The application uses the following data model:

- **Company**: Central entity that owns products and warehouses
- **Product**: Items that can be stocked, with details like SKU and supplier
- **ProductType**: Categories of products with specific low-stock thresholds
- **Warehouse**: Physical locations where inventory is stored
- **Inventory**: Junction entity tracking product stock in specific warehouses
- **Supplier**: Vendors who provide products for reordering

## 🛠️ Implementation Details

### Business Rules

1. **Low Stock Determination**:
    - Products are considered low stock when their current quantity falls below the threshold defined for their product type
    - Only products with recent sales activity generate alerts
    - The system calculates days until stockout based on average daily usage

2. **Warehouse Management**:
    - The same product can be stored in multiple warehouses
    - Each warehouse belongs to a specific company
    - Stock levels are tracked per warehouse

3. **Supplier Information**:
    - Every product is associated with a supplier
    - Supplier contact information is included in alerts to facilitate reordering

## ⚠️ Troubleshooting

### Common Issues

1. **Database Initialization Error**:
    - If you encounter errors related to table not found during startup, ensure that `spring.jpa.defer-datasource-initialization=true` is set in your `application.properties`

2. **Hibernate Entity Issues**:
    - If you experience entity mapping problems, verify that your entity relationships are correctly defined with proper cascade types and fetch strategies.