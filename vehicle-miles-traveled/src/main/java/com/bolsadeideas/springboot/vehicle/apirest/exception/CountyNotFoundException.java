package com.bolsadeideas.springboot.vehicle.apirest.exception;

public class CountyNotFoundException extends RuntimeException {

	
	private static final String MENSAJE="No se ha encontrado county : %s";
	
	public CountyNotFoundException(Long id) {
		super(String.format(MENSAJE,id));
	}

}
