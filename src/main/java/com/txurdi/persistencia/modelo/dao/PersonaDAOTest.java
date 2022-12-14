package com.txurdi.persistencia.modelo.dao;

import java.util.ArrayList;

import com.txurdi.persistencia.modelo.pojo.Persona;

/**
 * Test rapido con un main para probar el DAO de persona. <br>
 * El Test de verdad lo puedes encontrar en <b>src/test/java</b>
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
