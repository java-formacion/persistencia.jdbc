package com.txurdi.persistencia.crud.basico;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

/**
 * Ejemplo básico para crear modificar todos los datos de una Persona por su ID<br>  
 * Cuidado el NIF debes er único, gestionamos la excepción <br>
 * 
 * @author Ander Uraga Real
 *
 */
public class ModificarPersona {

	public static void main(String[] args) throws SQLException {

		final String SQL = "UPDATE `persona` SET `nombre` = ?, `nif` = ?, `edad` = ? WHERE `id` = ? ; ";

		// recursos autoclosables
		try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/txurdi?useSSL=false", "root",
				"root"); 
				PreparedStatement pst = con.prepareStatement(SQL);
				Scanner sc = new Scanner(System.in);) {

			// solicitar datos
			System.out.println("ID:");
			int id = Integer.parseInt(sc.nextLine());
			
			System.out.println("Nombre:");
			String nombre = sc.nextLine();
			
			System.out.println("Nif:");
			String nif = sc.nextLine();
			
			System.out.println("Edad [0-150]");
			int edad = Integer.parseInt(sc.nextLine());

			// sustituimmos intertogantes por valores
			pst.setString(1, nombre);
			pst.setString(2, nif);
			pst.setInt(3, edad);
			pst.setInt(4, id);

			// ejecutar pst y nos retorna un int == affetedRows
			int affectedRows = pst.executeUpdate();

			String msj = (affectedRows == 1) ? "Persona modificada" : "No se pudo modificar!!!";
			System.out.println(msj);
									

		} catch (MySQLIntegrityConstraintViolationException e) {
			System.out.println("NIF duplicado !!!! ");
		
		} catch (Exception e) {
			System.out.println("Excepcion " + e.getMessage());
		}

	}

}
