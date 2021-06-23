package com.bolsadeideas.springboot.vehicle.apirest.models.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bolsadeideas.springboot.vehicle.apirest.exception.StateIdExistException;
import com.bolsadeideas.springboot.vehicle.apirest.exception.StateNotFoundException;
import com.bolsadeideas.springboot.vehicle.apirest.models.dao.IStateDao;
import com.bolsadeideas.springboot.vehicle.apirest.models.entity.State;
import com.bolsadeideas.springboot.vehicle.apirest.models.services.IStateService;

@Service
public class StateImpl implements IStateService {

	@Autowired
	private IStateDao stateService;

	@Override
	@Transactional(readOnly = true)
	public List<State> findAll() {
		return (List<State>) stateService.findAll();
	}

	@Override
	@Transactional
	public State add(State state) {
		Optional<State> existeState = stateService.findById(state.getId());
		if (existeState.isPresent()) {
			throw new StateIdExistException(existeState.get());
		}
		return stateService.save(state);

	}

	@Override
	@Transactional
	public boolean deleteById(Long id) {
		if (stateService.findById((id)).isPresent()) {
			stateService.deleteById(id);
			return true;
		}
		throw new StateNotFoundException(id);
	}

	@Override
	@Transactional
	public State modify(State state) {
		if (stateService.findById((long) state.getId()).isPresent()) {
			return stateService.save(state);
		}
		throw new StateNotFoundException(state.getId());
	}

}
