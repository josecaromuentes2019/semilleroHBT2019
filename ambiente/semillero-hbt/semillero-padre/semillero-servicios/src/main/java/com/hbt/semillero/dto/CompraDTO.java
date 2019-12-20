package com.hbt.semillero.dto;

import java.time.LocalDate;

import javax.persistence.Column;

public class CompraDTO {
	
	
	private Long idCompra; 
	private String nombre;
	private Long comicComprado; 
	private LocalDate fechaCompra;
	
	

	public Long getIdCompra() {
		return idCompra;
	}
	

	public void setIdCompra(Long idCompra) {
		this.idCompra = idCompra;
	}


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


	public Long getComicComprado() {
		return comicComprado;
	}


	public void setComicComprado(Long comicComprado) {
		this.comicComprado = comicComprado;
	}
	
	
	
	
	

}
