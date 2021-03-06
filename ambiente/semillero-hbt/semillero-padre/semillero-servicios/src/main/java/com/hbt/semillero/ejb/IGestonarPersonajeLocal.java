package com.hbt.semillero.ejb;

import javax.ejb.Local;

import java.util.List;

import com.hbt.semillero.dto.ComicDTO;
import com.hbt.semillero.dto.PersonajeDTO;
import com.hbt.semillero.exception.ManejoExcepciones;


/**
 * Expone los métodos del EJB GestionarComic Las interfaces determinan una
 * especie de contrato donde se define las firmas de los metodos, define que se
 * necesita implementar pero no el como eso lo realiza la clase que la
 * implementa Palabras claves interface e implements
 * 
 * @author jose caro
 *
 */
@Local
public interface IGestonarPersonajeLocal {
	/**
	 * 
	 * Metodo encargado de crear un personaje y persistirlo
	 * 
	 * se agrega throws ManejoExcepciones para controlar la excepcio
	 * 
	 * @param personajeNuevo informacion nueva a crear
	 */
	public void crearPersonaje(PersonajeDTO personajeNuevo) throws ManejoExcepciones ;

	/**
	 * 
	 * Metodo encargado de consultar un personaje modificarlo y guardarlo
	 * 
	 * 
	 * 
	 * @param personajeModificar informacion nueva a modificar
	 */
	public void modificarPersonaje(Long id, String nombre, PersonajeDTO personajeNuevo) throws ManejoExcepciones;

	/**
	 * 
	 * Metodo encargado de eliminar un personaje modificarlo y guardarlo
	 * 
	 *  
	 * 
	 * @param personajeEliminar informacion a eliminar
	 */
	public void eliminarPersonaje(Long idPersonaje) throws ManejoExcepciones ;

	/**
	 * 
	 * Metodo encargado de retornar la informacion de un personaje
	 * 
	 * @param idPersonaje identificador del personaje a ser consultado
	 * @return personaje resultado de la consulta
	 * @throws Exception si no se recibe idPersonaje
	 * 
	 * se agrega throws ManejoExcepciones para controlar la excepcio
	 */
	public  List<PersonajeDTO> consultarPersonaje() throws ManejoExcepciones;
	
	public List<PersonajeDTO>  consultarPersonajes(Long idComic)  throws ManejoExcepciones;
	
	
	
	
	//public void modificarPrsonaje(Long id, String nombre, PersonajeDTO comicNuevo) throws ManejoExcepciones;

	
}