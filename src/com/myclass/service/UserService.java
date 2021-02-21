package com.myclass.service;

import java.util.List;

import org.mindrot.jbcrypt.BCrypt;

import com.myclass.dto.UserDto;
import com.myclass.entity.User;
import com.myclass.repository.UserRepository;

public class UserService {

	private UserRepository userRepository;

	public UserService() {
		userRepository = new UserRepository();
	}

	public List<UserDto> getAll() {
//		List<UserDto> dtos = new ArrayList<UserDto>();
//		// Gọi hàm truy vấn lấy dữ liệu
//		List<User> entities = userRepository.findAll(); // findAll trả về List<User>
//		// Tham chiếu dữ liệu từ entity -> Dto
//		for (User entity : entities) {
//			UserDto dto = new UserDto();
//			dto.setId(entity.getId());
//			dto.setEmail(entity.getEmail());
//			dto.setPassword(entity.getPassword());
//			dto.setFullname(entity.getFullname());
//			dto.setAvatar(entity.getAvatar());
//			dto.setRoleId(entity.getRoleId());
//			
//			// LẤY RA THÔNG TIN Role DỰA VÀ KHÓA NGOẠI
//			Role role = roleRepository.findById(entity.getRoleId());
//			dto.setRoleDesc(role.getDescription());
//			
//			dtos.add(dto);
//		}
//		// Trả về dto
//		return dtos;
		return userRepository.findAllJoinWithRole();
	}

	public UserDto getById(int id) {
		UserDto dto = new UserDto();
		User entity = userRepository.findById(id);

		if (entity != null) {
			dto.setId(entity.getId());
			dto.setEmail(entity.getEmail());
			dto.setPassword(entity.getPassword());
			dto.setFullname(entity.getFullname());
			dto.setAvatar(entity.getAvatar());
			dto.setRoleId(entity.getRoleId());
		}

		return dto;
	}

	public int insert(UserDto userDto) {
		try {
			String hashed = BCrypt.hashpw(userDto.getPassword(), BCrypt.gensalt());
			User entity = new User(userDto.getEmail(), hashed, userDto.getFullname(), userDto.getAvatar(),
					userDto.getRoleId());

			return userRepository.save(entity);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return -1;
	}

	public int update(UserDto userDto) {
		try {
			User entity = userRepository.findById(userDto.getId());
			if(entity != null) {
				entity.setId(userDto.getId());
				entity.setEmail(userDto.getEmail());
				entity.setFullname(userDto.getFullname());
				entity.setAvatar(userDto.getAvatar());
				entity.setRoleId(userDto.getRoleId());

				if(!userDto.getPassword().isEmpty()) {
					String hashed = BCrypt.hashpw(userDto.getPassword(), BCrypt.gensalt());
					entity.setPassword(hashed);
				}
			}
			
			return userRepository.edit(entity);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return -1;
	}
	
	public int deleteById(int id) {
		return userRepository.deleteById(id);
	}
}
