package com.hbt.semillero.rest;

import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;

import com.hbt.semillero.dto.PersonajeDTO;
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
	public  List<RolDTO> consultarRol(){
		try {
			return iGestionarRol.consultarRolPersonaje();
		}  catch (ManejoExcepciones e) {
			
			logger.error("Se capturo la excepcion "+e.getCodigo()+ " mensaje: "+e.getMensaje());
		}
		
		return null;
	};
	
	@GET
	@Path("/consultarPersonajeId")
	@Produces(MediaType.APPLICATION_JSON)
	public  RolDTO consultarRoles(@QueryParam("idRol") String idRol){
		try {
			return iGestionarRol.consultarRolPersonaje(idRol);
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
	
	/*@POST
	@Path("/crearPersonaje")
		public void crearPersonaje(RolDTO rolNuevo) {
		iGestionarRol.crearRolPersonaje(rolNuevo);
		
	};*/
	
	@POST
	@Path("/crearRol")
		public void crearRol(RolDTO tolDtoNuevo) {
		try {
			iGestionarRol.crearRolPersonaje(tolDtoNuevo);
		} catch (ManejoExcepciones e) {
			
			logger.error("Se capturo la excepcion "+e.getCodigo()+ " mensaje: "+e.getMensaje());
		}
		
	};
	
	/*@POST
	@Path("/crearPersonaje")
		public void crearPersonaje(PersonajeDTO personajeNuevo) {
		gestionarPersonajeEJB.crearPersonaje(personajeNuevo);
		
	};

	*/
	

}
