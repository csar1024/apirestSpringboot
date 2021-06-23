package com.bolsadeideas.springboot.vehicle.apirest.exception;

import com.bolsadeideas.springboot.vehicle.apirest.models.entity.State;

public class StateIdExistException extends RuntimeException {

	private static final String MENSAJE = "Ya se encuentra un registro en la base de datos con la id enviada. Registros encontrados : Id: %s Nombre: %s";

	public StateIdExistException(State state) {
		super(String.format(MENSAJE, state.getId(),state.getName()));
	}

}
