package com.tawdi7atnet.security.service;

import java.util.List;

import com.tawdi7atnet.security.entities.ProfileEntity;

public interface ProfileService {
	
	List<ProfileEntity> findAllProfiles();
	
	ProfileEntity findProfileById(Long id);
	
	ProfileEntity addProfile(ProfileEntity profile);
	
	ProfileEntity updateProfile(ProfileEntity profile);
	
	void deleteProdileById(Long id);

}
