package com.txurdi.persistencia.crud.basico;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * Ejemplo básico para crear eliminar Persona por su ID<br>  
 *  
 * @author Ander Uraga Real
 *
 */
public class EliminarPersona {

	public static void main(String[] args) throws SQLException {

		final String SQL = "DELETE FROM `persona` WHERE `id` = ? ; ";

		// recursos autoclosables
		try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/txurdi?useSSL=false", "root",
				"root"); 
				PreparedStatement pst = con.prepareStatement(SQL);
				Scanner sc = new Scanner(System.in);) {

			// solicitar datos
			System.out.println("ID:");
			int id = Integer.parseInt(sc.nextLine());
			
			// sustituimmos intertogantes por valores
			pst.setInt(1, id);

			// ejecutar pst y nos retorna un int == affetedRows
			int affectedRows = pst.executeUpdate();

			String msj = (affectedRows == 1) ? "Persona eliminada" : "No se pudo eliminar!!!";
			System.out.println(msj);
									
		
		} catch (Exception e) {
			System.out.println("Excepcion " + e.getMessage());
		}

	}

}
