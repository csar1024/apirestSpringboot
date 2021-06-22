package com.bolsadeideas.springboot.vehicle.apirest.exception;

public class VMTCountyNotFoundException extends RuntimeException{

	private static final String MENSAJE = "No se ha encontrado registro : %s";

	public VMTCountyNotFoundException(Long id) {
		super(String.format(MENSAJE,id));
	}
}
