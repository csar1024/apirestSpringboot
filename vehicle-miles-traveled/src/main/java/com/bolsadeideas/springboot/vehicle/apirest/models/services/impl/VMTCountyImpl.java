package com.bolsadeideas.springboot.vehicle.apirest.models.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bolsadeideas.springboot.vehicle.apirest.exception.CountyNotFoundException;
import com.bolsadeideas.springboot.vehicle.apirest.exception.VMTCountyIdExistException;
import com.bolsadeideas.springboot.vehicle.apirest.exception.VMTCountyNotFoundException;
import com.bolsadeideas.springboot.vehicle.apirest.models.dao.ICountyDao;
import com.bolsadeideas.springboot.vehicle.apirest.models.dao.IVMTCountyDao;
import com.bolsadeideas.springboot.vehicle.apirest.models.entity.County;
import com.bolsadeideas.springboot.vehicle.apirest.models.entity.VMTCounty;
import com.bolsadeideas.springboot.vehicle.apirest.models.services.IVMTCountyService;

@Service
public class VMTCountyImpl implements IVMTCountyService {

	@Autowired
	private IVMTCountyDao vmtcountyDao;
	
	@Autowired
	private ICountyDao countyDao;

	@Override
	@Transactional(readOnly = true)
	public List<VMTCounty> findAll() {
		return (List<VMTCounty>) vmtcountyDao.findAll();
	}

	@Override
	@Transactional
	public VMTCounty add(VMTCounty vmtCounty) {
		Optional<VMTCounty> existeVMTCounty = vmtcountyDao.findById(vmtCounty.getId());
		
		if(vmtCounty.getCounty()!=null) {
			Optional<County> existeCounty=countyDao.findById(vmtCounty.getCounty().getId());
			if (!existeCounty.isPresent()) {
				throw new CountyNotFoundException(vmtCounty.getCounty().getId());
			}
			else {
				vmtCounty.getCounty().setId(existeCounty.get().getId());
				vmtCounty.getCounty().setName(existeCounty.get().getName());
				vmtCounty.getCounty().setState(existeCounty.get().getState());
			}
		}
		
		if (existeVMTCounty.isPresent()) {
			throw new VMTCountyIdExistException(existeVMTCounty.get());
		}
			
		VMTCounty resp=vmtcountyDao.save(vmtCounty);
		return resp;
	}

	@Override
	@Transactional
	public boolean deleteById(Long id) {
		if (vmtcountyDao.findById((id)).isPresent()) {
			vmtcountyDao.deleteById(id);
			return true;
		}
		throw new VMTCountyNotFoundException(id);

	}

	@Override
	@Transactional
	public VMTCounty modify(VMTCounty vmtCounty) {
		
		if(vmtCounty.getCounty()!=null) {
			Optional<County> existeCounty=countyDao.findById(vmtCounty.getCounty().getId());
			if (!existeCounty.isPresent()) {
				throw new CountyNotFoundException(vmtCounty.getCounty().getId());
			}
		}
		
		if (vmtcountyDao.findById((long) vmtCounty.getId()).isPresent()) {
			return vmtcountyDao.save(vmtCounty);
		}
		throw new VMTCountyNotFoundException(vmtCounty.getId());
	}

}
