/**
 * 
 */
package com.txurdi.persistencia.jdbc;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.txurdi.persistencia.modelo.dao.PersonaDAO;
import com.txurdi.persistencia.modelo.pojo.Persona;

/**
 * @author Ander Uraga Real
 *
 */
public class PersonaDAOTest {
	
	static PersonaDAO dao = null;
	
	
	// objeto mock para pruebas
	static Persona pepe = null; 
	static final String PEPE_NOMBRE = "pepe";
	static final String PEPE_NIF = "1111111H";
	static final int PEPE_EDAD = 40;
	
	

	/**
	 * Antes de empezar los metodos de test
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
		dao = PersonaDAO.getInstance();
	}

	/**
	 * Al finalizar todos los metodos de test
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		dao = null;
	}

	/**
	 * antes de cada test creamos a "pepe", en este caso tenemos 4
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		
		pepe = new Persona();
		pepe.setNombre(PEPE_NOMBRE);
		pepe.setNif(PEPE_NIF);
		pepe.setEdad(PEPE_EDAD);
		
		dao.insert(pepe);	
		
	}

	/**
	 * Despues de cada test lo eliminamos, en este caso tenemos 4
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		
		dao.delete(pepe.getId());
	}

	
	/************************** metodos TEST **************************************/
	
	
	/**
	 * Test method for {@link com.txurdi.persistencia.modelo.dao.PersonaDAO#findAll()}.
	 */
	@Test
	public void testFindAll() {
		
		assertTrue("por lo menos debe existir pepe", dao.findAll().size() > 0);
	}

	/**
	 * Test method for {@link com.txurdi.persistencia.modelo.dao.PersonaDAO#findById(int)}.
	 */
	@Test
	public void testFindById() {
		
		//recupero a pepe y compruebo sus campos
		Persona p = dao.findById(pepe.getId());
		assertEquals( PEPE_NOMBRE, p.getNombre());
		assertEquals( PEPE_NIF, p.getNif());
		assertEquals( PEPE_EDAD, p.getEdad());
				
		// comprueba un id que no exista
		assertNull("no debe existir nadie con id == 0", dao.findById(0) );
		
	}

	/**
	 * Test method for {@link com.txurdi.persistencia.modelo.dao.PersonaDAO#update(com.txurdi.persistencia.modelo.pojo.Persona)}.
	 * @throws Exception 
	 */
	@Test
	public void testUpdate() throws Exception {
		
		// cambiar todos los datos y comrpobar que se cambian
		int id = pepe.getId();
		String nombre = "pepa";
		String nif = "2222222G";
		int edad = 50;
		
		pepe.setEdad(edad);
		pepe.setNif(nif);
		pepe.setNombre(nombre);
		
		dao.update(pepe);
		
		Persona pepa = dao.findById(id);
		assertEquals( id, pepa.getId());
		assertEquals( nombre, pepa.getNombre());
		assertEquals( nif, pepa.getNif());
		assertEquals( edad, pepa.getEdad());
		
		// comprobar nif unico		
		try {
			pepa.setNif(PEPE_NIF);
			dao.update(pepa);
			fail("deberia haber lanzado excepcion de NIF UNICO");
		}catch (Exception e) {
			
			//si la lanza la excepcion, funciona correctamente
			assertTrue(true);
		}	
		
		
		
	}

	/**
	 * Test method for {@link com.txurdi.persistencia.modelo.dao.PersonaDAO#insert(com.txurdi.persistencia.modelo.pojo.Persona)}.
	 */
	@Test
	public void testInsert() {
		
		// setUp ya se inserta pepe
		 int id = pepe.getId();
		assertTrue ("el id generado debe ser mayor a 0", id > 0);
		
		// probar NIF duplicado
		try {
			dao.insert(pepe);
			fail("deberia haber lanzado excepcion por NIF duplicado");
		}catch (Exception e) {
			//funciona si lanza exception
			assertTrue(true);
		}	
		
	}

	/**
	 * Test method for {@link com.txurdi.persistencia.modelo.dao.PersonaDAO#delete(int)}.
	 * @throws Exception 
	 */
	@Test
	public void testDelete() throws Exception {
		
		assertTrue( "Se debria poder eliminar a pepe", dao.delete(pepe.getId()));
		assertFalse( "No se puede eliminar algo que no existe id == 0", dao.delete(0));
		
	}

}
