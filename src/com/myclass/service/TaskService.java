package com.myclass.service;

import java.util.List;

import com.myclass.dto.TaskDto;
import com.myclass.entity.Task;
import com.myclass.repository.TaskRepository;

public class TaskService {
	
	private TaskRepository taskRepository;
	
	public TaskService() {
		taskRepository = new TaskRepository();
	}
	
	public List<TaskDto> getAll() {
		return taskRepository.findAllJoinWithStatusUserProject();
	}

	public TaskDto getById(int id) {
		Task entity = taskRepository.findById(id);
		TaskDto dto = new TaskDto();
		dto.setId(entity.getId());
		dto.setName(entity.getName());
		dto.setStartDate(entity.getStartDate());
		dto.setEndDate(entity.getEndDate());
		
		dto.setStatusId(entity.getStatusId());
		dto.setUserId(entity.getUserId());
		dto.setProjectId(entity.getProjectId());

		return dto;
	}
	
	public int insert(TaskDto dto) {
		Task entity = new Task();
		entity.setName(dto.getName());
		entity.setStartDate(dto.getStartDate());
		entity.setEndDate(dto.getEndDate());
		
		entity.setStatusId(dto.getStatusId());
		entity.setUserId(dto.getUserId());
		entity.setProjectId(dto.getProjectId());
		
		return taskRepository.save(entity);
	}
	
	public int update(TaskDto dto) {
		Task entity = taskRepository.findById(dto.getId());
		
		if(entity != null) {
			entity.setId(dto.getId());
			entity.setName(dto.getName());
			entity.setStartDate(dto.getStartDate());
			entity.setEndDate(dto.getEndDate());
			
			entity.setStatusId(dto.getStatusId());
			entity.setUserId(dto.getUserId());
			entity.setProjectId(dto.getProjectId());
			
			return taskRepository.edit(entity);
		}
		
		return -1;
	}
	
	public int deleteById(int id) {
		return taskRepository.deleteById(id);
	}
	
}
