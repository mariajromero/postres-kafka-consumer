CREATE TABLE  IF NOT EXISTS
pedido (serial INT NOT NULL AUTO_INCREMENT,
nombre VARCHAR(20),
fecha  VARCHAR(20),
precio NUMERIC(10,2)DEFAULT 0,
PRIMARY KEY (SERIAL));