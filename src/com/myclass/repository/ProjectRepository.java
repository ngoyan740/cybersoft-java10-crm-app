package com.myclass.repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.myclass.connection.DbConnection;
import com.myclass.entity.Project;

public class ProjectRepository {
	
	public List<Project> findAll() {
		List<Project> project = new ArrayList<Project>();
		Connection conn = DbConnection.getConnection();
		
		try {
			PreparedStatement statement = conn.prepareStatement("SELECT * FROM project");
			ResultSet resultSet = statement.executeQuery();
			
			while (resultSet.next()) {
				Project entity = new Project();
				entity.setId(resultSet.getInt("id"));
				entity.setName(resultSet.getString("name"));
				entity.setStartDate(resultSet.getDate("start_date"));
				entity.setEndDate(resultSet.getDate("end_date"));
				
				project.add(entity);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return project;
	}

	public Project findById(int id) {
		Project entity = null;
		Connection conn = DbConnection.getConnection();
		
		try {
			PreparedStatement statement = conn.prepareStatement("SELECT * FROM project WHERE id = ?");
			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {
				entity = new Project();
				entity.setId(resultSet.getInt("id"));
				entity.setName(resultSet.getString("name"));
				entity.setStartDate(resultSet.getDate("start_date"));
				entity.setEndDate(resultSet.getDate("end_date"));
				
				break;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return entity;
	}

	public int save(Project entity) {
		String query = "INSERT INTO project(name, start_date, end_date) VALUES (?, ?, ?)";
		Connection conn = DbConnection.getConnection();

		try {
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setString(1, entity.getName());
			statement.setDate(2, new Date(entity.getStartDate().getTime()));
			statement.setDate(3, new Date(entity.getEndDate().getTime()));

			return statement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return -1;
	}

	public int edit(Project entity) {
		String query = "UPDATE project SET name = ?, start_date = ?, end_date = ? WHERE id  = ?";
		Connection conn = DbConnection.getConnection();
		
		try {
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setString(1, entity.getName());
			statement.setDate(2, new Date(entity.getStartDate().getTime()));
			statement.setDate(3, new Date(entity.getEndDate().getTime()));
			statement.setInt(4, entity.getId());

			return statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return -1;
	}

	public Project findByName(String email) {
		String query = "SELECT * FROM project WHERE name = ?";
		Project entity = null;
		Connection conn = DbConnection.getConnection();

		try {
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setString(1, email);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				entity = new Project();
				entity.setId(resultSet.getInt("id"));
				entity.setName(resultSet.getString("name"));
				entity.setStartDate(resultSet.getDate("start_date"));
				entity.setEndDate(resultSet.getDate("end_date"));
				
				break;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return entity;
	}
	
	public int deleteById(int id) {
		String query = "DELETE FROM project WHERE id = ?";
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
