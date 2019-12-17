package com.hbt.semillero.ejb;

import java.util.List;

import javax.ejb.Local;

import com.hbt.semillero.dto.PersonajeDTO;
import com.hbt.semillero.dto.RolDTO;
import com.hbt.semillero.exception.ManejoExcepciones;

@Local
public interface IGestionarRol {
	
	
		/**
		 * 
		 * Metodo encargado de crear un comic y persistirlo
		 * 
		 * @author Eric Varilla
		 * 
		 * @param comicNuevo informacion nueva a crear
		 */
		public void crearRolPersonaje(RolDTO rolNuevo) throws ManejoExcepciones;

		/**
		 * 
		 * Metodo encargado de consultar un comic modificarlo y guardarlo
		 * 
		 * @author Eric Varilla
		 * 
		 * @param comicModificar informacion nueva a modificar
		 */
		public void modificarRolPersonaje(Long id, String nombre, RolDTO rolNuevo);

		/**
		 * 
		 * Metodo encargado de eliminar un comic modificarlo y guardarlo
		 * 
		 * @author Eric Varilla
		 * 
		 * @param comicEliminar informacion a eliminar
		 */
		public void eliminarRolPersonaje(Long idRol);

		/**
		 * 
		 * Metodo encargado de retornar la informacion de un comic
		 * 
		 * @param idComic identificador del comic a ser consultado
		 * @return comic Resultado de la consulta
		 * @throws Exception si no se recibe idComic
		 */
		public RolDTO consultarRolPersonaje(String idRol) throws ManejoExcepciones;

		/**
		 * 
		 * Metodo encargado de retornar una lista de comics
		 * 
		 * @return
		 */
		public List<RolDTO> consultarRolPersonaje() throws ManejoExcepciones;
	
	

}
