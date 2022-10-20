package com.txurdi.persistencia.modelo.dao;

import java.util.List;

/**
 * Definimos los metodós básicos de CRUD para un <b>P</b>ojo <br> el cual lo deben implementar los <b>DAO</b>s  Data Acces Object
 * C: Create <br>
 * R: Read <br>
 * U: Updata <br>
 * D: Delete <br>
 * @author Ander Uraga Real
 *
 */
public interface Crudable<P> {

	/**
	 * Obtiene un listado de Pojos ordenados por Id descendente y limitado a 500 registros
	 * @return Listado de Pojos, si existen registros retorna lista vacia
	 */
	List<P> findAll();
	
	/**
	 * Busca un registro por su identificaodr
	 * @param id int identificador
	 * @return registro con datos si lo encuentra, <b>null</b> en caso contario
	 */
	P findById(int id);
	
	
	/**
	 * Modifica un registro
	 * @param p Pojo o registro a modificar
	 * @throws Excepction si no cumple con las constarins de la bbdd
	 */
	void update(P pojo) throws Exception;
	
	
	/**
	 * Inserta un registro y actualiza su id autogenerado
	 * @param p Pojo o registro a insertar
	 * @throws Excepction si no cumple con las constarins de la bbdd
	 */
	void insert(P pojo) throws Exception;
	
	
	/**
	 * elimina un registro por su identificador
	 * @param id int identificador
	 * @return true si elimina el registro, false si no elimina 
	 * @throws Exception si no cumple con las constarins de la bbdd
	 * 
	 */
	boolean delete(int id) throws Exception;

	
	
	
	
}
