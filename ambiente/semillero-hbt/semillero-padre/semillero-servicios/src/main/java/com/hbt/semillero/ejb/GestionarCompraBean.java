package com.hbt.semillero.ejb;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;

import com.hbt.semillero.dto.ClienteDTO;
import com.hbt.semillero.dto.CompraDTO;
import com.hbt.semillero.entidad.Clientes;
import com.hbt.semillero.entidad.Compra;
import com.hbt.semillero.entidad.PKcompra;
import com.hbt.semillero.exception.ManejoExcepciones;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class GestionarCompraBean implements IGestionarCompraLocal{
	
	final static Logger logger = Logger.getLogger(GestionarPersonajeBean.class);
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void crearCompra(CompraDTO compraNuevo) throws ManejoExcepciones {
		// TODO Auto-generated method stub
		logger.debug("Aqui inicia el metodo CrearCompra");
		
		try {
			// Entidad nueva
			Compra compra = convertirCompraDTOToEntidad(compraNuevo);
			// Se almacena la informacion y se maneja la enidad comic
			entityManager.persist(compra);
			
		} catch (Exception e) {
			

			logger.error("Error al crear el cliente... " + e);
			throw new ManejoExcepciones("COD-0005", "Error al ejecutar el metodo crear compra", e);
		}
		
		
		
		logger.debug("Aqui finaliza el metodo CrearCompra");
		
	}

	@Override
	public List<CompraDTO> consultarCompra() throws ManejoExcepciones {
		logger.debug("Aqui inicia el metodo consulta cliente");

		List<CompraDTO> resultadosCompraDTO = new ArrayList<CompraDTO>();
		
		//manejo de la excepcion
		try {
			List<Compra> resultados = entityManager.createQuery("select c from Compra c").getResultList();
			System.out.println(resultados);

			for (Compra c:resultados) {
				resultadosCompraDTO.add(convertirEntidadToCompraTO(c));
			}
			
			
		} catch (Exception e) {
			
			logger.error("Error al consultar los el Cliente... " + e);
			throw new ManejoExcepciones("COD-0004", "Error al ejecutar el metodo consultarcliente", e);
		}
		
		return resultadosCompraDTO;
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
		
		compra.setNombre(compraDTO.getNombre());
		compra.setPk(new PKcompra(compraDTO.getIdCliente(),compraDTO.getIdComic(),compraDTO.getFechaCompra()));

		return compra;
	}
	
	
	private CompraDTO convertirEntidadToCompraTO(Compra compra) {
		CompraDTO compraDTO = new CompraDTO();
	
		compraDTO.setNombre(compra.getNombre());
		compraDTO.setIdCliente(compra.getPk().getIdClinete());
		compraDTO.setFechaCompra(compra.getPk().getFecha());
		compraDTO.setIdComic(compra.getPk().getIdComic());
		
		return compraDTO;
	}

}
