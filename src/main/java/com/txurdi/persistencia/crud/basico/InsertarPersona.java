package com.txurdi.persistencia.crud.basico;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

/**
 * Ejemplo básico para crear una nueva persona<br>
 * id es autoincremental, no hace falta solicitarlo <br>
 * Cuidado el NIF debes er único, gestionamos la excepción <br>
 * 
 * @author Ander Uraga Real
 *
 */
public class InsertarPersona {

	public static void main(String[] args) throws SQLException {

		final String SQL = "INSERT INTO `persona` (`nombre`, `nif`, `edad`) VALUES ( ?, ? , ?) ; ";

		// recursos autoclosables
		try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/txurdi?useSSL=false", "root",
				"root"); 
				PreparedStatement pst = con.prepareStatement(SQL, PreparedStatement.RETURN_GENERATED_KEYS );
				Scanner sc = new Scanner(System.in);) {

			// solicitar datos
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

			// ejecutar pst y nos retorna un int == affetedRows
			int affectedRows = pst.executeUpdate();

			String msj = (affectedRows == 1) ? "Persona insertada" : "No se realizo la insert";
			System.out.println(msj);
			
			//recuperar id autogenerado
			if (affectedRows == 1) {
				try( ResultSet rs = pst.getGeneratedKeys() ){
					if( rs.next() ) {
						int id = rs.getInt(1);
						System.out.println("El ID generado de la persona es " + id);
					}
				}
				
			}
			
			

		} catch (MySQLIntegrityConstraintViolationException e) {
			System.out.println("NIF duplicado !!!! ");
		
		} catch (Exception e) {
			System.out.println("Excepcion " + e.getMessage());
		}

	}

}
