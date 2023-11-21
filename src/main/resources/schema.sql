CREATE TABLE  IF NOT EXISTS
pedidos (serial INT NOT NULL AUTO_INCREMENT,
nombre VARCHAR(20),
fecha  VARCHAR(20),
precio_con_descuento NUMERIC(10,2)DEFAULT 0,
id INT,
FOREIGN KEY (id) REFERENCES galleta(id),
PRIMARY KEY (SERIAL));


CREATE TABLE  IF NOT EXISTS
galleta (id INT NOT NULL,
sabor VARCHAR(20),
tamano  VARCHAR(20),
tiene_gluten BIT,
precio NUMERIC(10,2),
PRIMARY KEY (id));

CREATE TABLE  IF NOT EXISTS
tortas (serial INT NOT NULL AUTO_INCREMENT,
sabor VARCHAR(20),
tamano  VARCHAR(20),
con_crema BIT,
PRIMARY KEY (SERIAL));

CREATE TABLE  IF NOT EXISTS
donas (serial INT NOT NULL AUTO_INCREMENT,
sabor VARCHAR(20),
precio NUMERIC(10,2)DEFAULT 0,
con_relleno BIT,
PRIMARY KEY (SERIAL));

--INSERT INTO galleta values (123,'chocolate', 'grande', 1, 1500)
INSERT INTO donas values (123,'chocolate',1500, 1)