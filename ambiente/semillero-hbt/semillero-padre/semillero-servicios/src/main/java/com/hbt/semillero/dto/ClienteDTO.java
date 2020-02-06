package com.hbt.semillero.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collection;

import javax.persistence.Column;

import com.hbt.semillero.entidad.Comic;

public class ClienteDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Long idCliente; 
	private String nombre;
	private Long identidad;  
	private LocalDate feNacimiento;
	private Collection<Comic> comic;
	
	
	
	
	public Collection<Comic> getComic() {
		return comic;
	}


	public void setComic(Collection<Comic> comic) {
		this.comic = comic;
	}


	public Long getIdCliente() {
		return idCliente;
	}
	
	
	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
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

	
	

}
