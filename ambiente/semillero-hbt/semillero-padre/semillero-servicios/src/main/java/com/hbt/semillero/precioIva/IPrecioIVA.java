package com.hbt.semillero.precioIva;

/**
 * @autor jose caro
 */

import javax.ejb.Local;

@FunctionalInterface
@Local
public interface IPrecioIVA {
	
	public double calcularPrecioConIva(String nombre, double precio);

}
