package com.hbt.semillero.ejb;

import java.util.List;

import com.hbt.semillero.dto.RolDTO;

public interface IGestionarRolLocal {
	
	/**
	 * 
	 * Metodo encargado de crear un rol y persistirlo
	 * 
	 * @author ccastano
	 * 
	 * @param rolNuevo informacion nueva a crear
	 */
	public void crearRol(RolDTO comicNuevo);

	/**
	 * 
	 * Metodo encargado de consultar un rol modificarlo y guardarlo
	 * 
	 * 
	 * 
	 * @param rolModificar informacion nueva a modificar
	 */
	public void modificarRol(Long id, String nombre, RolDTO comicNuevo);

	/**
	 * 
	 * Metodo encargado de eliminar un rol modificarlo y guardarlo
	 * 
	 * 
	 * 
	 * @param rolEliminar informacion a eliminar
	 */
	public void eliminarRol(Long idRol);

	/**
	 * 
	 * Metodo encargado de retornar la informacion de un rol
	 * 
	 * @param idComic identificador del rol a ser consultado
	 * @return rol Resultado de la consulta
	 * @throws Exception si no se recibe idRol
	 */
	public List<RolDTO> consultarRol(Long idRol);

	/**
	 * 
	 * Metodo encargado de retornar una lista de rol
	 * 
	 * @return
	 */
	public List<RolDTO> consultarRol();

}
