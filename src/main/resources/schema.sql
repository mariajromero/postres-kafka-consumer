CREATE TABLE  IF NOT EXISTS
galleta
(serial INT NOT NULL AUTO_INCREMENT,
sabor VARCHAR(20),
tamano  VARCHAR(20),
tiene_gluten BIT,
precio NUMERIC(10,2)DEFAULT 0,
PRIMARY KEY (SERIAL));

CREATE TABLE  IF NOT EXISTS
pedidos
(serial INT NOT NULL AUTO_INCREMENT,
nombre VARCHAR(20),
fecha  VARCHAR(20),
precio_con_descuento NUMERIC(10,2)DEFAULT 0,
id INT,
FOREIGN KEY (id) REFERENCES galleta(serial),
PRIMARY KEY (SERIAL));


CREATE TABLE  IF NOT EXISTS
torta
(serial INT NOT NULL AUTO_INCREMENT,
sabor VARCHAR(20),
tamano  VARCHAR(20),
con_crema BIT,
PRIMARY KEY (SERIAL));

CREATE TABLE  IF NOT EXISTS
dona
(serial INT NOT NULL AUTO_INCREMENT,
sabor VARCHAR(20),
precio NUMERIC(10,2)DEFAULT 0,
con_relleno BIT,
PRIMARY KEY (SERIAL));

INSERT INTO galleta values (001,'chocolate','grande',1,1500);
INSERT INTO galleta values (002,'chocolate','mediana',1,1400);
INSERT INTO galleta values (003,'chocolate','pequena',1,1300);
INSERT INTO galleta values (004,'caramelo','grande',1,1500);
INSERT INTO galleta values (005,'caramelo','mediana',1,1400);
INSERT INTO galleta values (006,'caramelo','pequeña',1,1300);
INSERT INTO galleta values (007,'milo','grande',1,1500);
INSERT INTO galleta values (008,'milo','grande',1,1400);
INSERT INTO galleta values (009,'milo','grande',1,1300);
INSERT INTO galleta values (010,'avena','grande',0,1500);

INSERT INTO dona values (001,'chocolate',1600, 1);
INSERT INTO dona values (002,'chocolate',1500, 0);
INSERT INTO dona values (003,'mani',1600, 1);
INSERT INTO dona values (004,'mani',1500, 0);
INSERT INTO dona values (005,'arequipe',1600, 1);
INSERT INTO dona values (006,'arequipe',1500, 0);
INSERT INTO dona values (007,'mora',1600, 1);
INSERT INTO dona values (008,'miel',1500, 1);
INSERT INTO dona values (009,'canale',1500, 1);
INSERT INTO dona values (010,'chocolate',1500, 1);

INSERT INTO torta values (001,'chocolate','grande', 1);
INSERT INTO torta values (002,'vainilla','grande', 1);
INSERT INTO torta values (003,'limon','grande', 0);
INSERT INTO torta values (004,'naranja','grande', 1);
INSERT INTO torta values (005,'vino','grande', 0);
INSERT INTO torta values (006,'red velvet','grande', 1);
INSERT INTO torta values (007,'almendra','grande', 1);
INSERT INTO torta values (008,'fresa','grande', 0);
INSERT INTO torta values (009,'amapola','grande', 0);
INSERT INTO torta values (010,'zanahoria','grande', 1);