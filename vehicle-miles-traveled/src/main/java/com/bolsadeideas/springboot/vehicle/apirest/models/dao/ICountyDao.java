package com.bolsadeideas.springboot.vehicle.apirest.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.bolsadeideas.springboot.vehicle.apirest.models.entity.County;

@Repository
public interface ICountyDao extends JpaRepository<County,Long> {

}
