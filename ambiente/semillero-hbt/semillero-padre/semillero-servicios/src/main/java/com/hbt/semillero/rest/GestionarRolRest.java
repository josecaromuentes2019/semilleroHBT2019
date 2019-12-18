package com.hbt.semillero.rest;

import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import com.hbt.semillero.dto.PersonajeDTO;
import com.hbt.semillero.dto.ResultadoDTO;
import com.hbt.semillero.dto.RolDTO;
import com.hbt.semillero.ejb.IGestionarRol;
import com.hbt.semillero.ejb.IGestonarPersonajeLocal;
import com.hbt.semillero.exception.ManejoExcepciones;

@Path("/GestionarRol")
public class GestionarRolRest {
	
	@EJB
	private IGestionarRol iGestionarRol;
	
	final static Logger logger = Logger.getLogger(GestionarRolRest.class);

	
	@GET
	@Path("/consultarRol")
	@Produces(MediaType.APPLICATION_JSON)
	public  Response consultarRol(){
		
		List<RolDTO>listaRoles = null;
		try {
			listaRoles =  iGestionarRol.consultarRolPersonaje();
			return Response.status(Response.Status.OK).entity(listaRoles).build();
		}  catch (ManejoExcepciones e) {
			
			logger.error("Se capturo la excepcion "+e.getCodigo()+ " mensaje: "+e.getMensaje());
			return Response.status(Response.Status.OK).entity("no se pudo hacer la consulta").build();
		}
		
		
	};
	
	@GET
	@Path("/consultarRolId")
	@Produces(MediaType.APPLICATION_JSON)
	public  Response consultarRoles(@QueryParam("idRol") String idRol){
		
		RolDTO rolporID = null;
		try {
			rolporID= iGestionarRol.consultarRolPersonaje(idRol);
			
			return Response.status(Response.Status.OK).entity(rolporID).build();
		} catch (ManejoExcepciones e) {
			logger.error("Se capturo la excepcion "+e.getCodigo()+ " mensaje: "+e.getMensaje());
			return Response.status(Response.Status.OK).entity("no se pudo hacer la consulta").build();
		}
		
	};
	
	/**
	 * 
	 * Metodo encargado de crear un personaje y persistirlo
	 * 
	 * @author ccastano
	 * 
	 * @param personajeNuevo informacion nueva a crear
	 */
	

	
	@POST
	@Path("/crearRol")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
		public Response crearRol(RolDTO tolDtoNuevo) {
		try {
			
			iGestionarRol.crearRol(tolDtoNuevo);
			ResultadoDTO resultadoDTO = new ResultadoDTO(Boolean.TRUE, "ROL Creado exitosamente");
			return Response.status(Response.Status.OK).entity(resultadoDTO).build();
		} catch (ManejoExcepciones e) {
			
			logger.error("Se capturo la excepcion "+e.getCodigo()+ " mensaje: "+e.getMensaje());
			return Response.status(Response.Status.OK).entity("No se pudo crear rol").build();
		}
		
	};
	
	
	@DELETE
	@Path("/eliminar")
	@Produces(MediaType.APPLICATION_JSON)
	public Response eliminarRol(@QueryParam("idRol") Long idPersonaje) {
		
			
			//manejo de la excepcion
			try {
				iGestionarRol.eliminarRolPersonaje(idPersonaje);
				ResultadoDTO resultadoDTO = new ResultadoDTO(Boolean.TRUE, "Personaje eliminado exitosamente");
				
				return Response.status(Response.Status.OK)
						   .entity(resultadoDTO)
						   .build();
			} catch (ManejoExcepciones e) {
				
				logger.error("Se capturo la excepcion "+e.getCodigo()+ " mensaje: "+e.getMensaje());
				return Response.status(Response.Status.BAD_REQUEST)
						   .entity("No se pudo Eliminar el Personaje")
						   .build();
			}

			
	}
	
	
	

}
