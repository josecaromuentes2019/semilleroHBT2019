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
import javax.ws.rs.core.Response;

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
 * @author JOSECARO
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
	public Response consultarComic() {
		List<ComicDTO> consultaComicDTO = null;
		//manejo de la excepcion
		try {
			consultaComicDTO = gestionarComicEJB.consultarComics();
			if(consultaComicDTO.size()!=0) {
			ResultadoDTO resultadoDTO = new ResultadoDTO(Boolean.TRUE, "Listado todos los Comic exitosamente");
			return Response.status(Response.Status.CREATED).entity(consultaComicDTO).build();	
			}else {
				return Response.status(Response.Status.CREATED).entity("No se pudo listar los Comic").build();
			}
			
		} catch (ManejoExcepciones e) {
			
			logger.error("Se capturo la excepcion "+e.getCodigo()+ " mensaje: "+e.getMensaje());
			return Response.status(Response.Status.BAD_REQUEST)
					   .entity("Se presento un fallo en el listado de los Comics")
					   .build();
			
		}
		
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
	public Response consultarComic(@QueryParam("idComic") Long idComic) {
		ComicDTO comicDTO = null;
		//manejo de la excepcion
		try {
			if (idComic != null) {
				 comicDTO = gestionarComicEJB.consultarComic(idComic.toString());
			}
				 //ResultadoDTO resultadoDTO = new ResultadoDTO(Boolean.TRUE, "Comic Listado exitosamente");
				 return Response.status(Response.Status.CREATED).entity(comicDTO).build();
				//return comicDTO;
			
			
		} catch (ManejoExcepciones e) {
			logger.error("Se capturo la excepcion "+e.getCodigo()+ " mensaje: "+e.getMensaje());
			return Response.status(Response.Status.BAD_REQUEST)
					   .entity("Se presento un fallo en el listado del Comic")
					   .build();
		}
		
		//return comicDTO;
	}

	/**
	 * Crea las personas en sus diferentes roles dentro del sistema.
	 * http://localhost:8085/semillero-servicios/rest/GestionarComic/crear
	 * @param persona
	 * @return
	 * 
	 * 
	 */
	@POST
	@Path("/crear")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response crearComic(ComicDTO comicDTO) {
		
		try {
			
			gestionarComicEJB.crearComic(comicDTO);
			ResultadoDTO resultadoDTO = new ResultadoDTO(Boolean.TRUE, "Comic creado exitosamente");
			return Response.status(Response.Status.CREATED).entity(resultadoDTO).build();	
		} catch (Exception e) {	
			return Response.status(Response.Status.BAD_REQUEST)
						   .entity("Se presento un fallo en la invocacion del servicio")
						   .build();
		}
		
	}
	
	
	
	
	
	@PUT
	@Path("/modificar")
	@Produces(MediaType.APPLICATION_JSON)
	public Response modificarComic(@QueryParam("idComic") Long idComic, @QueryParam("nombre") String nombre) {
		
		//manejo de la excepcion
		try {
			gestionarComicEJB.modificarComic(idComic, nombre, null);
			ResultadoDTO resultadoDTO = new ResultadoDTO(Boolean.TRUE, "Comic actualizado exitosamente");
			return Response.status(Response.Status.CREATED).entity(resultadoDTO).build();	
		} catch (ManejoExcepciones e) {
			logger.error("Se capturo la excepcion "+e.getCodigo()+ " mensaje: "+e.getMensaje());
			return Response.status(Response.Status.BAD_REQUEST)
					   .entity("Se presento un fallo en la actualizacion del comic")
					   .build();
		}
	}

	/**
	 * 
	 * Metodo encargado de eliminar un comic dado el id
	 * 
	 * @param idComic identificador del comic
	 * 
	 * URI http://localhost:8085/semillero-servicios/rest/GestionarComic/eliminar?idComic=8 donde el 8 es el parametro id a eliminar
	 */
	@DELETE
	@Path("/eliminar")
	@Produces(MediaType.APPLICATION_JSON)
	public Response eliminarComic(@QueryParam("idComic") Long idComic) {
		
			
			//manejo de la excepcion
			try {
				gestionarComicEJB.eliminarComic(idComic);
				ResultadoDTO resultadoDTO = new ResultadoDTO(Boolean.TRUE, "Comic eliminado exitosamente");
				
				return Response.status(Response.Status.BAD_REQUEST)
						   .entity(resultadoDTO)
						   .build();
			} catch (ManejoExcepciones e) {
				
				logger.error("Se capturo la excepcion "+e.getCodigo()+ " mensaje: "+e.getMensaje());
				return Response.status(Response.Status.BAD_REQUEST)
						   .entity("No se pudo Eliminar el comic")
						   .build();
			}

			
	}
	
	/**
	 * 
	 * @return precio con iva
	 */
	
	@GET
	@Path("/consultarComicsValor")
	@Produces(MediaType.APPLICATION_JSON)
	public List<ComicDTO> consultarComicsValor() {
		return gestionarComicEJB.consultarComicsValor();

	}
}
