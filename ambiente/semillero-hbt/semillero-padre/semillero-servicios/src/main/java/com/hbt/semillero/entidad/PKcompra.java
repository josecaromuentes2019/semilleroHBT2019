package com.hbt.semillero.entidad;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class PKcompra implements Serializable{
	
	@Column(name="COM_ID_CLIENTE")
	private Long idClinete;
	
	@Column(name= "COM_ID_COMIC")
	private Long idComic;
	
	@Column(name= "COM_FECHA_VENTA")
	private LocalDate fecha;
	
	public PKcompra() {
		// TODO Auto-generated constructor stub
	}

	public PKcompra(Long idClinete, Long idComic, LocalDate fecha) {
		super();
		this.idClinete = idClinete;
		this.idComic = idComic;
		this.fecha = fecha;
	}

	public Long getIdClinete() {
		return idClinete;
	}

	public void setIdClinete(Long idClinete) {
		this.idClinete = idClinete;
	}

	public Long getIdComic() {
		return idComic;
	}

	public void setIdComic(Long idComic) {
		this.idComic = idComic;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}
	
	

}
