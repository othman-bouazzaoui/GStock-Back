package com.tawdi7atnet.security.service;

import java.util.List;

import com.tawdi7atnet.security.entities.UserEntity;

public interface UserService {
	
	List<UserEntity> findAllUsers();
	
	UserEntity findUserById(Long id);
	
	UserEntity findUserByUserName(String u);
		
	UserEntity addUser(UserEntity u);
	
	UserEntity updateUser(UserEntity u);
	
	void deleteUserById(Long id);
	
	UserEntity addRoleToUser(String username, String roleName);

}
