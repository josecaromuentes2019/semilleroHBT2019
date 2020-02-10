package com.hbt.semillero.ejb;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;

import com.hbt.semillero.dto.ClienteDTO;
import com.hbt.semillero.dto.PersonajeDTO;
import com.hbt.semillero.entidad.Clientes;
import com.hbt.semillero.entidad.Comic;
import com.hbt.semillero.entidad.Personaje;
import com.hbt.semillero.exception.ManejoExcepciones;



@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class GestionarClienteBean implements IGestionarClienteLocal{
	
	
	final static Logger logger = Logger.getLogger(GestionarPersonajeBean.class);
	
	@PersistenceContext
	private EntityManager entityManager;
	

	@Override
	public void crearCliente(ClienteDTO clienteNuevo) throws ManejoExcepciones {
		
			logger.debug("Aqui inicia el metodo CrearCliente");
		
		//manejo de la excepcion
		try {
			// Entidad nueva
			Clientes cliente = convertirClienteDTOToEntidad(clienteNuevo);
			// Se almacena la informacion y se maneja la enidad comic
			entityManager.persist(cliente);
			
		} catch (Exception e) {
			

			logger.error("Error al crear el cliente... " + e);
			throw new ManejoExcepciones("COD-0005", "Error al ejecutar el metodo crear cliente", e);
		}
		
	
		
		logger.debug("Aqui finaliza el metodo Crearcliente");
		
	}

	@Override
	public List<ClienteDTO> consultarCliente() throws ManejoExcepciones {
		logger.debug("Aqui inicia el metodo consulta cliente");

		List<ClienteDTO> resultadosClienteDTO = new ArrayList<ClienteDTO>();
		
		//manejo de la excepcion
		try {
			List<Clientes> resultados = entityManager.createQuery("select c from Clientes c").getResultList();
			

			for (Clientes cliente:resultados) {
				resultadosClienteDTO.add(convertirEntidadToClinteDTO(cliente));
			}
			
			
		} catch (Exception e) {
			
			logger.error("Error al consultar los el Cliente... " + e);
			throw new ManejoExcepciones("COD-0004", "Error al ejecutar el metodo consultarcliente", e);
		}
		
		return resultadosClienteDTO;
	}
	
	@Override
	public Collection<ClienteDTO>  consultarCompra(Long idCliente){
		
		return null;
	}

	@Override
	public List<ClienteDTO> consultarCliente(Long idCliente) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void modificarCliente(Long id, String nombre, ClienteDTO clienteNuevo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void eliminarCliente(Long idCliente) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	private Clientes convertirClienteDTOToEntidad(ClienteDTO clienteDTO) {
		Clientes cliente = new Clientes();
		
		
		//cliente.setIdCliente(clienteDTO.getIdCliente());
		cliente.setNombre(clienteDTO.getNombre());
		cliente.setIdentidad(clienteDTO.getIdentidad());
		cliente.setFeNacimiento(clienteDTO.getFeNacimiento());
		
		
		
		return cliente;
	}
	
	private ClienteDTO convertirEntidadToClinteDTO(Clientes cliente) {
		ClienteDTO clienteDTO = new ClienteDTO();
		if(cliente.getIdCliente()!=null) {
			clienteDTO.setIdCliente(cliente.getIdCliente());
		}
		clienteDTO.setNombre(cliente.getNombre());
		clienteDTO.setIdentidad(cliente.getIdentidad());
		clienteDTO.setFeNacimiento(cliente.getFeNacimiento());
	
		
		return clienteDTO;
	}

	

}
