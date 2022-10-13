package com.txurdi.persistencia.crud.basico;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
		
		System.out.println("Listado de personas");
		final String SQL = "SELECT id, nombre, nif, edad FROM persona WHERE id = ? ; ";
		
		// recursos autoclosables
		try( Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/txurdi?useSSL=false", "root", "root");
			 PreparedStatement pst = con.prepareStatement(SQL);
			 	){
			
			pst.setInt(1, 3);
			
			//ResultSet rs = pst.executeQuery();
			
			System.out.printf("----------------------------------------------------------------------------------- \n");
			System.out.printf("----- persona  -------------------------------------------------------------------- \n");
			System.out.printf("----------------------------------------------------------------------------------- \n");
			System.out.printf("%-5s %-50s %-9s %-2s \n","id", "nombre","nif","edad");
			System.out.printf("----------------------------------------------------------------------------------- \n");
			
			//bucle para listar
			while (rs.next() ) {
			
				int id = rs.getInt("id");
				String nombre = rs.getString("nombre");
				String nif = rs.getString("nif");
				int edad = rs.getInt("edad");
				
				System.out.printf("%-5s %-50s %-9s %-2s \n",id, nombre,nif,edad);
				
			}//while
			System.out.printf("---------------------------------------------------------------------------------- \n");		
			
		}// try
		

	}

}
