package com.myclass.service;

import org.mindrot.jbcrypt.BCrypt;

import com.myclass.dto.UserDto;
import com.myclass.entity.Role;
import com.myclass.entity.User;
import com.myclass.repository.RoleRepository;
import com.myclass.repository.UserRepository;

public class AuthService {
	
	private UserRepository userRepository;
	private RoleRepository roleRepository;
	
	public AuthService() {
		userRepository = new UserRepository();
		roleRepository = new RoleRepository();
	}

	public UserDto login(String email, String password) {
		User user = userRepository.findByEmail(email);
		
		if(user == null) return null;
		if(!BCrypt.checkpw(password, user.getPassword())) {
			return null;
		}
		
		UserDto dto = new UserDto(
				user.getId(),
				user.getEmail(),
				user.getPassword(),
				user.getFullname(),
				user.getAvatar(),
				user.getRoleId()
			);
		
		Role role = roleRepository.findById(user.getRoleId());
		dto.setRoleName(role.getName());
		dto.setRoleDesc(role.getDescription());
		
		return dto;
	}
}
