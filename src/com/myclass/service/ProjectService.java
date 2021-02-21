package com.myclass.service;

import java.util.ArrayList;
import java.util.List;

import com.myclass.dto.ProjectDto;
import com.myclass.entity.Project;
import com.myclass.repository.ProjectRepository;

public class ProjectService {
	
	private ProjectRepository projectRepository;
	
	public ProjectService() {
		projectRepository = new ProjectRepository();
	}
	
	public List<ProjectDto> getAll() {
		List<ProjectDto> dtos = new ArrayList<ProjectDto>();
		List<Project> entities = projectRepository.findAll();

		for (Project entity : entities) {
			ProjectDto dto = new ProjectDto();
			dto.setId(entity.getId());
			dto.setName(entity.getName());
			dto.setStartDate(entity.getStartDate());
			dto.setEndDate(entity.getEndDate());
			
			dtos.add(dto);
		}

		return dtos;
	}

	public ProjectDto getById(int id) {
		Project entity = projectRepository.findById(id);
		ProjectDto dto = new ProjectDto();
		dto.setId(entity.getId());
		dto.setName(entity.getName());
		dto.setStartDate(entity.getStartDate());
		dto.setEndDate(entity.getEndDate());

		return dto;
	}
	
	public int insert(ProjectDto dto) {
		Project entity = new Project();
		entity.setName(dto.getName());
		entity.setStartDate(dto.getStartDate());
		entity.setEndDate(dto.getEndDate());
		
		return projectRepository.save(entity);
	}
	
	public int update(ProjectDto dto) {
		Project entity = projectRepository.findById(dto.getId());
		
		if(entity != null) {
			entity.setId(dto.getId());
			entity.setName(dto.getName());
			entity.setStartDate(dto.getStartDate());
			entity.setEndDate(dto.getEndDate());
			
			return projectRepository.edit(entity);
		}
		
		return -1;
	}
	
	public int deleteById(int id) {
		return projectRepository.deleteById(id);
	}
	
}
