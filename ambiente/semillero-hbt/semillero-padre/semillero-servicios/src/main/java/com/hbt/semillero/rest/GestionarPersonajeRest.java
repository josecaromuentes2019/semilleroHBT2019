package com.hbt.semillero.rest;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import com.hbt.semillero.dto.ComicDTO;
import com.hbt.semillero.dto.PersonajeDTO;
import com.hbt.semillero.dto.ResultadoDTO;
import com.hbt.semillero.ejb.IGestonarPersonajeLocal;
import com.hbt.semillero.entidad.Comic;
import com.hbt.semillero.entidad.Personaje;
import com.hbt.semillero.exception.ManejoExcepciones;

/**
 * <b>Descripción:<b> Clase que determina el servicio rest que permite gestionar
 * un comic
 * 
 * @author JOSECARO
 * @version
 */
@Path("/GestionarPersonaje")
public class GestionarPersonajeRest {

	@EJB
	private IGestonarPersonajeLocal gestionarPersonajeEJB;
	
	final static Logger logger = Logger.getLogger(GestionarPersonajeRest.class);
	
	/**
	 * Atributo em que se usa para interacturar con el contexto de persistencia.
	 */
	@PersistenceContext
	private EntityManager em;

	
	/**
	 * URI http://localhost:8085/semillero-servicios/rest/GestionarPersonaje/consultarPersonaje
	 * @return
	 */
	@GET
	@Path("/consultarPersonaje")
	@Produces(MediaType.APPLICATION_JSON)
	public  Response consultarPersonaje(){
		List<PersonajeDTO> personajeDTO= null;
		//manejo de la excepcion
		try {
			personajeDTO = gestionarPersonajeEJB.consultarPersonaje();
			//ResultadoDTO resultadoDTO = new ResultadoDTO(Boolean.TRUE, "Personajes Listados exitosamente");
			if(personajeDTO.size()!=0) {
				return Response.status(Response.Status.OK).entity(personajeDTO).build();
			}else {
				return Response.status(Response.Status.OK).entity("No hay datos en personaje").build();
			}
			
		} catch (ManejoExcepciones e) {
			
			logger.error("Se capturo la excepcion "+e.getCodigo()+ " mensaje: "+e.getMensaje());
			return Response.status(Response.Status.BAD_REQUEST)
					   .entity("Se presento un fallo al consultar el Personajes")
					   .build();
		}
		
		
	};
	
	/**
	 * URI http://localhost:8085/semillero-servicios/rest/GestionarPersonaje/consultarPersonajeId?idComic=1
	 * donsde idComic es llave foranea
	 * @param idComic
	 * @return
	 */
	
	@GET
	@Path("/consultarPersonajeId")
	@Produces(MediaType.APPLICATION_JSON)
	public Response  consultarPersonajes(@QueryParam("idComic") Long idComic){
		List<PersonajeDTO> personajeDTO= null;
		//manejo de la excepcion
		try {
			personajeDTO = gestionarPersonajeEJB.consultarPersonajes(idComic);
			ResultadoDTO resultadoDTO = new ResultadoDTO(Boolean.TRUE, "Personaje Listado exitosamente");
			if(personajeDTO.size()!=0) {
				return Response.status(Response.Status.OK).entity(personajeDTO).build();
			}else {
				return Response.status(Response.Status.OK).entity("El id no existe").build();
			}
			
		} catch (ManejoExcepciones e) {
			logger.error("Se capturo la excepcion "+e.getCodigo()+ " mensaje: "+e.getMensaje());
			return Response.status(Response.Status.BAD_REQUEST)
					   .entity("Se presento un fallo al consultar el Personaje")
					   .build();
		}
		
		//return null;
		
	};
	/**
	 * 
	 * Metodo encargado de crear un personaje y persistirlo
	 * 
	 * @author JOSECARO
	 * 
	 * @param personajeNuevo informacion nueva a crear
	 * 
	 * URI http://localhost:8085/semillero-servicios/rest/GestionarPersonaje/crearPersonaje
	
	 */
	@POST
	@Path("/crearPersonaje")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
		public Response crearPersonaje(PersonajeDTO personajeNuevo) {
		
		//manejo de la excepcion
		try {
			gestionarPersonajeEJB.crearPersonaje(personajeNuevo);
			ResultadoDTO resultadoDTO = new ResultadoDTO(Boolean.TRUE, "Personaje Creado exitosamente");
			return Response.status(Response.Status.OK).entity(resultadoDTO).build();
		} catch (ManejoExcepciones e) {
			
			logger.error("Se capturo la excepcion "+e.getCodigo()+ " mensaje: "+e.getMensaje());
			return Response.status(Response.Status.BAD_REQUEST)
					   .entity("Se presento un fallo en la Creacion del Personaje")
					   .build();
		}
		
	};
	
	

	@PUT
	@Path("/modificar")
	@Produces(MediaType.APPLICATION_JSON)
	public Response modificarComic(@QueryParam("idPersonaje") Long idPersonaje, @QueryParam("nombre") String nombre) {
		
		//manejo de la excepcion
		try {
			gestionarPersonajeEJB.modificarPersonaje(idPersonaje, nombre, null);
			ResultadoDTO resultadoDTO = new ResultadoDTO(Boolean.TRUE, "Personaje actualizado exitosamente");
			return Response.status(Response.Status.OK).entity(resultadoDTO).build();	
		} catch (ManejoExcepciones e) {
			logger.error("Se capturo la excepcion "+e.getCodigo()+ " mensaje: "+e.getMensaje());
			return Response.status(Response.Status.BAD_REQUEST)
					   .entity("Se presento un fallo en la actualizacion del Personaje")
					   .build();
		}
	}
	
	
	@DELETE
	@Path("/eliminar")
	@Produces(MediaType.APPLICATION_JSON)
	public Response eliminarComic(@QueryParam("idPersonaje") Long idPersonaje) {
		
			
			//manejo de la excepcion
			try {
				gestionarPersonajeEJB.eliminarPersonaje(idPersonaje);
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