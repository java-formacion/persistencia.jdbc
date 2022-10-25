package com.txurdi.persistencia.modelo.pa;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;

import com.txurdi.persistencia.modelo.dao.ConnectionManager;
import com.txurdi.persistencia.modelo.pojo.Producto;

/**
 * Ejemplo para listar los productos de la bbdd mediante un Procedimiento Almacenado
 * @author Ander Uraga Real
 *
 */
public class ListarProductosPA {

	public static void main(String[] args) {
		
		System.out.println("¿Porque solo salen 2 productos si en la bbdd hay 6 ?");
		String SQL_SELECT = "{call productos_obtener_todos()}";
		
		try (	Connection con = ConnectionManager.open();
				CallableStatement cs = con.prepareCall(SQL_SELECT);
				ResultSet rs = cs.executeQuery()
			) {
			
			Producto producto;
			while (rs.next()) {
				
				producto = new Producto(rs.getLong("p.id"), rs.getString("p.nombre"), rs.getString("p.descripcion"), rs.getString("p.url_imagen"), rs.getBigDecimal("p.precio"), rs.getInt("p.descuento"), rs.getString("p.unidad_medida"), rs.getBigDecimal("p.precio_unidad_medida"), rs.getInt("p.cantidad"));
				System.out.println(producto);
			}		
			
		} catch (Exception e) {
			e.printStackTrace();
		}	

	}

}
