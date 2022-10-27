CREATE DATABASE  IF NOT EXISTS `txurdi`;
USE `txurdi`;

--
-- TABLAS
--

DROP TABLE IF EXISTS `departamentos`;
CREATE TABLE `departamentos` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45)  NOT NULL,
  `descripcion` text ,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9;
;

LOCK TABLES `departamentos` WRITE;
/*!40000 ALTER TABLE `departamentos` DISABLE KEYS */;
INSERT INTO `departamentos` VALUES (1,'Verduras',NULL),(2,'Lácteos',NULL),(3,'Congelados',NULL),(4,'Electrónica',NULL),(5,'Navidad',NULL),(6,'Pruebas','Prueba de departamento nuevo'),(7,'Pruebas','Prueba de departamento nuevo'),(8,'Informática','Mola un montón');
/*!40000 ALTER TABLE `departamentos` ENABLE KEYS */;
UNLOCK TABLES;


DROP TABLE IF EXISTS `persona`;
CREATE TABLE `persona` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(150)  NOT NULL,
  `nif` varchar(9)   DEFAULT NULL,
  `edad` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `nif_UNIQUE` (`nif`)
) ENGINE=InnoDB AUTO_INCREMENT=118   ;

LOCK TABLES `persona` WRITE;
/*!40000 ALTER TABLE `persona` DISABLE KEYS */;
INSERT INTO `persona` VALUES (3,'bea',NULL,23),(7,'dummy2','1111112H',44),(9,'dummy2','1111132H',44),(13,'ander','212121212',55),(15,'anastasia','121212s',44);
/*!40000 ALTER TABLE `persona` ENABLE KEYS */;
UNLOCK TABLES;


DROP TABLE IF EXISTS `productos`;

CREATE TABLE `productos` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `nombre` varchar(150)    NOT NULL,
  `descripcion` text   ,
  `url_imagen` varchar(45)    DEFAULT NULL,
  `precio` decimal(20,2) NOT NULL,
  `descuento` int DEFAULT NULL,
  `unidad_medida` varchar(45)    DEFAULT NULL,
  `precio_unidad_medida` decimal(20,2) DEFAULT NULL,
  `cantidad` int DEFAULT NULL,
  `departamentos_id` int NOT NULL,
  `activo` tinyint NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `fk_productos_departamentos1_idx` (`departamentos_id`),
  CONSTRAINT `fk_productos_departamentos1` FOREIGN KEY (`departamentos_id`) REFERENCES `departamentos` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 ;
   

LOCK TABLES `productos` WRITE;
/*!40000 ALTER TABLE `productos` DISABLE KEYS */;
INSERT INTO `productos` VALUES (1,'Producto primero','Descripción','tech.jfif',12.34,20,'litros',12.34,1,8,1),(2,'Producto2','Descripcion2',NULL,34.56,27,NULL,NULL,2,1,0),(3,'Producto3',NULL,NULL,67.89,NULL,NULL,NULL,3,2,0),(6,'Viejo','Prueba de descripción','tech (2).jfif',98.93,8,'kg',2.05,6,1,1);
/*!40000 ALTER TABLE `productos` ENABLE KEYS */;
UNLOCK TABLES;

-- 
-- Procedimientos almacenados
-- 

DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `sumar`(a INT, b INT) RETURNS int
    DETERMINISTIC
BEGIN
RETURN a+b;
END ;;
DELIMITER ;

DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `productos_borrar`(p_id INT)
BEGIN
UPDATE productos SET activo = 0 WHERE id = p_id;
END ;;
DELIMITER ;


DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `productos_insertar`(p_nombre VARCHAR(150), p_descripcion TEXT(1000), p_url_imagen VARCHAR(45), p_precio DECIMAL(20,2), p_descuento INT, p_unidad_medida VARCHAR(45), p_precio_unidad_medida DECIMAL(20,2), p_cantidad INT, p_departamentos_id INT)
BEGIN
INSERT INTO productos 
	(nombre, descripcion, url_imagen, precio, descuento, unidad_medida, precio_unidad_medida, cantidad, departamentos_id) 
    VALUES 
	(p_nombre, p_descripcion, p_url_imagen, p_precio, p_descuento, p_unidad_medida, p_precio_unidad_medida, p_cantidad, p_departamentos_id) ;
END ;;
DELIMITER ;


DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `productos_modificar`(p_nombre VARCHAR(150), p_descripcion TEXT(1000), p_url_imagen VARCHAR(45), p_precio DECIMAL(20,2), p_descuento INT, p_unidad_medida VARCHAR(45), p_precio_unidad_medida DECIMAL(20,2), p_cantidad INT, p_departamentos_id INT, p_id INT)
BEGIN
UPDATE productos 
SET nombre = p_nombre, descripcion = p_descripcion, url_imagen = p_url_imagen, precio = p_precio, descuento = p_descuento, 
	unidad_medida = p_unidad_medida, precio_unidad_medida = p_precio_unidad_medida, cantidad = p_cantidad, 
    departamentos_id = p_departamentos_id WHERE id = p_id;
END ;;
DELIMITER ;


DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `productos_obtener_borrados`()
BEGIN
SELECT * FROM productos p JOIN departamentos d ON p.departamentos_id = d.id WHERE p.activo = 0;
END ;;
DELIMITER ;


DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `productos_obtener_por_id`(p_id INT)
BEGIN
SELECT * FROM productos p JOIN departamentos d ON p.departamentos_id = d.id WHERE p.id = p_id AND p.activo = 1;
END ;;
DELIMITER ;

DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `productos_obtener_todos`()
BEGIN
SELECT * FROM productos p JOIN departamentos d ON p.departamentos_id = d.id WHERE p.activo = 1;
END ;;
DELIMITER ;


DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `productos_recuperar`(p_id INT)
BEGIN
UPDATE productos SET activo = 1 WHERE id = p_id;
END ;;
DELIMITER ;

DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sumar`(a INT, b INT)
BEGIN
SELECT CONCAT(a, ' mas ', b, ' es ', a + b);
END ;;
DELIMITER ;

-- end