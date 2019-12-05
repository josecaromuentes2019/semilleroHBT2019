package com.hbt.semillero.ejb;

import java.util.List;
import com.hbt.semillero.dto.PersonaDTO;

public interface GestionarInformacioPersona {
	
	/**
	 * 
	 * Metodo encargado de crear un comic y persistirlo
	 * 
	 * @author ccastano
	 * 
	 * @param comicNuevo informacion nueva a crear
	 */
	public void crearPersona(PersonaDTO comicNuevo);

	/**
	 * 
	 * Metodo encargado de consultar un comic modificarlo y guardarlo
	 * 
	 * @author ccastano
	 * 
	 * @param comicModificar informacion nueva a modificar
	 */
	public void modificarPersona(Long id, String nombre, PersonaDTO comicNuevo);

	/**
	 * 
	 * Metodo encargado de eliminar un comic modificarlo y guardarlo
	 * 
	 * @author ccastano
	 * 
	 * @param comicEliminar informacion a eliminar
	 */
	public void eliminarPersona(Long idComic);

	/**
	 * 
	 * Metodo encargado de retornar la informacion de un comic
	 * 
	 * @param idComic identificador del comic a ser consultado
	 * @return comic Resultado de la consulta
	 * @throws Exception si no se recibe idComic
	 */
	public PersonaDTO consultarPersona(String idComic);

	/**
	 * 
	 * Metodo encargado de retornar una lista de comics
	 * 
	 * @return
	 */
	public List<PersonaDTO> consultarPersona();

}
