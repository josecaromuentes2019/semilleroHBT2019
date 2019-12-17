/**
 * GestionarComicRest.java
 */
package com.hbt.semillero.rest;

import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;

import com.hbt.semillero.dto.ComicDTO;
import com.hbt.semillero.dto.ResultadoDTO;
import com.hbt.semillero.ejb.GestionarComicBean;
import com.hbt.semillero.ejb.IGestionarComicLocal;
import com.hbt.semillero.exception.ManejoExcepciones;

/**
 * <b>Descripción:<b> Clase que determina el servicio rest que permite gestionar
 * un comic
 * 
 * @author ccastano
 * @version
 */
@Path("/GestionarComic")
public class GestionarComicRest {

	/**
	 * Atriburo que permite gestionar un comic
	 */
	@EJB
	private IGestionarComicLocal gestionarComicEJB;
	
	
	final static Logger logger = Logger.getLogger(GestionarComicRest.class);

	/**
	 * 
	 * Metodo encargado de traer la informacion de un comic determiando
	 * http://localhost:8085/semillero-servicios/rest/GestionarComic/saludo
	 * 
	 * @param idComic
	 * @return
	 */
	@GET
	@Path("/saludo")
	@Produces(MediaType.APPLICATION_JSON)
	public String primerRest() {
		return "Prueba inicial servicios rest en el semillero java hbt";
	}

	/**
	 * 
	 * Metodo encargado de traer la informacion de un comic determiando
	 * http://localhost:8085/semillero-servicios/rest/GestionarComic/consultarComics
	 * 
	 * @param idComic
	 * @return
	 */
	@GET
	@Path("/consultarComics")
	@Produces(MediaType.APPLICATION_JSON)
	public List<ComicDTO> consultarComic() {
		
		//manejo de la excepcion
		try {
			return gestionarComicEJB.consultarComics();
		} catch (ManejoExcepciones e) {
			
			logger.error("Se capturo la excepcion "+e.getCodigo()+ " mensaje: "+e.getMensaje());
		}
			
		return null;
	}

	/**
	 * 
	 * Metodo encargado de traer la informacion de un comic determiando
	 * http://localhost:8085/semillero-servicios/rest/GestionarComic/consultarComic?idComic=1
	 * 
	 * @param idComic
	 * @return
	 */
	@GET
	@Path("/consultarComic")
	@Produces(MediaType.APPLICATION_JSON)
	public ComicDTO consultarComic(@QueryParam("idComic") Long idComic) {
		ComicDTO comicDTO = null;
		//manejo de la excepcion
		try {
			if (idComic != null) {
				 comicDTO = gestionarComicEJB.consultarComic(idComic.toString());
				return comicDTO;
			}
			
		} catch (ManejoExcepciones e) {
			logger.error("Se capturo la excepcion "+e.getCodigo()+ " mensaje: "+e.getMensaje());
		}
		
		return comicDTO;
	}

	/**
	 * Crea las personas en sus diferentes roles dentro del sistema.
	 * http://localhost:8085/semillero-servicios/rest/GestionarComic/crear
	 * @param persona
	 * @return
	 */
	@POST
	@Path("/crear")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ResultadoDTO crearComic(ComicDTO comicDTO) {
		gestionarComicEJB.crearComic(comicDTO);
		ResultadoDTO resultadoDTO = new ResultadoDTO(Boolean.TRUE, "Comic creado exitosamente");
		return resultadoDTO;
		
	}

	/**
	 * 
	 * Metodo encargado de modificar el nombre de un comic
	 * http://localhost:8085/semillero-servicios/rest/GestionarComic/modificar?idComic=1&nombre=nuevonombre
	 * @param idComic identificador del comic a buscar
	 * @param nombre nombre nuevo del comic
	 */
	@PUT
	@Path("/modificar")
	@Produces(MediaType.APPLICATION_JSON)
	public void modificarComic(@QueryParam("idComic") Long idComic, @QueryParam("nombre") String nombre) {
		
		//manejo de la excepcion
		try {
			gestionarComicEJB.modificarComic(idComic, nombre, null);
		} catch (ManejoExcepciones e) {
			logger.error("Se capturo la excepcion "+e.getCodigo()+ " mensaje: "+e.getMensaje());
		}
	}

	/**
	 * 
	 * Metodo encargado de eliminar un comic dado el id
	 * 
	 * @param idComic identificador del comic
	 */
	@DELETE
	@Path("/eliminar")
	@Produces(MediaType.APPLICATION_JSON)
	public void eliminarComic(@QueryParam("idComic") Long idComic) {
		if (idComic != null) {
			
			//manejo de la excepcion
			try {
				gestionarComicEJB.consultarComic(idComic.toString());
			} catch (ManejoExcepciones e) {
				
				logger.error("Se capturo la excepcion "+e.getCodigo()+ " mensaje: "+e.getMensaje());
			}

		}
	}
	
	@GET
	@Path("/consultarComicsValor")
	@Produces(MediaType.APPLICATION_JSON)
	public List<ComicDTO> consultarComicsValor() {
		return gestionarComicEJB.consultarComicsValor();

	}
}
