package com.myclass.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.myclass.connection.DbConnection;
import com.myclass.entity.Status;

public class StatusRepository {

	public List<Status> findAll() {
		List<Status> status = new ArrayList<Status>();
		Connection conn = DbConnection.getConnection();
		
		try {
			PreparedStatement statement = conn.prepareStatement("SELECT * FROM status");
			ResultSet resultSet = statement.executeQuery();
			
			while (resultSet.next()) {
				Status entity = new Status();
				entity.setId(resultSet.getInt("id"));
				entity.setName(resultSet.getString("name"));
				
				status.add(entity);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return status;
	}

	public Status findById(int id) {
		Status entity = null;
		Connection conn = DbConnection.getConnection();
		
		try {
			PreparedStatement statement = conn.prepareStatement("SELECT * FROM status WHERE id = ?");
			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {
				entity = new Status();
				entity.setId(resultSet.getInt("id"));
				entity.setName(resultSet.getString("name"));
				
				break;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return entity;
	}

	public int save(Status entity) {
		String query = "INSERT INTO status(name) VALUES (?)";
		Connection conn = DbConnection.getConnection();

		try {
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setString(1, entity.getName());

			return statement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return -1;
	}

	public int edit(Status entity) {
		String query = "UPDATE status SET name = ? WHERE id  = ?";
		Connection conn = DbConnection.getConnection();
		
		try {
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setString(1, entity.getName());
			statement.setInt(2, entity.getId());

			return statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return -1;
	}

	public Status findByName(String email) {
		String query = "SELECT * FROM status WHERE name = ?";
		Status entity = null;
		Connection conn = DbConnection.getConnection();

		try {
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setString(1, email);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				entity = new Status();
				entity.setId(resultSet.getInt("id"));
				entity.setName(resultSet.getString("name"));
				
				break;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return entity;
	}
	
	public int deleteById(int id) {
		String query = "DELETE FROM status WHERE id = ?";
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
