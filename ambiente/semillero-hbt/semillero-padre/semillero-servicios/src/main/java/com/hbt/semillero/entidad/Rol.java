package com.hbt.semillero.entidad;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

public class Rol implements Serializable{

	/**
	 * Serializar es pasar un Objeto a un array de bytes y viceversa. Atributo que
	 * determina serialVersionUID es el id único que identifica una clase cuando lo
	 * serializamos. ;ediante este id podemos identificar el objeto convertido en un
	 * array de bytes.
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String nombre;
	//private EstadoEnum estadoEnum;
	private EstadoEnum estadoEnum;

	/**
	 * Constructor de la clase.
	 */
	
	public Rol() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Metodo encargado de retornar el valor del atributo id
	 * 
	 * @return El id asociado a la clase
	 */
	@Id
	@SequenceGenerator(allocationSize = 1, name = "ROL_SEC_ROL_GENERATOR", sequenceName = "SEC_ROL")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ROL_SEC_ROL_GENERATOR")
	@Column(name = "ROL_ID")
	public Long getId() {
		return id;
	}

	/**
	 * Metodo encargado de modificar el valor del atributo id
	 * 
	 * @param id El nuevo id a modificar.
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Metodo encargado de retornar el valor del atributo nombre
	 * 
	 * @return El nombre asociado a la clase
	 */
	@Column(name = "ROL_NOMBRE")
	public String getNombre() {
		return nombre;
	}

	/**
	 * Metodo encargado de modificar el valor del atributo nombre
	 * 
	 * @param nombre El nuevo nombre a modificar.
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	/**
	 * Metodo encargado de retornar el valor del atributo estado
	 * 
	 * @return El estado asociado a la clase
	 */
	@Column(name = "ROL_ESTADO")
	@Enumerated(value = EnumType.STRING)
	public EstadoEnum getEstadoEnum() {
		return estadoEnum;
	}

	/**
	 * Metodo encargado de modificar el valor del atributo estado
	 * 
	 * @param estado El nuevo estado a modificar.
	 */
	public void setEstadoEnum(EstadoEnum estadoEnum) {
		this.estadoEnum = estadoEnum;
	}

	/**
	 * @see java.lang.Object#toString() Metodo que permite asociar al objeto un
	 *      texto representativo
	 */
	@Override
	public String toString() {
		return "Comic [id=" + id + ", nombre=" + nombre +", estado=" + estadoEnum +  "]";
	}

	/**
	 * @see java.lang.Object#hashCode() Este método viene a complementar al método
	 *      equals y sirve para comparar objetos de una forma más rápida en
	 *      estructuras Hash ya que únicamente nos devuelve un número entero. Cuando
	 *      Java compara dos objetos en estructuras de tipo hash (HashMap, HashSet
	 *      etc) primero invoca al método hashcode y luego el equals
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((estadoEnum == null) ? 0 : estadoEnum.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
	}

	/**
	 * @see java.lang.Object#equals(java.lang.Object) Metodo que permite comparar
	 *      objetos
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Comic other = (Comic) obj;
	
		if (estadoEnum == null) {
			if (other.estadoEnum != null)
				return false;
		} else if (!estadoEnum.equals(other.estadoEnum))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		
		return true;
	}
}
