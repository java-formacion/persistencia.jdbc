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


## Table de contenidos
* [Crud simple](#crud-simple)
* [DAO](#dao)


### Crud Simple
Ejemplos básicos para realizar un CRUD contra la tabla de persona.

Aprendermos a realizar la conexión a la bbdd y ejecurtar sentencias SELECT, INSERT, UPDATE y DELETE.




	
### DAO
TODO
