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

import com.hbt.semillero.dto.ComicDTO;
import com.hbt.semillero.dto.PersonajeDTO;
import com.hbt.semillero.entidad.Comic;
import com.hbt.semillero.entidad.Personaje;
import com.hbt.semillero.exception.ManejoExcepciones;

/**
 * <b>Descripción:<b> Clase que determina el bean para realizar las gestion de
 * las peronas
 * 
 * @author jose caro
 * @version
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class GestionarPersonajeBean implements IGestonarPersonajeLocal {

	final static Logger logger = Logger.getLogger(GestionarPersonajeBean.class);

	/**
	 * Atributo em que se usa para interacturar con el contexto de persistencia.
	 */
	@PersistenceContext
	private EntityManager entityManager;

	
	
	/**
	 * 
	 * @throws ManejoExcepciones 
	 * @see com.hbt.semillero.ejb.IGestionarPersonajeLocal#crearComic(com.hbt.semillero.dto.PersonajeDTO)
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void crearPersonaje(PersonajeDTO personajeNuevo) throws ManejoExcepciones {
		logger.debug("Aqui inicia el metodo CrearPersonaje");
		
		//manejo de la excepcion
		try {
			// Entidad nueva
			Personaje personaje = convertirPersonajeDTOToEntidad(personajeNuevo);
			// Se almacena la informacion y se maneja la enidad comic
			entityManager.persist(personaje);
			
		} catch (Exception e) {
			

			logger.error("Error al consultar los el comic... " + e);
			throw new ManejoExcepciones("COD-0005", "Error al ejecutar el metodo crear personaje", e);
		}
		
	
		
		logger.debug("Aqui finaliza el metodo CrearPersonaje");
	}

	/**
	 * 
	 * @throws ManejoExcepciones 
	 * @see com.hbt.semillero.ejb.IGestionarPersonajeLocal#modificarPersonaje(com.hbt.semillero.dto.PersonajeDTO)
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void modificarPersonaje(Long id, String nombre, PersonajeDTO personajeNuevo) throws ManejoExcepciones {
		logger.debug("Aqui inicia el metodo ModificarPersonaje");
		
		Personaje personajeModificar;
		// manejo de la excepcion
		try {

			Query query = entityManager.createQuery("UPDATE Personaje c SET c.nombre = :nom WHERE c.id=:id")
					.setParameter("nom", nombre).setParameter("id", id);

			query.executeUpdate();

		} catch (Exception e) {
			logger.error("Error al eliminar el Personaje... " + e);
			throw new ManejoExcepciones("COD-0003", "Error al ejecutar el metodo eliminar Personaje", e);
		}

		//logger.debug("Aqui finaliza el metodo ModificarPersonaje");
	}
	
	
	

	

	/**
	 * 
	 * @throws ManejoExcepciones 
	 * @see com.hbt.semillero.ejb.IGestionarPersonajeLocal#eliminarPersonaje(java.lang.Long)
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void eliminarPersonaje(Long idPersonaje) throws ManejoExcepciones {
		logger.debug("Aqui inicia el metodo EliminarPersonaje");
		
		// manejo de la excepcion
		try {

			Query query = entityManager.createQuery("DELETE FROM Personaje c WHERE c.id = :idPers").setParameter("idPers", idPersonaje);
			query.executeUpdate();

		} catch (Exception e) {
			logger.error("Error al eliminar el Personaje... " + e);
			throw new ManejoExcepciones("COD-0003", "Error al ejecutar el metodo eliminar Personaje", e);
		}

		logger.debug("Aqui finaliza el metodo EliminarPersonaje");
	}
	
	

	

	@SuppressWarnings("unchecked")
	public List<PersonajeDTO> consultarPersonaje() throws ManejoExcepciones {
		logger.debug("Aqui inicia el metodo EliminarPersonaje");

		List<PersonajeDTO> resultadosPersonajeDTO = new ArrayList<PersonajeDTO>();
		
		//manejo de la excepcion
		try {
			List<Personaje> resultados = entityManager.createQuery("select c from Personaje c").getResultList();
			

			for (Personaje personaje:resultados) {
				resultadosPersonajeDTO.add(convertirPersonajeTopersonajeDTO(personaje));
			}
			
			
		} catch (Exception e) {
			
			logger.error("Error al consultar los el comic... " + e);
			throw new ManejoExcepciones("COD-0004", "Error al ejecutar el metodo consultarComics", e);
		}
		
		return resultadosPersonajeDTO;
		
	}

	/*public List<PersonajeDTO> consultarPersonajes(Long idComic) {
		
		
		return null;
	}*/
	@SuppressWarnings("unchecked")
	@Override
	public List<PersonajeDTO> consultarPersonajes(Long idPersonaje) throws ManejoExcepciones{
		logger.debug("Aqui inicia el metodo EliminarPersonaje");

		try {
			
			String query = "SELECT personaje FROM Personaje personaje WHERE personaje.comic.id = :idComic ";
			// List<PersonajeDTO> listarPersonajes = new ArrayList<PersonajeDTO>();
			List<Personaje> resultados = entityManager.createQuery(query).setParameter("idComic", idPersonaje).getResultList();
			List<PersonajeDTO> resultadosPersonajeDTO = new ArrayList<PersonajeDTO>();

			for (Personaje personajeDTO : resultados) {
				resultadosPersonajeDTO.add(convertirPersonajeTopersonajeDTO(personajeDTO));
			}
			logger.debug("Aqui finaliza el metodo EliminarPersonaje");

			return resultadosPersonajeDTO;
			
		} catch (NumberFormatException e) {
			logger.error("No se pudo pasar idComic a entero "+idPersonaje);
			throw new ManejoExcepciones("COD-0001", "ERROR no se pudo pasar "+idPersonaje+ "de String a int", e);
		}catch (Exception e) {
			logger.error("No se pudo ejecutar la consulta, es posible que el: idComic ("+idPersonaje+") no Exista");
			throw new ManejoExcepciones("COD-0002", "ERROR ejecutando consultar por id verifica si existe "+idPersonaje, e);
		}
		
	}

	/**
	 * 
	 * Metodo encargado de transformar un comicDTO a un comic
	 * 
	 * @param comic
	 * @return
	 */
	private Personaje convertirPersonajeDTOToEntidad(PersonajeDTO personajeDTO) {
		Personaje personaje = new Personaje();
		
		personaje.setId(personajeDTO.getId());
		personaje.setNombre(personajeDTO.getNombre());
		personaje.setComic(new Comic());
		personaje.getComic().setId((personajeDTO.getIdComic()));
		personaje.setEstado(personajeDTO.getEstado());
		personaje.setSuperPoder(personajeDTO.getSuperPoder());
		
		
		return personaje;
	}

	/**
	 * 
	 * Metodo encargado de transformar un comic a un comicDTO
	 * 
	 * @param comic
	 * @return
	 */
	private PersonajeDTO convertirPersonajeTopersonajeDTO(Personaje personaje) {
		PersonajeDTO personajeDTO = new PersonajeDTO();
		if(personaje.getId()!=null) {
			personajeDTO.setId(personaje.getId());
		}
		personajeDTO.setNombre(personaje.getNombre());
		personajeDTO.setEstado(personaje.getEstado());
		personajeDTO.setSuperPoder(personaje.getSuperPoder());
		personajeDTO.setIdComic(personaje.getComic().getId());
	
		
		return personajeDTO;
	}

}