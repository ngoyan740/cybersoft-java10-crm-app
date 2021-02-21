package com.myclass.service;

import java.util.ArrayList;
import java.util.List;

import com.myclass.dto.RoleDto;
import com.myclass.entity.Role;
import com.myclass.repository.RoleRepository;

public class RoleService {
	
	private RoleRepository roleRepository;
	
	public RoleService() {
		roleRepository = new RoleRepository();
	}
	
	public List<RoleDto> getAll() {
		List<RoleDto> dtos = new ArrayList<RoleDto>();
		List<Role> entities = roleRepository.findAll();

		for (Role entity : entities) {
			RoleDto dto = new RoleDto();
			dto.setId(entity.getId());
			dto.setName(entity.getName());
			dto.setDesc(entity.getDescription());
			
			dtos.add(dto);
		}

		return dtos;
	}

	public RoleDto getById(int id) {
		Role entity = roleRepository.findById(id);
		RoleDto dto = new RoleDto();
		dto.setId(entity.getId());
		dto.setName(entity.getName());
		dto.setDesc(entity.getDescription());

		return dto;
	}
	
	public int insert(RoleDto dto) {
		Role entity = new Role();
		entity.setName(dto.getName());
		entity.setDescription(dto.getDesc());
		
		return roleRepository.save(entity);
	}
	
	public int update(RoleDto dto) {
		Role entity = roleRepository.findById(dto.getId());
		
		if(entity != null) {
			entity.setId(dto.getId());
			entity.setName(dto.getName());
			entity.setDescription(dto.getDesc());
			
			return roleRepository.edit(entity);
		}
		
		return -1;
	}
	
	public int deleteById(int id) {
		return roleRepository.deleteById(id);
	}
	
}
