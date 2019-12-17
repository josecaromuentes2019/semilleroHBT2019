/**
 * GestionarComicBean.java
 */
package com.hbt.semillero.ejb;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.hibernate.QueryException;

import com.hbt.semillero.dto.ComicDTO;
import com.hbt.semillero.entidad.Comic;
import com.hbt.semillero.exception.ManejoExcepciones;
import com.hbt.semillero.precioIva.PrecioConIva;

/**
 * <b>Descripción:<b> Clase que determina el bean para realizar las gestion de
 * los comics
 * 
 * @author ccastano
 * @version
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class GestionarComicBean implements IGestionarComicLocal {
	
	final static Logger logger = Logger.getLogger(GestionarComicBean.class);

	/**
	 * Atributo em que se usa para interacturar con el contexto de persistencia.
	 */
	@PersistenceContext
	private EntityManager em;
	
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<ComicDTO> consultarComicsValor() {
		
		PrecioConIva precioIVA = new PrecioConIva();
		List<ComicDTO> resultadosComicDTO = new ArrayList<ComicDTO>();
		logger.debug("se ejecuta el metodo consultar commit");
		List<Comic> resultados = em.createQuery("select c from Comic c").getResultList();
		for (Comic comic:resultados) {
			double precio = precioIVA.calcularPrecioConIva(comic.getEstadoEnum().toString(),comic.getPrecio().doubleValue());
			logger.debug("Valor del comics: "+comic.getNombre()+ " Es: "+precio);
			resultadosComicDTO.add(convertirComicToComicDTO(comic));
		}
		return resultadosComicDTO;
	}

	/**
	 * 
	 * @see com.hbt.semillero.ejb.IGestionarComicLocal#crearComic(com.hbt.semillero.dto.ComicDTO)
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void crearComic(ComicDTO comicNuevo) {
		// Entidad nueva
		Comic comic = convertirComicDTOToComic(comicNuevo);
		// Se almacena la informacion y se maneja la enidad comic
		em.persist(comic);
	}

	/**
	 * 
	 * @throws ManejoExcepciones 
	 * @see com.hbt.semillero.ejb.IGestionarComicLocal#modificarComic(com.hbt.semillero.dto.ComicDTO)
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void modificarComic(Long id, String nombre, ComicDTO comicNuevo) throws ManejoExcepciones {
		Comic comicModificar ;
		try {
			if(comicNuevo==null) {
				// Entidad a modificar
				//comicModificar = em.find(Comic.class, id);
				comicModificar = (Comic) em.createQuery("SELECT FROM Comic c WHERE c.id = :idComic").setParameter("idComic", id).getSingleResult();
				
			}else {
				comicModificar = convertirComicDTOToComic(comicNuevo);
			}
			comicModificar.setNombre(nombre);
			//em.merge(comicModificar);
			Query query = em.createQuery("UPDATE Comic c SET c.nombre = :nom WHERE c.id=:id").setParameter("nom",comicModificar.getNombre()).setParameter("id", id);
			//comic = (Comic) query.getSingleResult();
			query.executeUpdate();
			
		} catch (Exception e) {
			logger.error("Error al eliminar el comic... " + e);
			throw new ManejoExcepciones("COD-0003", "Error al ejecutar el metodo eliminar comic", e);
		}
	
	}

	/**
	 * 
	 * @throws ManejoExcepciones 
	 * @see com.hbt.semillero.ejb.IGestionarComicLocal#eliminarComic(java.lang.Long)
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void eliminarComic(Long idComic) throws ManejoExcepciones {
		
		try {
			

			Query query = em.createQuery("DELETE FROM Comic c WHERE c.id = :idComic").setParameter("idComic", idComic);
			query.executeUpdate();
			
		} catch (Exception e) {
			logger.error("Error al eliminar el comic... " + e);
			throw new ManejoExcepciones("COD-0003", "Error al ejecutar el metodo eliminar comic", e);
		}
		//Comic comicEliminar = em.find(Comic.class, idComic);
		//if (comicEliminar != null) {
		//	em.remove(comicEliminar);
		//}
	}

	/**
	 * 
	 * @throws ManejoExcepciones 
	 * @see com.hbt.semillero.ejb.IGestionarComicLocal#consultarComic(java.lang.String)
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public ComicDTO consultarComic(String idComics) throws ManejoExcepciones  {
		
		logger.debug("Se ejecuta el comando consultar comics por Id");
		try {
			Comic comic = null;
			comic = new Comic();
			
			Long idComic = Long.parseLong(idComics);
			logger.debug("No se pudo pasar idComic a entero "+idComics);
			//comic = em.find(Comic.class, Long.parseLong(idComic));
			Query query = em.createQuery("SELECT c FROM Comic c WHERE c.id = :idComic").setParameter("idComic",idComic);
			comic = (Comic) query.getSingleResult();
			ComicDTO comicDTO = convertirComicToComicDTO(comic);
			
			return comicDTO;
			
		} catch (NumberFormatException e) {
			logger.error("No se pudo pasar idComic a entero "+idComics);
			throw new ManejoExcepciones("COD-0001", "ERROR no se pudo pasar "+idComics+ "de String a int", e);
		}catch (Exception e) {
			logger.error("No se pudo ejecutar la consulta, es posible que el: idComic ("+idComics+") no Exista");
			throw new ManejoExcepciones("COD-0002", "ERROR ejecutando consultar por id verifica si existe "+idComics, e);
		}
		
	}

	/**
	 * 
	 * @throws ManejoExcepciones 
	 * @see com.hbt.semillero.ejb.IGestionarComicLocal#consultarComics()
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<ComicDTO> consultarComics() throws ManejoExcepciones {
		logger.debug("Se ejecuta el comando consultar comics");
		List<ComicDTO> resultadosComicDTO = new ArrayList<ComicDTO>();
		
		try {
			
			List<Comic> resultados = em.createQuery("select c from Comic c").getResultList();
			for (Comic comic:resultados) {
				resultadosComicDTO.add(convertirComicToComicDTO(comic));
			}
			
			
		} catch (Exception e) {
			
			logger.error("Error al consultar los el comic... " + e);
			throw new ManejoExcepciones("COD-0004", "Error al ejecutar el metodo consultarComics", e);
		}
		
		return resultadosComicDTO;
		
	}

	/**
	 * 
	 * Metodo encargado de transformar un comic a un comicDTO
	 * 
	 * @param comic
	 * @return
	 */
	private ComicDTO convertirComicToComicDTO(Comic comic) {
		ComicDTO comicDTO = new ComicDTO();
		if(comic.getId()!=null) {
		 comicDTO.setId(comic.getId().toString());
		}
		comicDTO.setNombre(comic.getNombre());
		comicDTO.setEditorial(comic.getEditorial());
		comicDTO.setTematicaEnum(comic.getTematicaEnum());
		comicDTO.setColeccion(comic.getColeccion());
		comicDTO.setNumeroPaginas(comic.getNumeroPaginas());
		comicDTO.setPrecio(comic.getPrecio());
		comicDTO.setAutores(comic.getAutores());
		comicDTO.setColor(comic.getColor());
		comicDTO.setFechaVenta(comic.getFechaVenta());
		comicDTO.setEstadoEnum(comic.getEstadoEnum());
		comicDTO.setCantidad(comic.getCantidad());
		return comicDTO;
	}

	/**
	 * 
	 * Metodo encargado de transformar un comicDTO a un comic
	 * 
	 * @param comic
	 * @return
	 */
	private Comic convertirComicDTOToComic(ComicDTO comicDTO) {
		Comic comic = new Comic();
		if(comicDTO.getId()!=null) {
			comic.setId(Long.parseLong(comicDTO.getId()));
		}
		comic.setNombre(comicDTO.getNombre());
		comic.setEditorial(comicDTO.getEditorial());
		comic.setTematicaEnum(comicDTO.getTematicaEnum());
		comic.setColeccion(comicDTO.getColeccion());
		comic.setNumeroPaginas(comicDTO.getNumeroPaginas());
		comic.setPrecio(comicDTO.getPrecio());
		comic.setAutores(comicDTO.getAutores());
		comic.setColor(comicDTO.getColor());
		comic.setFechaVenta(comicDTO.getFechaVenta());
		comic.setEstadoEnum(comicDTO.getEstadoEnum());
		comic.setCantidad(comicDTO.getCantidad());
		return comic;
	}
}
