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
	private static final String SQL_FIN_BY_ID = "SELECT id, nombre, nif, edad FROM persona WHERE id = ? ; ";
	private static final String SQL_UPDATE = "UPDATE `persona` SET `nombre` = ?, `nif` = ?, `edad` = ? WHERE `id` = ? ; ";
	private static final String SQL_INSERT = "INSERT INTO `persona` (`nombre`, `nif`, `edad`) VALUES (?,?,?) ; ";
	private static final String SQL_DELETE = "DELETE FROM `persona` WHERE `id` = ? ; ";

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

		try (
				Connection con = ConnectionManager.open();
				PreparedStatement pst = con.prepareStatement(SQL_FIND_ALL);
				ResultSet rs = pst.executeQuery();
			) {

				while (rs.next()) {
					regitros.add(mapper(rs));
				}

		} catch (SQLException e) {			
			e.printStackTrace();
		}

		return regitros;
	}

	@Override
	public Persona findById(int id) {
		Persona registro = null;

		try (
				Connection con = ConnectionManager.open(); 
				PreparedStatement pst = con.prepareStatement(SQL_FIN_BY_ID);
		) {

			pst.setInt(1, id);

			try (ResultSet rs = pst.executeQuery()) {
				while (rs.next()) {
					registro = mapper(rs);
				}
			}

		} catch (SQLException e) {			
			e.printStackTrace();
		}

		return registro;
	}

	@Override
	public void update(Persona pojo) throws Exception {

		try (
				Connection con = ConnectionManager.open(); 
				PreparedStatement pst = con.prepareStatement(SQL_UPDATE);
		) {

			// sustituimmos intertogantes por valores
			pst.setString(1, pojo.getNombre());
			pst.setString(2, pojo.getNif());
			pst.setInt(3, pojo.getEdad());
			pst.setInt(4, pojo.getId());

			pst.executeUpdate();

		}

	}

	@Override
	public void insert(Persona pojo) throws Exception {

		try (
				Connection con = ConnectionManager.open(); 
				PreparedStatement pst = con.prepareStatement(SQL_INSERT, PreparedStatement.RETURN_GENERATED_KEYS );
			) {

				// sustituimmos intertogantes por valores
				pst.setString(1, pojo.getNombre());
				pst.setString(2, pojo.getNif());
				pst.setInt(3, pojo.getEdad());
	
				// ejecutar pst y nos retorna un int == affetedRows
				int affectedRows = pst.executeUpdate();
	
				// recuperar id autogenerado
				if (affectedRows == 1) {
					try (ResultSet rs = pst.getGeneratedKeys()) {
						if (rs.next()) {
							int id = rs.getInt(1);
							pojo.setId(id);
						}
					}
	
				}//if
		}//try
	}

	@Override
	public boolean delete(int id) throws Exception {
		boolean eliminado = false;
		
		try (
				Connection con = ConnectionManager.open(); 
				PreparedStatement pst = con.prepareStatement(SQL_DELETE);
			) {
			
				pst.setInt(1, id);				
				if ( pst.executeUpdate() == 1 ) {
					eliminado = true;
				}			
		}		
		return eliminado;
	}

	/**
	 * Mapea los datos de un registro del ResultSet a un Pojo
	 * @param rs ResultSet
	 * @return Persona
	 * @throws SQLException
	 */
	private Persona mapper(ResultSet rs) throws SQLException {
		
		Persona p = new Persona();
		
		p.setId(rs.getInt("id"));
		p.setNombre(rs.getNString("nombre"));
		p.setNif(rs.getString("nif"));
		p.setEdad(rs.getInt("edad"));
		
		return p;
	}
	
}
