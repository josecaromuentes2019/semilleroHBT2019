package com.hbt.semillero.dto;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;

public class PersonaClienteDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * Varables globales
	 */
	
	private Long idPersona;  
	private String nombre;
	private Long identidad;  
	private LocalDate feNacimiento;
	
	
	public Long getIdPersona() {
		return idPersona;
	}
	public void setIdPersona(Long idPersona) {
		this.idPersona = idPersona;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Long getIdentidad() {
		return identidad;
	}
	public void setIdentidad(Long identidad) {
		this.identidad = identidad;
	}
	public LocalDate getFeNacimiento() {
		return feNacimiento;
	}
	public void setFeNacimiento(LocalDate feNacimiento) {
		this.feNacimiento = feNacimiento;
	}
	
	
	/**
	 * Método encargado de convertir los datos recibidos en JSON al tipo PersonaClienteDTO.
	 * <b>Caso de Uso:</b>
	 * 
	 * @param arg Cadena que representa el objeto complejo JSON.
	 * @return Instancia con los datos recibidos.
	 */
	public static PersonaClienteDTO valueOf(String arg) {
		return JsonUtils.valueOf(arg, PersonaClienteDTO.class);
	}


	@Override
	public String toString() {
		return JsonUtils.toStringJson(this);
	}

}
