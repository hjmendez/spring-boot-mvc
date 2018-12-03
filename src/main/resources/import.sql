/* Populate tables */
INSERT INTO clients (name,surname,email,create_at,birth_date,photo) VALUES ('A','A','aguzman@atsistemas.com', '2018-03-11', '2018-03-11',null);
INSERT INTO clients (name,surname,email,create_at,birth_date,photo) VALUES ('B','B','apena@atsistemas.com', '2017-03-11', '2018-03-11',null);
INSERT INTO clients (name,surname,email,create_at,birth_date,photo) VALUES ('C','C','apena@atsistemas.com', '2017-03-11', '2018-03-11',null);
INSERT INTO clients (name,surname,email,create_at,birth_date,photo) VALUES ('D','D','apena@atsistemas.com', '2017-03-11', '2018-03-11',null);
INSERT INTO clients (name,surname,email,create_at,birth_date,photo) VALUES ('E','E','apena@atsistemas.com', '2017-03-11', '2018-03-11',null);
INSERT INTO clients (name,surname,email,create_at,birth_date,photo) VALUES ('F','F','apena@atsistemas.com', '2017-03-11', '2018-03-11',null);
INSERT INTO clients (name,surname,email,create_at,birth_date,photo) VALUES ('G','G','apena@atsistemas.com', '2017-03-11', '2018-03-11',null);


/* Populate tabla de productos */
INSERT INTO productos (nombre,precio,create_at) VALUES ('Panasonic Pantalla LCD', 12.50, NOW());
INSERT INTO productos (nombre,precio,create_at) VALUES ('Sony Camara digital DSW-WS', 33.15, NOW());
INSERT INTO productos (nombre,precio,create_at) VALUES ('Apple iPod shuffle', 28.99, NOW());
INSERT INTO productos (nombre,precio,create_at) VALUES ('Hewlett Packard Multinacional F2280', 3500, NOW());


/*Populate tabla de facturas */
INSERT INTO facturas (descripcion, observacion, cliente_id, create_at) VALUES ('Factura equipos oficina',null,1,NOW());
INSERT INTO facturas_items(cantidad, factura_id, producto_id) values (1,1,1);
INSERT INTO facturas_items(cantidad, factura_id, producto_id) values (2,1,2);
INSERT INTO facturas_items(cantidad, factura_id, producto_id) values (1,1,3);

INSERT INTO facturas (descripcion, observacion, cliente_id, create_at) VALUES ('Factura Bicicleta',null,1,NOW());
INSERT INTO facturas_items(cantidad, factura_id, producto_id) values (1,2,1);


