package com.bolsadeideas.springboot.vehicle.apirest.models.services;

import java.util.List;

import com.bolsadeideas.springboot.vehicle.apirest.models.entity.County;

public interface ICountyServices {
	
	public List<County> findAll();
	
	public County add(County county);
	
	public boolean deleteById(Long id);
	
	public County modify(County county);

	
}
