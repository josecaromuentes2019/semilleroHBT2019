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

import com.hbt.semillero.dto.ClienteDTO;
import com.hbt.semillero.dto.ComicDTO;
import com.hbt.semillero.dto.PersonajeDTO;
import com.hbt.semillero.dto.ResultadoDTO;
import com.hbt.semillero.ejb.IGestionarClienteLocal;
import com.hbt.semillero.exception.ManejoExcepciones;

@Path("/GestionarClientes")
public class GestionarClienteRest {
	
	@EJB
	private IGestionarClienteLocal gestionarClienteEJB;
	
	final static Logger logger = Logger.getLogger(GestionarPersonajeRest.class);
	
	@PersistenceContext
	private EntityManager em;
	
	
	@POST
	@Path("/crearCliente")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
		public Response crearCliente(ClienteDTO clienteNuevo) {
		
		//manejo de la excepcion
		try {
			gestionarClienteEJB.crearCliente(clienteNuevo);
			ResultadoDTO resultadoDTO = new ResultadoDTO(Boolean.TRUE, "Cliente Creado exitosamente");
			return Response.status(Response.Status.OK).entity(resultadoDTO).build();
		} catch (ManejoExcepciones e) {
			
			logger.error("Se capturo la excepcion "+e.getCodigo()+ " mensaje: "+e.getMensaje());
			return Response.status(Response.Status.BAD_REQUEST)
					   .entity("Se presento un fallo en la Creacion del cliente")
					   .build();
		}
		
	};
	
	
	
	
	
	
	@GET
	@Path("/consultarClientes")
	@Produces(MediaType.APPLICATION_JSON)
	public  Response consultarClientes(){
		List<ClienteDTO> clienteDTO= null;
		//manejo de la excepcion
		try {
			clienteDTO = gestionarClienteEJB.consultarCliente();
			//ResultadoDTO resultadoDTO = new ResultadoDTO(Boolean.TRUE, "Personajes Listados exitosamente");
			if(clienteDTO.size()!=0) {
				return Response.status(Response.Status.OK).entity(clienteDTO).build();
			}else {
				return Response.status(Response.Status.OK).entity("No hay datos en cliente").build();
			}
			
		} catch (ManejoExcepciones e) {
			
			logger.error("Se capturo la excepcion "+e.getCodigo()+ " mensaje: "+e.getMensaje());
			return Response.status(Response.Status.BAD_REQUEST)
					   .entity("Se presento un fallo al consultar el clinte")
					   .build();
		}
		
		
	};
	

}
