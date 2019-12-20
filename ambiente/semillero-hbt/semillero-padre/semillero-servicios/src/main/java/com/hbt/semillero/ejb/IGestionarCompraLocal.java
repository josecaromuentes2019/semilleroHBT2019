package com.hbt.semillero.ejb;

import java.util.List;

import com.hbt.semillero.dto.CompraDTO;
import com.hbt.semillero.exception.ManejoExcepciones;

public interface IGestionarCompraLocal {
	
	public void crearCompra(CompraDTO compraNuevo) throws ManejoExcepciones;
	
	public  List<CompraDTO> consultarCompra() throws ManejoExcepciones ;
	
	public List<CompraDTO>  consultarCompra(Long idcompra) ;
	
	public void modificarCompra(Long id, String nombre, CompraDTO compraNuevo);
	
	public void eliminarCompra(Long idPersona);

}
