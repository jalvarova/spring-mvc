/* Populate tables */
INSERT INTO clients (name, last_name, email, create_date, photo) VALUES('Alvaro', 'Aguinaga', 'alvaro92a18@gmail.com', '2017-08-01', '6b11883a-e3ef-4958-b4f5-24f4f74d55e9_Alvaro Aguinaga.jpg');
INSERT INTO clients (name, last_name, email, create_date, photo) VALUES('John', 'Doe', 'john.doe@gmail.com', '2017-08-02', '');
INSERT INTO clients (name, last_name, email, create_date, photo) VALUES('Linus', 'Torvalds', 'linus.torvalds@gmail.com', '2017-08-03', '');
INSERT INTO clients (name, last_name, email, create_date, photo) VALUES('Jane', 'Doe', 'jane.doe@gmail.com', '2017-08-04', '');
INSERT INTO clients (name, last_name, email, create_date, photo) VALUES('Rasmus', 'Lerdorf', 'rasmus.lerdorf@gmail.com', '2017-08-05', '');
INSERT INTO clients (name, last_name, email, create_date, photo) VALUES('Erich', 'Gamma', 'erich.gamma@gmail.com', '2017-08-06', '');
INSERT INTO clients (name, last_name, email, create_date, photo) VALUES('Richard', 'Helm', 'richard.helm@gmail.com', '2017-08-07', '');
INSERT INTO clients (name, last_name, email, create_date, photo) VALUES('Ralph', 'Johnson', 'ralph.johnson@gmail.com', '2017-08-08', '');
INSERT INTO clients (name, last_name, email, create_date, photo) VALUES('John', 'Vlissides', 'john.vlissides@gmail.com', '2017-08-09', '');
INSERT INTO clients (name, last_name, email, create_date, photo) VALUES('James', 'Gosling', 'james.gosling@gmail.com', '2017-08-010', '');
INSERT INTO clients (name, last_name, email, create_date, photo) VALUES('Bruce', 'Lee', 'bruce.lee@gmail.com', '2017-08-11', '');
INSERT INTO clients (name, last_name, email, create_date, photo) VALUES('Johnny', 'Doe', 'johnny.doe@gmail.com', '2017-08-12', '');
INSERT INTO clients (name, last_name, email, create_date, photo) VALUES('John', 'Roe', 'john.roe@gmail.com', '2017-08-13', '');
INSERT INTO clients (name, last_name, email, create_date, photo) VALUES('Jane', 'Roe', 'jane.roe@gmail.com', '2017-08-14', '');
INSERT INTO clients (name, last_name, email, create_date, photo) VALUES('Richard', 'Doe', 'richard.doe@gmail.com', '2017-08-15', '');
INSERT INTO clients (name, last_name, email, create_date, photo) VALUES('Janie', 'Doe', 'janie.doe@gmail.com', '2017-08-16', '');
INSERT INTO clients (name, last_name, email, create_date, photo) VALUES('Phillip', 'Webb', 'phillip.webb@gmail.com', '2017-08-17', '');
INSERT INTO clients (name, last_name, email, create_date, photo) VALUES('Stephane', 'Nicoll', 'stephane.nicoll@gmail.com', '2017-08-18', '');
INSERT INTO clients (name, last_name, email, create_date, photo) VALUES('Sam', 'Brannen', 'sam.brannen@gmail.com', '2017-08-19', '');  
INSERT INTO clients (name, last_name, email, create_date, photo) VALUES('Juergen', 'Hoeller', 'juergen.Hoeller@gmail.com', '2017-08-20', ''); 
INSERT INTO clients (name, last_name, email, create_date, photo) VALUES('Janie', 'Roe', 'janie.roe@gmail.com', '2017-08-21', '');
INSERT INTO clients (name, last_name, email, create_date, photo) VALUES('John', 'Smith', 'john.smith@gmail.com', '2017-08-22', '');
INSERT INTO clients (name, last_name, email, create_date, photo) VALUES('Joe', 'Bloggs', 'joe.bloggs@gmail.com', '2017-08-23', '');
INSERT INTO clients (name, last_name, email, create_date, photo) VALUES('John', 'Stiles', 'john.stiles@gmail.com', '2017-08-24', '');
INSERT INTO clients (name, last_name, email, create_date, photo) VALUES('Richard', 'Roe', 'stiles.roe@gmail.com', '2017-08-25', '');

/* Populate tabla productos */
INSERT INTO products (name, price, create_date) VALUES('Panasonic Pantalla LCD', 259990, NOW());
INSERT INTO products (name, price, create_date) VALUES('Sony Camara digital DSC-W320B', 123490, NOW());
INSERT INTO products (name, price, create_date) VALUES('Apple iPod shuffle', 1499990, NOW());
INSERT INTO products (name, price, create_date) VALUES('Sony Notebook Z110', 37990, NOW());
INSERT INTO products (name, price, create_date) VALUES('Hewlett Packard Multifuncional F2280', 69990, NOW());
INSERT INTO products (name, price, create_date) VALUES('Bianchi Bicicleta Aro 26', 69990, NOW());
INSERT INTO products (name, price, create_date) VALUES('Mica Comoda 5 Cajones', 299990, NOW());


/* Creamos algunas facturas */
INSERT INTO invoices (description, observation, client_id, create_date) VALUES('Factura equipos de oficina', 'Equipo completo', 1, NOW());
INSERT INTO invoices_items (amount, invoice_id, product_id) VALUES(1, 1, 1);
INSERT INTO invoices_items (amount, invoice_id, product_id) VALUES(2, 1, 4);
INSERT INTO invoices_items (amount, invoice_id, product_id) VALUES(1, 1, 5);
INSERT INTO invoices_items (amount, invoice_id, product_id) VALUES(1, 1, 7);

INSERT INTO invoices (description, observation, client_id, create_date) VALUES('Factura Bicicleta', 'Alguna nota importante!', 1, NOW());
INSERT INTO invoices_items (amount, invoice_id, product_id) VALUES(3, 2, 6);

