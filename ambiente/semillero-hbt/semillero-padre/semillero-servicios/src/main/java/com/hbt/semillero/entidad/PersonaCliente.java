package com.hbt.semillero.entidad;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "VENTAPORPERSONA")
public class PersonaCliente implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	
	@Id
	@SequenceGenerator(allocationSize = 1, name = "PERSONA_ID_GENERATOR", sequenceName = "SEC_VENTAPORPERSONAJE")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PERSONA_ID_GENERATOR")
	@Column(name = "VENTAPERS_ID")
	private Long idPersona;  
	
	
	@Column(name = "VENTAPERS_NOMBRE")
	private String nombre;
	
	@Column(name = "VENTAPERS_DOCUMENTO")
	private Long identidad;  
	
	
	@Column(name = "VENTAPERS_FECHA_NACIMIENTO")
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
	
	
	

}
