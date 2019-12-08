package com.hbt.semillero.ejb;

import java.util.ArrayList;
import java.util.List;


import com.hbt.semillero.dto.PersonajeDTO;
import com.hbt.semillero.entidad.Comic;
import com.hbt.semillero.entidad.Personaje;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;




/**
 * <b>Descripción:<b> Clase que determina el bean para realizar las gestion de
 * los personajes
 * 
 * @author JOSECARO
 * @version
 */

@Stateless
public class GestionarPersonajeBean implements IGestionarPersonajeLocal{
	
	//entitymanager para las operaciones que se realicen sobre la BD
	@PersistenceContext
	private EntityManager entitymanager; 
	
final static Logger logger = Logger.getLogger(GestionarPersonajeBean.class);
	
	
	/**
	 * 
	 * @see com.hbt.semillero.ejb.IGestionarComicLocal#crearPersona(com.hbt.semillero.dto.ComicDTO)
	 */

	@Override
	public void crearPersonaje(PersonajeDTO personajeNuevo) {
		// TODO Auto-generated method stub
		
		Personaje personaje = convertirDTOEntidad(personajeNuevo);
		entitymanager.persist(personaje);
		
		
		logger.debug("finaliza el metodo crear Personaje");
	}
	
	
	
	/**
	 * 
	 * @see com.hbt.semillero.ejb.IGestionarComicLocal#eliminarPersona(java.lang.Long)
	 */

	@Override
	public void eliminarPersonaje(Long idPersonaje) {
		// TODO Auto-generated method stub
		
		logger.debug("se ejecuta el metodo eliminar Personaje");
		
		
		logger.debug("finaliza el metodo eliminar Personaje");
		
	}
	
	
	/**
	 * 
	 * @see com.hbt.semillero.ejb.IGestionarComicLocal#consultarPersona(java.lang.String)
	 */
	@Override
	public List<PersonajeDTO> consultarPersonaje(Long idComic) {
		
		// TODO Auto-generated method stub
		
				logger.debug("se ejecuta el metodo consultar Personaje");
				
				String query = "SELECT personaje"+
								"FROM Personaje personaje"+
								"WHERE personaje.Comic.id = :idComic";
				
				List<Personaje> listaPersonaje = entitymanager.createQuery(query).setParameter("idComic", idComic).getResultList();
				
				List<PersonajeDTO> listaPersonajesDTO = new ArrayList();
				
				for (Personaje personaje : listaPersonaje) {
					listaPersonajesDTO.add(convertirEntidadToDTO(personaje));
				}
				
				logger.debug("finaliza el metodo consultar Personaje");
				return listaPersonajesDTO;
	
	}
	
	
	/**
	 * 
	 * @see com.hbt.semillero.ejb.IGestionarComicLocal#consultarComics()
	 */

	@Override
	public List<PersonajeDTO> consultarPersonaje() {
		// TODO Auto-generated method stub
		
		logger.debug("se ejecuta el metodo consultar Personaje");
		
		String query = "SELECT personaje"+
						"FROM Personaje personaje";
		
		List<Personaje> listaPersonaje = entitymanager.createQuery(query).getResultList();
		
		List<PersonajeDTO> listaPersonajesDTO = new ArrayList();
		
		for (Personaje personaje : listaPersonaje) {
			listaPersonajesDTO.add(convertirEntidadToDTO(personaje));
		}
		
		logger.debug("finaliza el metodo consultar Personaje");
		return listaPersonajesDTO;
	}


	@Override
	public void modificarPersonaje (Long id, String nombre, PersonajeDTO comicNuevo) {
		// TODO Auto-generated method stub
		logger.debug("se ejecuta el metodo modificar Personaje");
		
		
		logger.debug("finaliza el metodo modificar Personaje");
		
	}
	
	
	private Personaje convertirDTOEntidad(PersonajeDTO personajeNuevo) {
		
		Personaje personaje  = new Personaje();
		
		personaje.setNombre(personajeNuevo.getNombre());
		personaje.setId(personajeNuevo.getId());
		personaje.setComic(new Comic());
		personaje.getComic().setId(personajeNuevo.getId());
		personaje.setEstado(personajeNuevo.getEstado());
		personaje.setSuperPoder(personajeNuevo.getSuperPoder());
		
		return personaje;
		
	}
	
private PersonajeDTO convertirEntidadToDTO(Personaje personaje) {
		
	PersonajeDTO personajeDto  = new PersonajeDTO();
		
	personajeDto.setNombre(personaje.getNombre());
	personajeDto.setId(personaje.getId());
	personajeDto.setIdComic(personaje.getComic().getId());
	personajeDto.setEstado(personaje.getEstado());
	personajeDto.setSuperPoder(personaje.getSuperPoder());
		
		return personajeDto;
		
	}

}
