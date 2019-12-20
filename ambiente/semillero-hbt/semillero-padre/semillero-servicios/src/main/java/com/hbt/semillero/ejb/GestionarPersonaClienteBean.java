package com.hbt.semillero.ejb;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;

import com.hbt.semillero.dto.PersonaClienteDTO;
import com.hbt.semillero.dto.PersonajeDTO;
import com.hbt.semillero.entidad.Comic;
import com.hbt.semillero.entidad.PersonaCliente;
import com.hbt.semillero.entidad.Personaje;
import com.hbt.semillero.exception.ManejoExcepciones;


	@TransactionManagement(TransactionManagementType.CONTAINER)
	public class GestionarPersonaClienteBean  implements IPersonaClienteLocal{

	
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
	@Override
	public void crearPersona(PersonaClienteDTO personajeNuevo) throws ManejoExcepciones {
		
		logger.debug("Aqui inicia el metodo CrearPersonaje");
		
		//manejo de la excepcion
		try {
			// Entidad nueva
			PersonaCliente persona = convertirPersonaClienteDTOToEntidad(personajeNuevo);
			// Se almacena la informacion y se maneja la enidad comic
			entityManager.persist(persona);
			
		} catch (Exception e) {
			

			logger.error("Error al consultar los la Persona cliente... " + e);
			throw new ManejoExcepciones("COD-0005", "Error al ejecutar el metodo crear persona cliente", e);
		}
		
	
		
		logger.debug("Aqui finaliza el metodo CrearPersonaje");
		
	}
	
	
	
	

	@Override
	public List<PersonaClienteDTO> consultarPersona() throws ManejoExcepciones {
		// TODO Auto-generated method stub
		
		logger.debug("Aqui inicia el metodo EliminarPersonaje");

		List<PersonaClienteDTO> resultadosPersonaDTO = new ArrayList<PersonaClienteDTO>();
		
		//manejo de la excepcion
		try {
			List<PersonaCliente> resultados = entityManager.createQuery("select c from PersonaCliente c").getResultList();
			

			for (PersonaCliente persona:resultados) {
				
				resultadosPersonaDTO.add(convertirEntidadTopersonaClienteDTO(persona));
			}
			
			
		} catch (Exception e) {
			
			logger.error("Error al consultar los el comic... " + e);
			throw new ManejoExcepciones("COD-0004", "Error al ejecutar el metodo consultar PersonaCliente", e);
		}
		
		return resultadosPersonaDTO;
		
	}

	@Override
	public List<PersonaClienteDTO> consultarPersonajes(Long idPersona) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void modificarPersonaje(Long id, String nombre, PersonaClienteDTO personaNuevo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void eliminarPersonaje(Long idPersona) {
		// TODO Auto-generated method stub
		
	}
	
	
	private PersonaCliente convertirPersonaClienteDTOToEntidad(PersonaClienteDTO personaDTO) {
		PersonaCliente persona = new PersonaCliente();
		
		persona.setIdPersona(personaDTO.getIdPersona());
		persona.setNombre(personaDTO.getNombre());
		persona.setIdentidad(personaDTO.getIdentidad());
		persona.setFeNacimiento(personaDTO.getFeNacimiento());
		
		
		return persona;
	}
	
	private PersonaClienteDTO convertirEntidadTopersonaClienteDTO(PersonaCliente persona) {
		PersonaClienteDTO personaDTO = new PersonaClienteDTO();
		if(persona.getIdPersona()!=null) {
			personaDTO.setIdentidad(persona.getIdPersona());
		}
		personaDTO.setNombre(persona.getNombre());
		personaDTO.setIdentidad(persona.getIdentidad());
		personaDTO.setFeNacimiento(persona.getFeNacimiento());
	
		
		return personaDTO;
	}

}
