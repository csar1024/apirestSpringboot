package com.bolsadeideas.springboot.vehicle.apirest.models.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bolsadeideas.springboot.vehicle.apirest.models.dao.ICountyDao;
import com.bolsadeideas.springboot.vehicle.apirest.models.dao.IStateDao;
import com.bolsadeideas.springboot.vehicle.apirest.models.entity.County;
import com.bolsadeideas.springboot.vehicle.apirest.models.entity.State;
import com.bolsadeideas.springboot.vehicle.apirest.models.services.ICountyServices;
import com.bolsadeideas.springboot.vehicle.apirest.exception.CountyIdExistException;
import com.bolsadeideas.springboot.vehicle.apirest.exception.CountyNotFoundException;
import com.bolsadeideas.springboot.vehicle.apirest.exception.StateNotFoundException;

@Service
public class CountyImpl implements ICountyServices {

	@Autowired
	private ICountyDao countyDao;
	
	@Autowired
	private IStateDao stateDao;
		
	@Override
	@Transactional(readOnly = true)
	public List<County> findAll() {
		return (List<County>) countyDao.findAll();
	}
	
	@Override
	@Transactional
	public County add(County county) {
		Optional<County> existeCounty=countyDao.findById(county.getId());
		
		if(existeCounty.isPresent()) {
			   throw new CountyIdExistException(existeCounty.get());
		}
		
		if(county.getState()!=null) {
			Optional<State> existeState=stateDao.findById(county.getState().getId());
			if(!existeState.isPresent()) {
				throw new StateNotFoundException(county.getState().getId());
			}
			else {
				county.setState(existeState.get());
			}
		}
		
		return countyDao.save(county);
		
	}
	
	@Override
	@Transactional
	public boolean deleteById(Long id) {
		if(countyDao.findById((id)).isPresent()) {
			countyDao.deleteById(id);
			return true;
		}
		throw new CountyNotFoundException(id);
	}

	@Override
	@Transactional
	public County modify(County county){
		
		if(county.getState()!=null) {
			Optional<State> existeState=stateDao.findById(county.getState().getId());
			if(!existeState.isPresent()) {
				throw new StateNotFoundException(county.getState().getId());
			}
		}
		
		if(countyDao.findById((long) county.getId()).isPresent()) {
			return countyDao.save(county);
		}		
		throw new CountyNotFoundException(county.getId());
	}

}
