-- Insert Users
insert into BARISTA_USER (user_id, user_name, password, user_role) values (1, 'admin', 'password123', 'ADMINISTRATOR');

insert into BARISTA_USER (user_id, user_name, password, user_role) values (2, 'customer', 'password123', 'CUSTOMER');

-- Insert Ingredients
insert into BARISTA_INGREDIENT (ingredient_id, ingredient_name, cost, inventory, purchases) values (1, 'Coffee',  0.75, 10, 0);

insert into BARISTA_INGREDIENT (ingredient_id, ingredient_name, cost, inventory, purchases) values (2, 'Decaf Coffee', 0.75, 10, 0);

insert into BARISTA_INGREDIENT (ingredient_id, ingredient_name, cost, inventory, purchases) values (3, 'Sugar', 0.25, 10, 0);

insert into BARISTA_INGREDIENT (ingredient_id, ingredient_name, cost, inventory, purchases) values (4, 'Cream', 0.25, 10, 0);

insert into BARISTA_INGREDIENT (ingredient_id, ingredient_name, cost, inventory, purchases) values (5, 'Steamed Milk', 0.35, 10, 0);

insert into BARISTA_INGREDIENT (ingredient_id, ingredient_name, cost, inventory, purchases) values (6, 'Foamed Milk', 0.35, 10, 0);

insert into BARISTA_INGREDIENT (ingredient_id, ingredient_name, cost, inventory, purchases) values (7, 'Espresso', 1.10, 10, 0);

insert into BARISTA_INGREDIENT (ingredient_id, ingredient_name, cost, inventory, purchases) values (8, 'Cocoa', 0.90, 10, 0);

insert into BARISTA_INGREDIENT (ingredient_id, ingredient_name, cost, inventory, purchases) values (9, 'Whipped Cream', 1.00, 10, 0);


-- Insert Drinks
insert into BARISTA_DRINK (drink_id, drink_name, cost, sales) values (1, 'Coffee', 2.75, 0);

insert into BARISTA_DRINK (drink_id, drink_name, cost, sales) values (2, 'Decaf Coffee', 2.75, 0);

insert into BARISTA_DRINK (drink_id, drink_name, cost, sales) values (3, 'Caffee Latte', 2.55, 0);

insert into BARISTA_DRINK (drink_id, drink_name, cost, sales) values (4, 'Caffe Americano', 3.30, 0);

insert into BARISTA_DRINK (drink_id, drink_name, cost, sales) values (5, 'Caffe Mocha', 3.35, 0);

insert into BARISTA_DRINK (drink_id, drink_name, cost, sales) values (6, 'Cappuccino', 2.90, 0);

-- Coffee has 3 units of Coffee
insert into BARISTA_RECIPE (drink_drink_id, ingredient_ingredient_id, part) values (1, 1, 3);

-- Coffee has 1 unit of Sugar
insert into BARISTA_RECIPE (drink_drink_id, ingredient_ingredient_id, part) values (1, 3, 1);

-- Coffee has 1 unit of Cream
insert into BARISTA_RECIPE (drink_drink_id, ingredient_ingredient_id, part) values (1, 4, 1);

-- Decaf Coffee has 3 units of Decaf Coffee
insert into BARISTA_RECIPE (drink_drink_id, ingredient_ingredient_id, part) values (2, 2, 3);

-- Decaf Coffee has 1 unit of Sugar
insert into BARISTA_RECIPE (drink_drink_id, ingredient_ingredient_id, part) values (2, 3, 1);

-- Decaf Coffee has 1 unit of Cream
insert into BARISTA_RECIPE (drink_drink_id, ingredient_ingredient_id, part) values (2, 4, 1);

-- Caffee Latte has 2 units of Espresso
insert into BARISTA_RECIPE (drink_drink_id, ingredient_ingredient_id, part) values (3, 7, 2);

-- Caffee Latte has 1 unit of Steamed Milk
insert into BARISTA_RECIPE (drink_drink_id, ingredient_ingredient_id, part) values (3, 5, 1);

-- Caffee Americano has 3 units of Espresso
insert into BARISTA_RECIPE (drink_drink_id, ingredient_ingredient_id, part) values (4, 7, 3);

-- Caffee Mocha has 1 unit of Espresso
insert into BARISTA_RECIPE (drink_drink_id, ingredient_ingredient_id, part) values (5, 7, 1);

-- Caffee Mocha has 1 unit of Cocoa
insert into BARISTA_RECIPE (drink_drink_id, ingredient_ingredient_id, part) values (5, 8, 1);

-- Caffee Mocha has 1 unit of Steamed Milk
insert into BARISTA_RECIPE (drink_drink_id, ingredient_ingredient_id, part) values (5, 5, 1);

-- Caffee Mocha has 1 unit of Whipped Cream
insert into BARISTA_RECIPE (drink_drink_id, ingredient_ingredient_id, part) values (5, 9, 1);

-- Cappuccino has 2 units of Espresso
insert into BARISTA_RECIPE (drink_drink_id, ingredient_ingredient_id, part) values (6, 7, 2);

-- Cappuccino has 1 unit of Steamed Milk
insert into BARISTA_RECIPE (drink_drink_id, ingredient_ingredient_id, part) values (6, 5, 1);

-- Cappuccino has 1 unit of Foamed Milk
insert into BARISTA_RECIPE (drink_drink_id, ingredient_ingredient_id, part) values (6, 6, 1);