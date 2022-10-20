package com.txurdi.persistencia.modelo.dao;

import java.util.ArrayList;

import com.txurdi.persistencia.modelo.pojo.Persona;

/**
 * Test txapucero para probar ql DAO de persona
 * @author Ander Uraga Real
 *
 */
public class PersonaDAOTest {

	public static void main(String[] args) {
		
		
		PersonaDAO dao = PersonaDAO.getInstance();
		
		ArrayList<Persona> personas = (ArrayList<Persona>) dao.findAll();
		
		System.out.println(personas);
		

	}

}
