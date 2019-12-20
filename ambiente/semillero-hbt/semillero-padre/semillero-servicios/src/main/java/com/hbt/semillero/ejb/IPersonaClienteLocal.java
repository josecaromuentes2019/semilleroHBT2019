package com.hbt.semillero.ejb;

import java.util.List;

import com.hbt.semillero.dto.PersonaClienteDTO;
import com.hbt.semillero.dto.PersonajeDTO;
import com.hbt.semillero.exception.ManejoExcepciones;

public interface IPersonaClienteLocal {
	
	public void crearPersona(PersonaClienteDTO personajeNuevo) throws ManejoExcepciones;
	
	public  List<PersonaClienteDTO> consultarPersona() throws ManejoExcepciones ;
	
	public List<PersonaClienteDTO>  consultarPersonajes(Long idPersona) ;
	
	public void modificarPersonaje(Long id, String nombre, PersonaClienteDTO personaNuevo);
	
	public void eliminarPersonaje(Long idPersona);

}
