package com.hbt.semillero.ejb;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.apache.log4j.Logger;

import com.hbt.semillero.dto.PersonajeDTO;
import com.hbt.semillero.dto.RolDTO;
import com.hbt.semillero.entidad.Comic;
import com.hbt.semillero.entidad.Personaje;
import com.hbt.semillero.entidad.Rol;

public class GestionarRolBean implements IGestionarRolLocal{
	
	private EntityManager entitymanager; 
	final static Logger logger = Logger.getLogger(GestionarRolBean.class);

	@Override
	public void crearRol(RolDTO rolNuevo) {
		// TODO Auto-generated method stub
		
		
		Rol rol = convertirDTOEntidad(rolNuevo);
		entitymanager.persist(rolNuevo);
		
		
		logger.debug("finaliza el metodo crear Personaje");
		
	}

	@Override
	public void modificarRol(Long id, String nombre, RolDTO comicNuevo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void eliminarRol(Long idRol) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<RolDTO> consultarRol(Long idRol) {
		// TODO Auto-generated method stub
		logger.debug("se ejecuta el metodo consultar Personaje");
		
		String query = "SELECT rol"+
						"FROM Rol rol"+
						"WHERE rol.id = :idRol";
		
		List<Rol> listaRol = entitymanager.createQuery(query).setParameter("id", idRol).getResultList();
		
		List<RolDTO> listaRolDTO = new ArrayList();
		
		for (Rol rol : listaRol) {
			listaRolDTO.add(convertirEntidadToDTO(rol));
		}
		
		logger.debug("finaliza el metodo consultar Personaje");
		return listaRolDTO;
	}

	@Override
	public List<RolDTO> consultarRol() {
		// TODO Auto-generated method stub
	logger.debug("se ejecuta el metodo consultar rol");
		
		String query = "SELECT rol"+
						"FROM Rol rol";
		
		List<Rol> listaRol = entitymanager.createQuery(query).getResultList();
		
		List<RolDTO> listaRolDTO = new ArrayList();
		
		for (Rol rol : listaRol) {
			listaRolDTO.add(convertirEntidadToDTO(rol));
		}
		
		logger.debug("finaliza el metodo consultar rol");
		return listaRolDTO;
	}
	
	
	private Rol convertirDTOEntidad(RolDTO rolNuevo) {
		
		Rol rol  = new Rol();
		
		rol.setNombre(rolNuevo.getNombre());
		rol.setId(rolNuevo.getId());
		rol.setEstadoEnum(rolNuevo.getEstado());
		return rol;
		
	}
	
private RolDTO convertirEntidadToDTO(Rol rol) {
		
	RolDTO rolDto  = new RolDTO();
		
	rolDto.setNombre(rol.getNombre());
	rolDto.setId(rol.getId());
	rolDto.setEstado(rol.getEstadoEnum());
		
		return rolDto;
		
	}

	
}
