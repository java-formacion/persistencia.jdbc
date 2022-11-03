package com.txurdi.persistencia.modelo.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

//TODO mejorar PAs, alguno tiene * y la insert no actulizaza el ID



import java.util.List;

import com.txurdi.persistencia.modelo.pojo.Producto;

/**
 * Dao para productos ya prender a usaar PAs
 * @author Ander Uraga Real
 *
 */

public class ProductoDAO implements Crudable<Producto> {
	
	// instancia unica
	private static ProductoDAO INSTANCE = null;
	
	// SQLs con las llamadas a los PAs
	String SQL_SELECT_ALL = "{call productos_obtener_todos()}";
	
	// constructor privado
	private ProductoDAO() {
		super();		
	}

	// el metodo para acceder a la instancia unica
	public static ProductoDAO getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new ProductoDAO();
		}
		return INSTANCE;
	}
	
	@Override
	public List<Producto> findAll() {
		ArrayList<Producto> registros = new ArrayList<Producto>(); 
		try (	Connection con = ConnectionManager.open();
				CallableStatement cs = con.prepareCall(SQL_SELECT_ALL);
				ResultSet rs = cs.executeQuery()
			) {
				while (rs.next()) {
					registros.add(mapper(rs));
				}		
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return registros;
	}

	@Override
	public Producto findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Producto pojo) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void insert(Producto pojo) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean delete(int id) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Mappear los datos del ResultSet al pojo Producto
	 * @param rs ResultSet
	 * @return Producto
	 * @throws Exception no deberia lanzar la excepcion, si esta bien mapeado
	 */
	private Producto mapper(ResultSet rs) throws Exception {
		Producto p = new Producto();
		p.setId(rs.getLong("p.id"));
		p.setNombre(rs.getString("p.nombre"));
		//TODO mapear el resto de campos
		
		return p;
	}
	
}
