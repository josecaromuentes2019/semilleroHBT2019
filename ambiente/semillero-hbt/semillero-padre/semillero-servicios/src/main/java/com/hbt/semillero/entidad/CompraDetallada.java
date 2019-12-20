package com.hbt.semillero.entidad;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "COMPRADETALLADA")
public class CompraDetallada {
	
	
	
	@Id
	@SequenceGenerator(allocationSize = 1, name = "COMPRA_DET_ID_GENERATOR", sequenceName = "\"SEC_COMPRADETALLADA\" ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "COMPRA_DET_ID_GENERATOR")
	private Long idCompraDe;
	
	
	
	
	@JoinColumn(name = "COMPRADETALLADA_ID_PERSONA")
	@ManyToMany(fetch = FetchType.LAZY)
	private Long idPersonaCompra;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@Column(name = "COMPRADETALLADA_ID_VENTA")
	private Long idPersonaVenta;


	public Long getIdCompraDe() {
		return idCompraDe;
	}


	public void setIdCompraDe(Long idCompraDe) {
		this.idCompraDe = idCompraDe;
	}


	public Long getIdPersonaCompra() {
		return idPersonaCompra;
	}


	public void setIdPersonaCompra(Long idPersonaCompra) {
		this.idPersonaCompra = idPersonaCompra;
	}


	public Long getIdPersonaVenta() {
		return idPersonaVenta;
	}


	public void setIdPersonaVenta(Long idPersonaVenta) {
		this.idPersonaVenta = idPersonaVenta;
	}
	
	
	

}
