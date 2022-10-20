package com.txurdi.persistencia.modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.txurdi.persistencia.modelo.pojo.Persona;

public class PersonaDAO implements Singleton, Crudable<Persona> {

	private static PersonaDAO INSTANCE = null;
	
	// Sentencias SQL
	private static final String SQL_FIND_ALL = "SELECT id, nombre, nif, edad FROM persona ORDER BY nombre ASC LIMIT 500; ";
	
	

	private PersonaDAO() {
		super();
	}
	
	public static PersonaDAO getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new PersonaDAO();
		}
		return INSTANCE;
	}

	@Override
	public List<Persona> findAll() {

		ArrayList<Persona> regitros = new ArrayList<Persona>();		

		try (Connection con = ConnectionManager.open();
			 PreparedStatement pst = con.prepareStatement(SQL_FIND_ALL);
		     ResultSet rs = pst.executeQuery();
		){

			while (rs.next()) {
				
				Persona p = new Persona();
				p.setId( rs.getInt("id"));
				p.setNombre(rs.getNString("nombre"));
				p.setNif(rs.getString("nif"));
				// TODO resto de campos
				
				regitros.add(p);
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return regitros;
	}

	@Override
	public Persona findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Persona pojo) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void insert(Persona pojo) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean delete(int id) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

}
