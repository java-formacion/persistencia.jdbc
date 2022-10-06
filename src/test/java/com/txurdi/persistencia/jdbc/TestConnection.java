package com.txurdi.persistencia.jdbc;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.junit.Test;

public class TestConnection {

	@Test
	public void test() {
		// fail("Not yet implemented");
		// assertTrue( 2 == 2 );
		// assertFalse( 2 != 3);
		// assertTrue("intersante escribir porque falla", 2 == 3);

		// comprobar que exsite la libreria
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			fail("No hemos cargado el driver, mira el pom.xml y mete la dependencia");
		}

		// establecer conexion con la bbdd txurdi
		try {
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/txurdi", "root", "root");
			assertNotNull("La conexion no puede ser nula",con);
		} catch (SQLException e) {
			fail( "Parece que no existe la bbdd txurdi, o los url de conexion no es correcta");
		}

	}

}
