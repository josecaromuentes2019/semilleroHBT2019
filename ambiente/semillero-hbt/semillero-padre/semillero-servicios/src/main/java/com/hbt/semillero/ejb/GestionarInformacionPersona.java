package com.hbt.semillero.ejb;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.hbt.semillero.dto.ComicDTO;
import com.hbt.semillero.dto.PersonaDTO;
import com.hbt.semillero.entidad.Comic;

/**
 * <b>Descripción:<b> Clase que determina el bean para realizar las gestion de
 * los persona
 * 
 * @author ccastano
 * @version
 */

public class GestionarInformacionPersona implements GestionarInformacioPersona{
	
	final static Logger logger = Logger.getLogger(GestionarComicBean.class);
	
	
	/**
	 * 
	 * @see com.hbt.semillero.ejb.IGestionarComicLocal#crearPersona(com.hbt.semillero.dto.ComicDTO)
	 */

	@Override
	public void crearPersona(PersonaDTO comicNuevo) {
		// TODO Auto-generated method stub
		logger.debug("se ejecuta el metodo crear Persona");
	}
	
	
	
	/**
	 * 
	 * @see com.hbt.semillero.ejb.IGestionarComicLocal#eliminarPersona(java.lang.Long)
	 */

	@Override
	public void eliminarPersona(Long idComic) {
		// TODO Auto-generated method stub
		
		logger.debug("se ejecuta el metodo eliminar Persona");
		
	}
	
	
	/**
	 * 
	 * @see com.hbt.semillero.ejb.IGestionarComicLocal#consultarPersona(java.lang.String)
	 */
	@Override
	public PersonaDTO consultarPersona(String idPersona) {
		
		// TODO Auto-generated method stub
		logger.debug("se ejecuta el metodo consultar Persona");
		
		return null;
		
		
	}
	
	
	/**
	 * 
	 * @see com.hbt.semillero.ejb.IGestionarComicLocal#consultarComics()
	 */

	@Override
	public List<PersonaDTO> consultarPersona() {
		// TODO Auto-generated method stub
		List<PersonaDTO> resultadosComicDTO = new ArrayList<PersonaDTO>();
		logger.debug("se ejecuta el metodo consultar Persona");
		
		return resultadosComicDTO;
	}


	@Override
	public void modificarPersona(Long id, String nombre, PersonaDTO comicNuevo) {
		// TODO Auto-generated method stub
		logger.debug("se ejecuta el metodo modificar Persona");
		
	}

}
