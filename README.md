# persistencia.jdbc

Proyecto java para la capa de persistencia con JDBC.

Usamos un SGBD de MySQL y driver mysql-connector-java 5.1.46.

## Importar proyecto a eclipse

1. Clonar repositorio desde Github a Eclipse
2. Importar > Existing Maven Project

## Test Junit para comprobar la conexión

com.txurdi.persistencia.jdbc.TestConnection

```
Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/txurdi", "root", "root");
assertNotNull("La conexion no puede ser nula",con);
```



# header H1
## header H2
### header H3
#### header H4
##### header H5
###### header H6


## Table of contents
* [General info](#general-info)
* [Technologies](#technologies)
* [Setup](#setup)

## General info
This project is simple Lorem ipsum dolor generator.
	
## Technologies
Project is created with:
* Lorem version: 12.3
* Ipsum version: 2.33
* Ament library version: 999
	
## Setup
To run this project, install it locally using npm:

```
$ cd ../lorem
$ npm install
$ npm start
```
