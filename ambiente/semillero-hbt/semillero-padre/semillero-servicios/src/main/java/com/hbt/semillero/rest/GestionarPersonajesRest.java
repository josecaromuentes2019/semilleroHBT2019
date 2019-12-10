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

import org.hibernate.service.spi.InjectService;

import com.hbt.semillero.dto.PersonajeDTO;
import com.hbt.semillero.ejb.GestionarComicBean;
import com.hbt.semillero.ejb.GestionarPersonajeBean;
import com.hbt.semillero.ejb.IGestionarPersonajeLocal;

@Path("/GestionarPersonaje")
public class GestionarPersonajesRest {


	@EJB
	private IGestionarPersonajeLocal gestionarPersonajeEJB;

	
	@GET
	@Path("/consultarPersonaje")
	@Produces(MediaType.APPLICATION_JSON)
	public  List<PersonajeDTO> consultarPersonaje(){
		return gestionarPersonajeEJB.consultarPersonaje();
		
	};
	
	@GET
	@Path("/consultarPersonajeId")
	@Produces(MediaType.APPLICATION_JSON)
	public List<PersonajeDTO>  consultarPersonajes(@QueryParam("idComic") Long idComic){
		return gestionarPersonajeEJB.consultarPersonajes(idComic);
		
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
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public void crearPersonaje(PersonajeDTO personajeNuevo) {
		gestionarPersonajeEJB.crearPersonaje(personajeNuevo);
		
	};


	
}
