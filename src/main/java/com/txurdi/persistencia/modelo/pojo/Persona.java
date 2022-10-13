package com.txurdi.persistencia.modelo.pojo;

import java.io.Serializable;

public class Persona implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String nif;
	private String nombre;
	private int edad;
	
	public Persona() {
		super();
		this.nif = "";
		this.nombre = "";
		this.edad = 0;
	}

	public Persona(String nombre, int edad) {
		this();  // constructor por defecto
		this.nombre = nombre;
		this.edad = edad;
	}

	public String getNif() {
		return nif;
	}

	public void setNif(String nif) {
		this.nif = nif;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	@Override
	public String toString() {
		return "Persona [nif=" + nif + ", nombre=" + nombre + ", edad=" + edad + "]";
	}
	
	
	

}
