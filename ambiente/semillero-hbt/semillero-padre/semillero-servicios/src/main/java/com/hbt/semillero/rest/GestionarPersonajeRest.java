package com.hbt.semillero.rest;

import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;

import com.hbt.semillero.dto.ComicDTO;
import com.hbt.semillero.dto.PersonajeDTO;
import com.hbt.semillero.dto.ResultadoDTO;
import com.hbt.semillero.ejb.IGestonarPersonajeLocal;
import com.hbt.semillero.exception.ManejoExcepciones;

/**
 * <b>Descripción:<b> Clase que determina el servicio rest que permite gestionar
 * un comic
 * 
 * @author ccastano
 * @version
 */
@Path("/GestionarPersonaje")
public class GestionarPersonajeRest {

	@EJB
	private IGestonarPersonajeLocal gestionarPersonajeEJB;
	
	final static Logger logger = Logger.getLogger(GestionarPersonajeRest.class);

	
	@GET
	@Path("/consultarPersonaje")
	@Produces(MediaType.APPLICATION_JSON)
	public  List<PersonajeDTO> consultarPersonaje(){
		try {
			return gestionarPersonajeEJB.consultarPersonaje();
		} catch (ManejoExcepciones e) {
			
			logger.error("Se capturo la excepcion "+e.getCodigo()+ " mensaje: "+e.getMensaje());
		}
		return null;
		
	};
	
	@GET
	@Path("/consultarPersonajeId")
	@Produces(MediaType.APPLICATION_JSON)
	public List<PersonajeDTO>  consultarPersonajes(@QueryParam("idComic") Long idComic){
		try {
			return gestionarPersonajeEJB.consultarPersonajes(idComic);
		} catch (ManejoExcepciones e) {
			logger.error("Se capturo la excepcion "+e.getCodigo()+ " mensaje: "+e.getMensaje());
		}
		
		return null;
		
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
	@Path("/crearPersonaje")
		public void crearPersonaje(PersonajeDTO personajeNuevo) {
		try {
			gestionarPersonajeEJB.crearPersonaje(personajeNuevo);
		} catch (ManejoExcepciones e) {
			
			logger.error("Se capturo la excepcion "+e.getCodigo()+ " mensaje: "+e.getMensaje());
		}
		
	};

	
	


	
}