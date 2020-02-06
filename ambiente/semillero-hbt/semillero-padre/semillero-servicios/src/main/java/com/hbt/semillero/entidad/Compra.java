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
@Table(name = "COMPRA")
public class Compra {
	
	
	@Id
	private PKcompra pk;
	
	@Column(name = "COM_NOMBRE")
	private String nombre;
	
	
	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public PKcompra getPk() {
		return pk;
	}


	public void setPk(PKcompra pk) {
		this.pk = pk;
	}

	
	
	
	

}
