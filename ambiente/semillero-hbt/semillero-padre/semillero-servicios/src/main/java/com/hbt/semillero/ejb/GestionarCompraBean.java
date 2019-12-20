package com.hbt.semillero.ejb;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;

import com.hbt.semillero.dto.CompraDTO;
import com.hbt.semillero.dto.PersonaClienteDTO;
import com.hbt.semillero.entidad.Compra;
import com.hbt.semillero.entidad.PersonaCliente;
import com.hbt.semillero.exception.ManejoExcepciones;


@TransactionManagement(TransactionManagementType.CONTAINER)
public class GestionarCompraBean implements IGestionarCompraLocal{
	
	final static Logger logger = Logger.getLogger(GestionarPersonajeBean.class);
	
	
	@PersistenceContext
	private EntityManager entityManager;
	

	
	/**
	 * 
	 * @throws ManejoExcepciones 
	 * @see com.hbt.semillero.ejb.IGestionarPersonajeLocal#crearComic(com.hbt.semillero.dto.PersonajeDTO)
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	@Override
	public void crearCompra(CompraDTO compraNuevo) throws ManejoExcepciones {
		
         logger.debug("Aqui inicia el metodo CrearPersonaje");
		
		//manejo de la excepcion
		try {
			// Entidad nueva
			Compra compra = convertirCompraDTOToEntidad(compraNuevo);
			// Se almacena la informacion y se maneja la enidad comic
			entityManager.persist(compra);
			
		} catch (Exception e) {
			

			logger.error("Error al consultar los la Persona cliente... " + e);
			throw new ManejoExcepciones("COD-0005", "Error al ejecutar el metodo crear la compra", e);
		}
		
	
		
		logger.debug("Aqui finaliza el metodo compra");
		
	}

	@Override
	public List<CompraDTO> consultarCompra() throws ManejoExcepciones  {
		
		logger.debug("Aqui inicia el metodo EliminarPersonaje");

		List<CompraDTO> resultadosPersonaDTO = new ArrayList<CompraDTO>();
		
		//manejo de la excepcion
		try {
			List<Compra> resultados = entityManager.createQuery("select c from Compra c").getResultList();
			

			for (Compra persona:resultados) {
				
				resultadosPersonaDTO.add(convertirEntidadToCompraDTO(persona));
			}
			
			
		} catch (Exception e) {
			
			logger.error("Error al consultar los el comic... " + e);
			throw new ManejoExcepciones("COD-0004", "Error al ejecutar el metodo consultar PersonaCliente", e);
		}
		
		return resultadosPersonaDTO;
	}

	@Override
	public List<CompraDTO> consultarCompra(Long idcompra) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void modificarCompra(Long id, String nombre, CompraDTO compraNuevo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void eliminarCompra(Long idPersona) {
		// TODO Auto-generated method stub
		
	}
	
	
	private Compra convertirCompraDTOToEntidad(CompraDTO compraDTO) {
		Compra compra = new Compra();
		
		compra.setIdCompra(compraDTO.getIdCompra());
		compra.setNombre(compraDTO.getNombre());
		compra.setFechaCompra(compraDTO.getFechaCompra());
		
		
		return compra;
	}
	
	private CompraDTO convertirEntidadToCompraDTO(Compra compra) {
	CompraDTO compraDTO = new CompraDTO();
		if(compra.getIdCompra()!=null) {
			compraDTO.setIdCompra(compra.getIdCompra());
		}
		compraDTO.setNombre(compra.getNombre());
		compraDTO.setFechaCompra(compra.getFechaCompra());
	
		
		return compraDTO;
	}

}
