package com.bolsadeideas.springboot.vehicle.apirest.models.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bolsadeideas.springboot.vehicle.apirest.models.entity.VMTCounty;

@Repository
public interface IVMTCountyDao extends CrudRepository<VMTCounty,Long> {

}
