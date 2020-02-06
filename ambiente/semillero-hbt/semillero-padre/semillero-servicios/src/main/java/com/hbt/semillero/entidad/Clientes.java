package com.hbt.semillero.entidad;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name = "CLIENTE")
public class Clientes implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	
	@Id
	@SequenceGenerator(allocationSize = 1, name = "CLIENTE_ID_GENERATOR", sequenceName = "SEC_CLIENTE")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CLIENTE_ID_GENERATOR")
	@Column(name = "CLIE_ID")
	private Long idCliente;  
	
	@Column(name = "CLIE_NOMBRE")
	private String nombre;
	
	@Column(name = "CLIE_CC")
	private Long identidad;  
	
	@Column(name = "CLIE_FECHA_NACIMIENTO")
	private LocalDate feNacimiento;
	
	
	@ManyToMany
	@JoinTable(name= "COMPRA", joinColumns={@JoinColumn(name="CLIE_ID")},
				inverseJoinColumns={@JoinColumn(name="SCID")})
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
