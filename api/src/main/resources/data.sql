-- Insert data into the "user" table
-- INSERT INTO "user" (userid, phone, first_name, last_name, email) 
-- VALUES 
-- (1, '123-456-7890', 'John', 'Doe', 'john.doe@example.com'),
-- (2, '098-765-4321', 'Jane', 'Smith', 'jane.smith@example.com');

-- -- -- Insert data into the billing_address table
-- INSERT INTO billing_address (billing_addressid, userid, state, zip_code, city, country, street)
-- VALUES 
-- (1, 1, 'CA', '90001', 'Los Angeles', 'USA', '123 Main St'),
-- (2, 2, 'NY', '10001', 'New York', 'USA', '456 Broadway');

-- -- -- Insert data into the "tag" table
-- INSERT INTO tag (tagid, name) 
-- VALUES 
-- (1, 'Mens'),
-- (2, 'Womens'),
-- (3, 'Kids'),
-- (4, 'Sale'),
-- (5, 'New Arrival');

-- -- Insert data into the "color" table
-- INSERT INTO color (colorid, name) 
-- VALUES 
-- (1, 'Red'),
-- (2, 'Blue'),
-- (3, 'Green'),
-- (4, 'Black'),
-- (5, 'White');

-- -- -- Insert data into the "product" table
-- INSERT INTO product (productid, price, quantity, name, description) 
-- VALUES 
-- (1, 19.99, 100, 'T-shirt', 'A comfortable cotton t-shirt'),
-- (2, 49.99, 50, 'Dress', 'Elegant evening dress'),
-- (3, 29.99, 30, 'Kids Jacket', 'Warm winter jacket'),
-- (4, 59.99, 200, 'Sneakers', 'Comfortable sneakers'),
-- (5, 15.99, 150, 'Hat', 'Stylish summer hat'),
-- (6, 99.99, 80, 'Coat', 'Winter coat');

-- -- Insert data into the "product_color" table
-- INSERT INTO product_color (product_colorid, productid, colorid) 
-- VALUES 
-- (1, 1, 1), -- T-shirt (Red)
-- (2, 1, 2), -- T-shirt (Blue)
-- (3, 2, 5), -- Dress (White)
-- (4, 3, 3), -- Kids Jacket (Green)
-- (5, 4, 4), -- Sneakers (Black)
-- (6, 5, 2), -- Hat (Blue)
-- (7, 6, 1); -- Coat (Red)

-- -- Insert data into the "product_tag" table
-- INSERT INTO product_tag (product_tagid, productid, tagid) 
-- VALUES 
-- (1, 1, 1),  -- T-shirt (Mens)
-- (2, 2, 2),  -- Dress (Womens)
-- (3, 3, 3),  -- Kids Jacket (Kids)
-- (4, 4, 5),  -- Sneakers (New Arrival)
-- (5, 5, 4),  -- Hat (Sale)
-- (6, 6, 1);  -- Coat (Mens)

-- -- Insert data into the "cart_item" table
-- INSERT INTO cart_item (cart_itemid, productid, userid) 
-- VALUES 
-- (1, 1, 1),  -- John Doe's cart (T-shirt)
-- (2, 2, 2);  -- Jane Smith's cart (Dress)

-- -- Insert data into the "order" table
-- INSERT INTO "order" (orderid, state, userid, status, zip_code, country, city, street) 
-- VALUES 
-- (1, 'CA', 1, 'Shipped', '90001', 'USA', 'Los Angeles', '123 Main St'),
-- (2, 'NY', 2, 'Processing', '10001', 'USA', 'New York', '456 Broadway');

-- -- Insert data into the "order_item" table
-- INSERT INTO order_item (order_itemid, orderid, productid) 
-- VALUES 
-- (1, 1, 1),  -- Order 1 contains T-shirt
-- (2, 2, 2);  -- Order 2 contains Dress

-- -- Insert data into the "order_tracking" table
INSERT INTO order_tracking (order_trackingid, orderid, tracking_number) 
VALUES 
(1, 1, 'TRACK123'), 
(2, 2, 'TRACK456');

-- -- Insert data into the "product_image" table
-- -- Assuming the "image" column is a bytea (binary) column
-- -- For simplicity, here are references to image IDs. In real usage, you'd store actual image data.
-- -- INSERT INTO product_image (product_imageid, productid, image) 
-- -- VALUES 
-- -- (1, 1, 'sample_image_data'), 
-- -- (2, 2, 'sample_image_data');