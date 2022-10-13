package com.txurdi.persistencia.crud.basico;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * Ejemplo básico para crear una nueva persona<br>
 * id es autoincremental, no hace falta solicitarlo <br>
 * 
 * @author Ander Uraga Real
 *
 */
public class InsertarPersona {

	public static void main(String[] args) throws SQLException {

		final String SQL = "INSERT INTO `persona` (`nombre`, `nif`, `edad`) VALUES ( ?, ? , ?) ; ";

		// recursos autoclosables
		try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/txurdi?useSSL=false", "root",
				"root"); PreparedStatement pst = con.prepareStatement(SQL); Scanner sc = new Scanner(System.in);) {

			// solicitar datos			
			// TODO harcoded data
			String nombre = "dummy2";
			String nif = "1111132H";
			int edad = 44;

			// sustituimmos intertogantes por valores
			pst.setString(1, nombre);
			pst.setString(2, nif);
			pst.setInt(3, edad);

			// ejecutar pst y nos retorna un int == affetedRows
			int affectedRows = pst.executeUpdate();

			String msj = (affectedRows == 1) ? "Persona insertada" : "No se realizo la insert";
			System.out.println(msj);

			//TODO gestionar Duplicate entry '1111111H' for key 'persona.nif_UNIQUE'
			
		} // 1ºtry

	}

}
