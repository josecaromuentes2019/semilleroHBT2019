package com.hbt.semillero.ejb;

import java.util.List;

import javax.ejb.Local;

import com.hbt.semillero.dto.PersonajeDTO;


@Local
public interface IGestionarPersonajeLocal {
	/**
	 * 
	 * Metodo encargado de crear un personaje y persistirlo
	 * 
	 * @author ccastano
	 * 
	 * @param personajeNuevo informacion nueva a crear
	 */
	public void crearPersonaje(PersonajeDTO personajeNuevo);

	/**
	 * 
	 * Metodo encargado de consultar un personaje modificarlo y guardarlo
	 * 
	 * @author ccastano
	 * 
	 * @param personajeModificar informacion nueva a modificar
	 */
	public void modificarPersonaje(Long id, String nombre, PersonajeDTO personajeNuevo);

	/**
	 * 
	 * Metodo encargado de eliminar un personaje modificarlo y guardarlo
	 * 
	 * @author ccastano
	 * 
	 * @param personajeEliminar informacion a eliminar
	 */
	public void eliminarPersonaje(Long idPersonaje);

	/**
	 * 
	 * Metodo encargado de retornar la informacion de un personaje
	 * 
	 * @param idPersonaje identificador del personaje a ser consultado
	 * @return personaje resultado de la consulta
	 * @throws Exception si no se recibe idPersonaje
	 */
	public  List<PersonajeDTO> consultarPersonaje();
	
	public List<PersonajeDTO>  consultarPersonajes(Long idComic);

	
}
