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
		 * @author Jose caro
		 * 
		 * se agrega throws ManejoExcepciones para controlar la excepcio
		 * 
		 * @param comicNuevo informacion nueva a crear
		 */
		public void crearRolPersonaje(RolDTO rolNuevo) throws ManejoExcepciones;

		/**
		 * 
		 * Metodo encargado de consultar un comic modificarlo y guardarlo
		 * 
		 * 
		 * 
		 * @param comicModificar informacion nueva a modificar
		 */
		public void modificarRolPersonaje(Long id, String nombre, RolDTO rolNuevo);

		/**
		 * 
		 * Metodo encargado de eliminar un comic modificarlo y guardarlo
		 * 
		 * 
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
		 * 
		 * se agrega throws ManejoExcepciones para controlar la excepcio
		 */
		public RolDTO consultarRolPersonaje(String idRol) throws ManejoExcepciones;

		/**
		 * 
		 * Metodo encargado de retornar una lista de comics
		 * 
		 * se agrega throws ManejoExcepciones para controlar la excepcio
		 * 
		 * @return
		 */
		public List<RolDTO> consultarRolPersonaje() throws ManejoExcepciones;
	
	

}
