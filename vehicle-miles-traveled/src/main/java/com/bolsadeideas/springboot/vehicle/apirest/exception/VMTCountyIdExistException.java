package com.bolsadeideas.springboot.vehicle.apirest.exception;

import com.bolsadeideas.springboot.vehicle.apirest.models.entity.VMTCounty;

public class VMTCountyIdExistException extends RuntimeException {

	private static final String MENSAJE = "Ya se encuentra un registro en la base de datos con la id enviada. Registros encontrados : Id: %s";

	public VMTCountyIdExistException(VMTCounty vmtcounty) {
		super(String.format(MENSAJE, vmtcounty.getId()));
	}

}
