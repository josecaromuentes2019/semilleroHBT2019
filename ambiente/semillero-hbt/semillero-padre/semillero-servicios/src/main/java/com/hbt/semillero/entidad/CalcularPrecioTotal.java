package com.hbt.semillero.entidad;

import javax.ejb.Local;

import com.hbt.semillero.dto.ComicDTO;

@FunctionalInterface
@Local
public interface CalcularPrecioTotal {
	
	public double precioTotal(String nombre, double precio);
	
}
