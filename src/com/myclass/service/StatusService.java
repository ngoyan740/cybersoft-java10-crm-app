package com.myclass.service;

import java.util.ArrayList;
import java.util.List;

import com.myclass.dto.StatusDto;
import com.myclass.entity.Status;
import com.myclass.repository.StatusRepository;

public class StatusService {
	
	private StatusRepository statusRepository;
	
	public StatusService() {
		statusRepository = new StatusRepository();
	}
	
	public List<StatusDto> getAll() {
		List<StatusDto> dtos = new ArrayList<StatusDto>();
		List<Status> entities = statusRepository.findAll();

		for (Status entity : entities) {
			StatusDto dto = new StatusDto();
			dto.setId(entity.getId());
			dto.setName(entity.getName());
			
			dtos.add(dto);
		}

		return dtos;
	}

	public StatusDto getById(int id) {
		Status entity = statusRepository.findById(id);
		StatusDto dto = new StatusDto();
		dto.setId(entity.getId());
		dto.setName(entity.getName());

		return dto;
	}
	
	public int insert(StatusDto dto) {
		Status entity = new Status();
		entity.setName(dto.getName());
		
		return statusRepository.save(entity);
	}
	
	public int update(StatusDto dto) {
		Status entity = statusRepository.findById(dto.getId());
		
		if(entity != null) {
			entity.setId(dto.getId());
			entity.setName(dto.getName());
			
			return statusRepository.edit(entity);
		}
		
		return -1;
	}
	
	public int deleteById(int id) {
		return statusRepository.deleteById(id);
	}
	
}
