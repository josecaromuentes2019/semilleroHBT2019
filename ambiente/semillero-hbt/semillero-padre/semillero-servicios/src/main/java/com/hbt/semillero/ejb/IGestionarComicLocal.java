package com.hbt.semillero.ejb;

import java.util.List;

import javax.ejb.Local;

import com.hbt.semillero.dto.ComicDTO;
import com.hbt.semillero.exception.ManejoExcepciones;

/**
 * Expone los métodos del EJB GestionarComic Las interfaces determinan una
 * especie de contrato donde se define las firmas de los metodos, define que se
 * necesita implementar pero no el como eso lo realiza la clase que la
 * implementa Palabras claves interface e implements
 * 
 * @author ccastano
 *
 */
@Local
public interface IGestionarComicLocal {

	/**
	 * 
	 * Metodo encargado de crear un comic y persistirlo
	 * 
	 * @author ccastano
	 * 
	 * @param comicNuevo informacion nueva a crear
	 */
	public void crearComic(ComicDTO comicNuevo);

	/**
	 * 
	 * Metodo encargado de consultar un comic modificarlo y guardarlo
	 * 
	 * @author ccastano
	 * 
	 * @param comicModificar informacion nueva a modificar
	 * 
	 * se agrega throws ManejoExcepciones para controlar la excepcio
	 */
	public void modificarComic(Long id, String nombre, ComicDTO comicNuevo) throws ManejoExcepciones;

	/**
	 * 
	 * Metodo encargado de eliminar un comic modificarlo y guardarlo
	 * 
	 * @author ccastano
	 * 
	 * @param comicEliminar informacion a eliminar
	 * 
	 * se agrega throws ManejoExcepciones para controlar la excepcio
	 */
	public void eliminarComic(Long idComic) throws ManejoExcepciones;

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
	public ComicDTO consultarComic(String idComic) throws ManejoExcepciones;

	/**
	 * 
	 * Metodo encargado de retornar una lista de comics
	 * 
	 * se agrega throws ManejoExcepciones para controlar la excepcio
	 * 
	 * @return
	 */
	public List<ComicDTO> consultarComics() throws ManejoExcepciones;
	
	/**
	 * precio con iva
	 * @return
	 */
	public List<ComicDTO> consultarComicsValor() ;
}
