package com.bolsadeideas.springboot.vehicle.apirest.models.services;

import java.util.List;

import com.bolsadeideas.springboot.vehicle.apirest.models.entity.VMTCounty;

public interface IVMTCountyService {

	public List<VMTCounty> findAll();
	
	public VMTCounty add(VMTCounty vmtCounty);
	
	public boolean deleteById(Long id);
	
	public VMTCounty modify(VMTCounty vmtCounty);
}
