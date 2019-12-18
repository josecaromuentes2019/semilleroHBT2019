package com.hbt.semillero.ejb;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import com.hbt.semillero.dto.ComicDTO;
import com.hbt.semillero.dto.PersonajeDTO;
import com.hbt.semillero.dto.RolDTO;
import com.hbt.semillero.entidad.Comic;
import com.hbt.semillero.entidad.Personaje;
import com.hbt.semillero.entidad.Rol;
import com.hbt.semillero.exception.ManejoExcepciones;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)

public class GestionarRol implements IGestionarRol {
	
	final static Logger logger = Logger.getLogger(GestionarPersonajeBean.class);
	
	@PersistenceContext
	private EntityManager entityManager;
	
	

	/*@Override
	public void crearRolPersonaje(RolDTO rolNuevo) {
		// TODO Auto-generated method stub
		
	}*/


	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void crearRol(RolDTO rolNuevo) throws ManejoExcepciones {
		logger.debug("Aqui inicia el metodo CrearROL");
		
		//manejo de la excepcion
		try {
			
			// Entidad nueva
			Rol rol = convertirRolDTOToEntidad(rolNuevo);
			// Se almacena la informacion y se maneja la enidad comic
			entityManager.persist(rol);
			
		} catch (Exception e) {
			

			logger.error("Error al consultar los el Roles... " + e);
			throw new ManejoExcepciones("COD-0005", "Error al ejecutar el metodo crear Rol", e);
		}
	
		
		logger.debug("Aqui finaliza el metodo CrearROL");
	}



	@Override
	public void modificarRolPersonaje(Long id, String nombre, RolDTO rolNuevo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void eliminarRolPersonaje(Long idRol) throws ManejoExcepciones {
		
			logger.debug("Aqui inicia el metodo EliminarPersonaje");
		
		// manejo de la excepcion
		try {

			Query query = entityManager.createQuery("DELETE FROM Rol c WHERE c.id = :idRol").setParameter("idRol", idRol);
			query.executeUpdate();

		} catch (Exception e) {
			logger.error("Error al eliminar el Personaje... " + e);
			throw new ManejoExcepciones("COD-0003", "Error al ejecutar el metodo eliminar Rol", e);
		}

		logger.debug("Aqui finaliza el metodo EliminarPersonaje");
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public RolDTO consultarRolPersonaje(String idRol) throws ManejoExcepciones {
		logger.debug("Aqui inicia el metodo EliminarRol");

		try {
			Rol rol = null;
			rol = new Rol();
			
			Long idrol = Long.parseLong(idRol);
			Query query = entityManager.createQuery("SELECT c FROM Rol c WHERE c.id = :idRol").setParameter("idRol",idrol);
			rol = (Rol) query.getSingleResult();
			RolDTO rolDTO = convertirRolToDTO(rol);
			
			return rolDTO;
			
		} catch (NumberFormatException e) {
			logger.error("No se pudo pasar idComic a entero "+idRol);
			throw new ManejoExcepciones("COD-0001", "ERROR no se pudo pasar "+idRol+ "de String a int", e);
		}catch (Exception e) {
			logger.error("No se pudo ejecutar la consulta, es posible que el: idComic ("+idRol+") no Exista");
			throw new ManejoExcepciones("COD-0002", "ERROR ejecutando consultar por id verifica si existe "+idRol, e);
		}
		
		//rol = entityManager.find(Rol.class, Long.parseLong(idRol));
		//RolDTO rolDTO = convertirRolToDTO(rol);

		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RolDTO> consultarRolPersonaje() throws ManejoExcepciones {
		// TODO Auto-generated method stub
		logger.debug("Aqui inicia el metodo consultar ROL");

		List<RolDTO> resultadosRolDTO = new ArrayList<RolDTO>();
		
		try {
			List<Rol> resultadosRol = entityManager.createQuery("select c from Rol c").getResultList();
			

			for (Rol rol:resultadosRol) {
				resultadosRolDTO.add(convertirRolToDTO(rol));
			}
			
		} catch (Exception e) {
			
			logger.error("Error al consultar los el comic... " + e);
			throw new ManejoExcepciones("COD-0004", "Error al ejecutar el metodo consultarRoles", e);
		}
		
		
		return resultadosRolDTO;
		
		
	}
	
	
	/**
	 * 
	 * Metodo encargado de transformar un RolDTO a un Rol
	 * 
	 * @param Rol
	 * @return
	 */
	private Rol convertirRolDTOToEntidad(RolDTO rolDTO) {
		Rol rol = new Rol();
		
		rol.setId(rolDTO.getId());
		rol.setNombre(rolDTO.getNombre());
		rol.setEstado(rolDTO.getEstado());
		
		return rol;
	}

	/**
	 * 
	 * Metodo encargado de transformar un Rol a un RolDTO
	 * 
	 * @param comic
	 * @return
	 */
	private RolDTO convertirRolToDTO(Rol rol) {
		RolDTO rolDTO = new RolDTO();
		if(rol.getId()!=null) {
			rolDTO.setId(rol.getId());
		}
		
		rolDTO.setNombre(rol.getNombre());
		rolDTO.setEstado(rol.getEstado());
		rolDTO.setId(rol.getId());
	
		
		return rolDTO;
	}

}
