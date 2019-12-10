package com.hbt.semillero.rest;

import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.hbt.semillero.dto.PersonajeDTO;
import com.hbt.semillero.dto.RolDTO;
import com.hbt.semillero.ejb.GestionarRolBean;

@Path("/GestionarRol")
public class GestionarRolRest {
	
	@EJB
	private GestionarRolBean  gestionarRolBean;
	
	@POST
	@Path("/crear")
	@Produces(MediaType.APPLICATION_JSON)
	public void crearRol(RolDTO rolDTO) {
		
		gestionarRolBean.crearRol(rolDTO);
	}
	
	@GET
	@Path("/consultarRol")
	@Produces(MediaType.APPLICATION_JSON)
	public List<RolDTO> consultarPersonaje(@QueryParam("id") Long id){
		return gestionarRolBean.consultarRol(id);
	}
	
	@GET
	@Path("/consultarRol")
	@Produces(MediaType.APPLICATION_JSON)
	public List<RolDTO>  consultarRol(){
		return gestionarRolBean.consultarRol();
	}

}
