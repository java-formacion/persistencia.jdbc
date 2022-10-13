package com.txurdi.persistencia.crud.basico;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Ejemplo básico para listar todas las personas de la bbdd
 * @author Ander Uraga Real
 *
 */
public class ListarPersonas {

	public static void main(String[] args) throws SQLException {
		
		System.out.println("Listado de personas");
		final String SQL = "SELECT id, nombre, nif, edad FROM persona ORDER BY nombre ASC LIMIT 500; ";
		
		// recursos autoclosables
		try( Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/txurdi?useSSL=false", "root", "root");
			 PreparedStatement pst = con.prepareStatement(SQL);
			 ResultSet rs = pst.executeQuery();	){
			
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
