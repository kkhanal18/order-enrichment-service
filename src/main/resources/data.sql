-- Clear existing data (optional)
DELETE FROM orders;
DELETE FROM products;
DELETE FROM customers;

INSERT INTO customers (id, name, address, postalcode, country)
VALUES (1,'John Smith','742 Evergreen Terrace','62704','United States')
ON CONFLICT (id) DO NOTHING;

INSERT INTO customers (id, name, address, postalcode, country)
VALUES (2,'Alice Johnson','123 Maplewood Drive','73301','United States')
ON CONFLICT (id) DO NOTHING;


INSERT INTO products (id, name, price, category, tags)
VALUES (1, 'TV', 499.99, 'Electronics', 'HDR10+, 120Hz refresh rate, Smart TV features')
ON CONFLICT (id) DO NOTHING;

INSERT INTO products (id, name, price, category, tags)
VALUES (2, 'Smartphone X200', 899.50, 'Electronics', '6.5-inch OLED, 128GB Storage, Dual Camera')
ON CONFLICT (id) DO NOTHING;
