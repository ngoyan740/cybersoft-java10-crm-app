package com.myclass.repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.myclass.connection.DbConnection;
import com.myclass.dto.TaskDto;
import com.myclass.entity.Task;

public class TaskRepository {
	public List<TaskDto> findAllJoinWithStatusUserProject() {
		String query = "SELECT * FROM task t JOIN status s ON t.status_id = s.id "
				+ "JOIN user u ON t.user_id = u.id "
				+ "JOIN project p ON t.project_id = p.id";

		List<TaskDto> taskDtos = new ArrayList<TaskDto>();
		Connection conn = DbConnection.getConnection();
		
		try {
			PreparedStatement statement = conn.prepareStatement(query);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				TaskDto dto = new TaskDto();
				dto.setId(resultSet.getInt("id"));
				dto.setName(resultSet.getString("name"));
				dto.setStartDate(resultSet.getDate("start_date"));
				dto.setEndDate(resultSet.getDate("end_date"));
				
				dto.setStatusId(resultSet.getInt("status_id"));
				dto.setUserId(resultSet.getInt("user_id"));
				dto.setProjectId(resultSet.getInt("project_id"));
				
				dto.setStatusName(resultSet.getString("s.name"));
				dto.setUserName(resultSet.getString("u.fullname"));
				dto.setProjectName(resultSet.getString("p.name"));
				
				taskDtos.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return taskDtos;
	}

	public List<Task> findAll() {
		List<Task> task = new ArrayList<Task>();
		Connection conn = DbConnection.getConnection();
		
		try {
			PreparedStatement statement = conn.prepareStatement("SELECT * FROM task");
			ResultSet resultSet = statement.executeQuery();
			
			while (resultSet.next()) {
				Task entity = new Task();
				entity.setId(resultSet.getInt("id"));
				entity.setName(resultSet.getString("name"));
				entity.setStartDate(resultSet.getDate("start_date"));
				entity.setEndDate(resultSet.getDate("end_date"));
				
				entity.setStatusId(resultSet.getInt("status_id"));
				entity.setUserId(resultSet.getInt("user_id"));
				entity.setProjectId(resultSet.getInt("project_id"));
				
				task.add(entity);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return task;
	}

	public Task findById(int id) {
		Task entity = null;
		Connection conn = DbConnection.getConnection();
		
		try {
			PreparedStatement statement = conn.prepareStatement("SELECT * FROM task WHERE id = ?");
			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {
				entity = new Task();
				entity.setId(resultSet.getInt("id"));
				entity.setName(resultSet.getString("name"));
				entity.setStartDate(resultSet.getDate("start_date"));
				entity.setEndDate(resultSet.getDate("end_date"));
				
				entity.setStatusId(resultSet.getInt("status_id"));
				entity.setUserId(resultSet.getInt("user_id"));
				entity.setProjectId(resultSet.getInt("project_id"));
				
				break;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return entity;
	}

	public int save(Task entity) {
		String query = "INSERT INTO task(name, start_date, end_date, status_id, user_id, project_id)"
				+ " VALUES (?, ?, ?, ?, ?, ?)";
		Connection conn = DbConnection.getConnection();

		try {
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setString(1, entity.getName());
			statement.setDate(2, new Date(entity.getStartDate().getTime()));
			statement.setDate(3, new Date(entity.getEndDate().getTime()));
			
			statement.setInt(4, entity.getStatusId());
			statement.setInt(5, entity.getUserId());
			statement.setInt(6, entity.getProjectId());

			return statement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return -1;
	}

	public int edit(Task entity) {
		String query = "UPDATE task SET name = ?, start_date = ?, end_date = ?,"
				+ " status_id = ?, user_id = ?, project_id = ? "
				+ "WHERE id  = ?";
		Connection conn = DbConnection.getConnection();
		
		try {
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setString(1, entity.getName());
			statement.setDate(2, new Date(entity.getStartDate().getTime()));
			statement.setDate(3, new Date(entity.getEndDate().getTime()));
			
			statement.setInt(4, entity.getStatusId());
			statement.setInt(5, entity.getUserId());
			statement.setInt(6, entity.getProjectId());
			
			statement.setInt(7, entity.getId());

			return statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return -1;
	}

	public Task findByName(String email) {
		String query = "SELECT * FROM task WHERE name = ?";
		Task entity = null;
		Connection conn = DbConnection.getConnection();

		try {
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setString(1, email);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				entity = new Task();
				entity.setId(resultSet.getInt("id"));
				entity.setName(resultSet.getString("name"));
				entity.setStartDate(resultSet.getDate("start_date"));
				entity.setEndDate(resultSet.getDate("end_date"));
				
				entity.setStatusId(resultSet.getInt("status_id"));
				entity.setUserId(resultSet.getInt("user_id"));
				entity.setProjectId(resultSet.getInt("project_id"));
				
				break;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return entity;
	}
	
	public int deleteById(int id) {
		String query = "DELETE FROM task WHERE id = ?";
		Connection conn = DbConnection.getConnection();
		
		try {
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setInt(1, id);
			return statement.executeUpdate();	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return -1;
	}
}
