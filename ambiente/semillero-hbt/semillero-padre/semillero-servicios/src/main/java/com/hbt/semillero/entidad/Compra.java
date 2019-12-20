package com.hbt.semillero.entidad;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;



@Entity
@Table(name = "COMPRACOMIC")
public class Compra {
	
	
	@Id
	@SequenceGenerator(allocationSize = 1, name = "COMPRA_ID_GENERATOR", sequenceName = "SEC_COMPRACOMIC")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "COMPRA_ID_GENERATOR")
	private Long idCompra;  
	
	
	@Column(name = "COMPRACOMIC_ID")
	private String nombre;
	
	
	@Column(name = "COMPRACOMIC_COMPRADOS")
	private Long comicComprado; 
	
	
	@Column(name = "COMPRACOMIC_FECHA_VENTA")
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
	
	

}
