package com.hbt.semillero.ejb;

import java.util.Collection;
import java.util.List;

import com.hbt.semillero.dto.ClienteDTO;
import com.hbt.semillero.exception.ManejoExcepciones;

public interface IGestionarClienteLocal {
	
public void crearCliente(ClienteDTO clienteNuevo) throws ManejoExcepciones;
	
	public  List<ClienteDTO> consultarCliente() throws ManejoExcepciones ;
	
	public List<ClienteDTO>  consultarCliente(Long idCliente) ;
	
	public void modificarCliente(Long id, String nombre, ClienteDTO clienteNuevo);
	
	public Collection<ClienteDTO>  consultarCompra(Long idCliente);
	
	public void eliminarCliente(Long idCliente);

}
