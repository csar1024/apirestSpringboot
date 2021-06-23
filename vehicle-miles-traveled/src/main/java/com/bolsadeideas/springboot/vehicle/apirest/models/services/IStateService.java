package com.bolsadeideas.springboot.vehicle.apirest.models.services;

import java.util.List;

import com.bolsadeideas.springboot.vehicle.apirest.models.entity.State;

public interface IStateService {

	public List<State> findAll();
	
	public State add(State state);
	
	public boolean deleteById(Long id);
	
	public State modify(State state); 
}
