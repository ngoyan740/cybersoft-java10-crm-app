package com.myclass.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.myclass.connection.DbConnection;
import com.myclass.dto.UserDto;
import com.myclass.entity.User;

public class UserRepository {

	public List<UserDto> findAllJoinWithRole() {
		String query = "SELECT * FROM user u JOIN role r ON u.role_id = r.id";
		List<UserDto> userDtos = new ArrayList<UserDto>();
		Connection conn = DbConnection.getConnection();
		
		try {
			PreparedStatement statement = conn.prepareStatement(query);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				UserDto dto = new UserDto();
				dto.setId(resultSet.getInt("id"));
				dto.setEmail(resultSet.getString("email"));
				dto.setPassword(resultSet.getString("password"));
				dto.setFullname(resultSet.getString("fullname"));
				dto.setAvatar(resultSet.getString("avatar"));
				dto.setRoleId(resultSet.getInt("role_id"));
				dto.setRoleDesc(resultSet.getString("r.description"));
				dto.setRoleName(resultSet.getString("r.name"));
				
				userDtos.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return userDtos;
	}

	public List<User> findAll() {
		List<User> user = new ArrayList<User>();
		Connection conn = DbConnection.getConnection();
		
		try {
			PreparedStatement statement = conn.prepareStatement("SELECT * FROM user");
			ResultSet resultSet = statement.executeQuery();
			
			while (resultSet.next()) {
				User entity = new User();
				entity.setId(resultSet.getInt("id"));
				entity.setEmail(resultSet.getString("email"));
				entity.setPassword(resultSet.getString("password"));
				entity.setFullname(resultSet.getString("fullname"));
				entity.setAvatar(resultSet.getString("avatar"));
				entity.setRoleId(resultSet.getInt("role_id"));
				
				user.add(entity);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return user;
	}

	public User findById(int id) {
		User entity = null;
		Connection conn = DbConnection.getConnection();
		
		try {
			PreparedStatement statement = conn.prepareStatement("SELECT * FROM user WHERE id = ?");
			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {
				entity = new User();
				entity.setId(resultSet.getInt("id"));
				entity.setEmail(resultSet.getString("email"));
				entity.setPassword(resultSet.getString("password"));
				entity.setFullname(resultSet.getString("fullname"));
				entity.setAvatar(resultSet.getString("avatar"));
				entity.setRoleId(resultSet.getInt("role_id"));
				
				break;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return entity;
	}

	public int save(User entity) {
		String query = "INSERT INTO user(email, password, fullname, avatar, role_id) VALUES (?, ?, ?, ?, ?)";
		Connection conn = DbConnection.getConnection();
		
		try {
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setString(1, entity.getEmail());
			statement.setString(2, entity.getPassword());
			statement.setString(3, entity.getFullname());
			statement.setString(4, entity.getAvatar());
			statement.setInt(5, entity.getRoleId());

			return statement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return -1;
	}

	public int edit(User entity) {
		String query = "UPDATE user SET email = ?, password = ?, fullname = ?, avatar = ?, role_id = ? WHERE id  = ?";
		Connection conn = DbConnection.getConnection();
		
		try {
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setString(1, entity.getEmail());
			statement.setString(2, entity.getPassword());
			statement.setString(3, entity.getFullname());
			statement.setString(4, entity.getAvatar());
			statement.setInt(5, entity.getRoleId());
			statement.setInt(6, entity.getId());

			return statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return -1;
	}

	public User findByEmail(String email) {
		String query = "SELECT * FROM user WHERE email = ?";
		User entity = null;
		Connection conn = DbConnection.getConnection();

		try {
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setString(1, email);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				entity = new User();
				entity.setId(resultSet.getInt("id"));
				entity.setEmail(resultSet.getString("email"));
				entity.setPassword(resultSet.getString("password"));
				entity.setFullname(resultSet.getString("fullname"));
				entity.setAvatar(resultSet.getString("avatar"));
				entity.setRoleId(resultSet.getInt("role_id"));
				
				break;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return entity;
	}
	
	public int deleteById(int id) {
		String query = "DELETE FROM user WHERE id = ?";
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
