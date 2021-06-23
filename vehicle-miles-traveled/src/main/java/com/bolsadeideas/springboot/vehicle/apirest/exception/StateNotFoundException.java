package com.bolsadeideas.springboot.vehicle.apirest.exception;

public class StateNotFoundException extends RuntimeException {

	private static final String MENSAJE = "No se ha encontrado state : %s";

	public StateNotFoundException(Long id) {
		super(String.format(MENSAJE,id));
	}
}
