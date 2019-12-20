package com.hbt.semillero.rest;

import java.util.List;

import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import com.hbt.semillero.dto.PersonaClienteDTO;
import com.hbt.semillero.dto.PersonajeDTO;
import com.hbt.semillero.dto.ResultadoDTO;
import com.hbt.semillero.ejb.IGestonarPersonajeLocal;
import com.hbt.semillero.ejb.IPersonaClienteLocal;
import com.hbt.semillero.exception.ManejoExcepciones;

@Path("/GestionarPersonaCliente")
public class GestionarPersonaRest {
	
	@EJB
	private IPersonaClienteLocal gestionarPersonaEJB;
	
	final static Logger logger = Logger.getLogger(GestionarPersonajeRest.class);
	
	@PersistenceContext
	private EntityManager em;
	
	
	
	@GET
	@Path("/consultarPersonaCliente")
	@Produces(MediaType.APPLICATION_JSON)
	public  Response consultarPersona(){
		List<PersonaClienteDTO> personaDTO= null;
		//manejo de la excepcion
		try {
			personaDTO = gestionarPersonaEJB.consultarPersona();
			
			if(personaDTO.size()!=0) {
				return Response.status(Response.Status.OK).entity(personaDTO).build();
			}else {
				return Response.status(Response.Status.OK).entity("No hay datos en persona Cliente").build();
			}
			
		} catch (ManejoExcepciones e) {
			
			logger.error("Se capturo la excepcion "+e.getCodigo()+ " mensaje: "+e.getMensaje());
			return Response.status(Response.Status.BAD_REQUEST)
					   .entity("Se presento un fallo al consultar el Personajes  cliente")
					   .build();
		}
		
		
	};
	
	
	@POST
	@Path("/crearPersonacliente")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
		public Response crearPersonaje(PersonaClienteDTO personaNuevo) {
		
		//manejo de la excepcion
		try {
			gestionarPersonaEJB.crearPersona(personaNuevo);
			ResultadoDTO resultadoDTO = new ResultadoDTO(Boolean.TRUE, "Personaje cliente Creado exitosamente");
			return Response.status(Response.Status.OK).entity(resultadoDTO).build();
		} catch (ManejoExcepciones e) {
			
			logger.error("Se capturo la excepcion "+e.getCodigo()+ " mensaje: "+e.getMensaje());
			return Response.status(Response.Status.BAD_REQUEST)
					   .entity("Se presento un fallo en la Creacion del Personaje cliente")
					   .build();
		}
		
	};

}
