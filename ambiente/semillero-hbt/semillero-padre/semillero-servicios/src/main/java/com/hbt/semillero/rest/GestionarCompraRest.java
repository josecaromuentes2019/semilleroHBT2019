package com.hbt.semillero.rest;

import java.util.List;

import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import com.hbt.semillero.dto.CompraDTO;
import com.hbt.semillero.dto.PersonaClienteDTO;
import com.hbt.semillero.ejb.IGestionarCompraLocal;
import com.hbt.semillero.ejb.IPersonaClienteLocal;
import com.hbt.semillero.exception.ManejoExcepciones;

public class GestionarCompraRest {
	
	@EJB
	private IGestionarCompraLocal gestionarPersonaEJB;
	
	final static Logger logger = Logger.getLogger(GestionarPersonajeRest.class);
	
	@PersistenceContext
	private EntityManager em;
	
	
	@GET
	@Path("/consultarcompra")
	@Produces(MediaType.APPLICATION_JSON)
	public  Response consultarPersona(){
		List<CompraDTO> compraDTO= null;
		//manejo de la excepcion
		try {
			compraDTO = gestionarPersonaEJB.consultarCompra();
			
			if(compraDTO.size()!=0) {
				return Response.status(Response.Status.OK).entity(compraDTO).build();
			}else {
				return Response.status(Response.Status.OK).entity("No hay datos en la compra").build();
			}
			
		} catch (ManejoExcepciones e) {
			
			logger.error("Se capturo la excepcion "+e.getCodigo()+ " mensaje: "+e.getMensaje());
			return Response.status(Response.Status.BAD_REQUEST)
					   .entity("Se presento un fallo al consultar la compra")
					   .build();
		}
		
		
	};
	

}
