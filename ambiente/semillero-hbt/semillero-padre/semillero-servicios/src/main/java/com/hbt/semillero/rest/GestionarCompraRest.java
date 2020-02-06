package com.hbt.semillero.rest;

import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;


import com.hbt.semillero.dto.CompraDTO;
import com.hbt.semillero.dto.ResultadoDTO;
import com.hbt.semillero.ejb.IGestionarCompraLocal;
import com.hbt.semillero.exception.ManejoExcepciones;

@Path("/GestionarCompra")
public class GestionarCompraRest {
	
	@EJB
	private IGestionarCompraLocal gestionarCompraEJB;
	
	final static Logger logger = Logger.getLogger(GestionarCompraRest.class);
	
	@PersistenceContext
	private EntityManager em;
	
	
	@POST
	@Path("/crearCompra")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
		public Response crearCompra(CompraDTO compraNuevo) {
		
		//manejo de la excepcion
		try {
			gestionarCompraEJB.crearCompra(compraNuevo);
			ResultadoDTO resultadoDTO = new ResultadoDTO(Boolean.TRUE, "Compra Creada exitosamente");
			return Response.status(Response.Status.OK).entity(resultadoDTO).build();
		} catch (ManejoExcepciones e) {
			
			logger.error("Se capturo la excepcion "+e.getCodigo()+ " mensaje: "+e.getMensaje());
			return Response.status(Response.Status.BAD_REQUEST)
					   .entity("Se presento un fallo en la Crear la Compra")
					   .build();
		}
		
	};

}
