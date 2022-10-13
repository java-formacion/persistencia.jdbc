package com.txurdi.persistencia.crud.basico;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * Ejemplo básico para buscar una persona por su Id <br>
 * Se debe preguntar por consola el id a buscar <br>
 * Si lo encuentra muestra todos sus datos <br>
 * Si no existe indicar mostrar un mensaje <br>
 * @author Ander Uraga Real
 *
 */
public class BuscarPersonaPorId {

	public static void main(String[] args) throws SQLException {
		
		
		final String SQL = "SELECT id, nombre, nif, edad FROM persona WHERE id = ? ; ";
		
		// recursos autoclosables
		try( Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/txurdi?useSSL=false", "root", "root");
			 PreparedStatement pst = con.prepareStatement(SQL);
			 Scanner sc = new Scanner(System.in);
			){
			
			// solicitar id
			System.out.println("Dime el ID de la persona a buscar:");
			int id = Integer.parseInt(sc.nextLine());
			
			// sustituimmos el 1º ? por la variable id
			pst.setInt(1, id);
			
			try( ResultSet rs = pst.executeQuery() ) {
								
				//bucle para listar
				if (rs.next() ) {
				
					id = rs.getInt("id");
					String nombre = rs.getString("nombre");
					String nif = rs.getString("nif");
					int edad = rs.getInt("edad");
					
					System.out.printf("%-5s %-10s %-9s %-2s \n",id, nombre,nif,edad);
					
				}else {
					System.out.println("Lo sentimos pero no existe la persona");
				}
				
			}// 2º try		
			
			
		}// 1ºtry
		

	}

}
