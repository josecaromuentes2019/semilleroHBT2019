package com.hbt.semillero.dto;

import java.time.LocalDate;

import javax.persistence.Column;

public class CompraDTO {
	

	
	private String nombre;
	private LocalDate fechaCompra;
	private Long idCliente; 
	private Long idComic;
	
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public LocalDate getFechaCompra() {
		return fechaCompra;
	}
	
	public void setFechaCompra(LocalDate fechaCompra) {
		this.fechaCompra = fechaCompra;
	}
	
	public Long getIdCliente() {
		return idCliente;
	}
	
	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}
	
	public Long getIdComic() {
		return idComic;
	}
	
	public void setIdComic(Long idComic) {
		this.idComic = idComic;
	} 
	
	

	
	
	
	
	

}
