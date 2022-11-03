package com.txurdi.persistencia.modelo.pa;

import com.txurdi.persistencia.modelo.dao.ProductoDAO;
import com.txurdi.persistencia.modelo.pojo.Producto;

/**
 * Clase para probar el listado de productos con el DAO
 * @author Ander Uraga Real
 *
 */
public class ListarProductosConDAO {

	public static void main(String[] args) {
		
		ProductoDAO dao = ProductoDAO.getInstance();
		
		for (Producto p : dao.findAll()) {
			System.out.println(p);
		}
		
		
	}
	
	
}
