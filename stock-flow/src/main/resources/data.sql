-- Companies
INSERT INTO company (id, name) VALUES (1, 'ABC Corp');

-- Product Types with different thresholds
INSERT INTO product_type (id, name, low_stock_threshold) VALUES (1, 'Electronics', 30);
INSERT INTO product_type (id, name, low_stock_threshold) VALUES (2, 'Office Supplies', 50);
INSERT INTO product_type (id, name, low_stock_threshold) VALUES (3, 'Perishables', 15);

-- Suppliers
INSERT INTO supplier (id, name, contact_email) VALUES (1, 'TechSupplier Inc.', 'orders@techsupplier.com');
INSERT INTO supplier (id, name, contact_email) VALUES (2, 'Office Essentials', 'sales@officeessentials.com');
INSERT INTO supplier (id, name, contact_email) VALUES (3, 'Fresh Goods Co.', 'supply@freshgoods.com');

-- Products
INSERT INTO product (id, name, sku, company_id, product_type_id, supplier_id, average_daily_usage)
VALUES (1, 'Laptop', 'LT-001', 1, 1, 1, 2.5);
INSERT INTO product (id, name, sku, company_id, product_type_id, supplier_id, average_daily_usage)
VALUES (2, 'Printer Paper', 'PP-100', 1, 2, 2, 10.0);
INSERT INTO product (id, name, sku, company_id, product_type_id, supplier_id, average_daily_usage)
VALUES (3, 'Coffee Pods', 'CP-200', 1, 3, 3, 5.0);

-- Warehouses
INSERT INTO warehouse (id, name, company_id) VALUES (1, 'Main Warehouse', 1);
INSERT INTO warehouse (id, name, company_id) VALUES (2, 'Secondary Warehouse', 1);

-- Inventory (some below threshold, some not)
-- Low stock laptop
INSERT INTO inventory (id, product_id, warehouse_id, current_stock, has_recent_sales, last_sale_date)
VALUES (1, 1, 1, 10, true, '2025-09-10');

-- Normal stock printer paper
INSERT INTO inventory (id, product_id, warehouse_id, current_stock, has_recent_sales, last_sale_date)
VALUES (2, 2, 1, 60, true, '2025-09-14');

-- Low stock printer paper
INSERT INTO inventory (id, product_id, warehouse_id, current_stock, has_recent_sales, last_sale_date)
VALUES (3, 2, 2, 25, true, '2025-09-12');

-- Low stock coffee pods with recent sales
INSERT INTO inventory (id, product_id, warehouse_id, current_stock, has_recent_sales, last_sale_date)
VALUES (4, 3, 1, 10, true, '2025-09-14');

-- Low stock coffee pods without recent sales (should not show in alerts)
INSERT INTO inventory (id, product_id, warehouse_id, current_stock, has_recent_sales, last_sale_date)
VALUES (5, 3, 2, 5, false, '2025-08-15');