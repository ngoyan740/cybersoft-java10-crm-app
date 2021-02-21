package com.myclass.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.myclass.connection.DbConnection;
import com.myclass.entity.Role;

public class RoleRepository {

	public List<Role> findAll() {
		List<Role> role = new ArrayList<Role>();
		Connection conn = DbConnection.getConnection();

		try {
			PreparedStatement statement = conn.prepareStatement("SELECT * FROM role");
			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {
				Role entity = new Role();
				entity.setId(resultSet.getInt("id"));
				entity.setName(resultSet.getString("name"));
				entity.setDescription(resultSet.getString("description"));

				role.add(entity);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return role;
	}
	
	public int save(Role role) {
		String query = "INSERT INTO role(name, description) VALUES (?, ?)";
		Connection conn = DbConnection.getConnection();
		
		try {
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setString(1, role.getName());
			statement.setString(2, role.getDescription());

			return statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return -1;
	}
	
	public int edit(Role role) {
		String query = "UPDATE role SET name = ?, description = ? WHERE id = ?";
		Connection conn = DbConnection.getConnection();
		
		try {
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setString(1, role.getName());
			statement.setString(2, role.getDescription());
			statement.setInt(3, role.getId()); 
		
			return statement.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return -1;
	} 
	
	public Role findById(int id) {
		String query = "SELECT * FROM role WHERE id = ?";
		Connection conn = DbConnection.getConnection();
		Role entity = null;
		
		try {
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				entity = new Role();
				entity.setId(resultSet.getInt("id"));
				entity.setName(resultSet.getString("name"));
				entity.setDescription(resultSet.getString("description"));
				
				break;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return entity;
	}
	
	public int deleteById(int id) {
		String query = "DELETE FROM role WHERE id = ?";
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
