package com.hbt.semillero.exception;

public class ManejoExcepciones extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private String codigo;
	private String mensaje;
	
	public ManejoExcepciones(String codigo, String mensaje, Throwable causa) {
		
		this.codigo = codigo;
		this.mensaje = mensaje;
		
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	
	

}
