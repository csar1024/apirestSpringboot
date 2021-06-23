package com.bolsadeideas.springboot.vehicle.apirest.exception;

import com.bolsadeideas.springboot.vehicle.apirest.models.entity.County;

public class CountyIdExistException extends RuntimeException {

	private static final String MENSAJE="Ya se encuentra un registro en la base de datos con la id enviada. Registros encontrados : Id: %s Nombre: %s";
								   		
	public CountyIdExistException(County county) {
		super(String.format(MENSAJE, county.getId(),county.getName()));
	}


	
	
	

}
