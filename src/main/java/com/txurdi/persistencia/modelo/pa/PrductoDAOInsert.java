package com.txurdi.persistencia.modelo.pa;

import com.txurdi.persistencia.modelo.dao.ProductoDAO;
import com.txurdi.persistencia.modelo.pojo.Producto;

/**
 * Ejemplo rapido para pobar la insert del DAOProducto
 * @author Ander Uraga Real
 *
 */
public class PrductoDAOInsert {

	public static void main(String[] args) throws Exception {
		
		ProductoDAO dao = ProductoDAO.getInstance();
		
		Producto p = new Producto("tortilla");
		System.out.println(p);		
		
		dao.insert(p);
		
		System.out.println("El ID deberia estar actualizado desde la bbdd");
		System.out.println(p);
		
		
	}

}
