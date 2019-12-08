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

@Path("/GestionarPersonaje")
public class GestionarPersonajesRest {

	@EJB
	private GestionarPersonajeBean  gestionarPersonajeBean;
	
	@POST
	@Path("/crear")
	public void crearPersonaje(PersonajeDTO personejeDTO) {
		
		gestionarPersonajeBean.crearPersonaje(personejeDTO);
	}
	
	@GET
	@Path("/consultarPersonaje")
	public List<PersonajeDTO> consultarPersonaje(@QueryParam("idComic") Long idComic){
		return gestionarPersonajeBean.consultarPersonaje(idComic);
	}
	
	@GET
	@Path("/consultarPersonaje")
	public List<PersonajeDTO>  consultarPersonaje(){
		return gestionarPersonajeBean.consultarPersonaje();
	}
}
