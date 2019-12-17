package com.hbt.semillero.precioIva;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;
import com.hbt.semillero.dto.ComicDTO;
import com.hbt.semillero.entidad.Comic;


/**
 * 
 * @author JOSECARO
 * 
 * clase para controlar el precio con iva
 *
 */

public class PrecioConIva implements IPrecioIVA{
	
	private double iva;
	private double precio;
	
	
	public double getIva() {
		return iva;
	}
	public void setIva(double iva) {
		this.iva = iva;
	}
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	
	
	
	
	@Override
	public double calcularPrecioConIva(String nombre, double precio) {
		
	
		
		if(nombre.equals("AVENTURAS") || nombre.equals("HISTORICO") || nombre.equals("FANTASTICO")) {
			iva = precio*0.05;
			return  precio + iva;
		} else if (nombre.equals("BELICO") || nombre.equals("CIENCIA_FICCION") || nombre.equals("HORROR")) {
			iva = precio*0.16;
			return precio + iva;
			
		}
		iva = precio*0.10;	
		return  precio+iva;
			
		
	}
	
	

}
