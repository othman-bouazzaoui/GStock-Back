package com.tawdi7atnet.security.service;

import java.util.List;

import com.tawdi7atnet.security.entities.RoleEntity;

public interface RoleService {
	
	List<RoleEntity> findAllRoles();
	
	RoleEntity findRoleById(Long id);
	
	RoleEntity findByRoleName(String name);
	
	RoleEntity addRole(RoleEntity role);
	
	RoleEntity updateRole(RoleEntity role);
	
	void deleteRoleById(Long id);

}
