package com.tawdi7atnet.security.service;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;

import com.tawdi7atnet.security.dao.ProfileRepository;
import com.tawdi7atnet.security.entities.ProfileEntity;

@Service
public class ProfileServiceImpl implements ProfileService{
	
	private ProfileRepository profileRepository;
	
	public ProfileServiceImpl(ProfileRepository profileRepository) {
		this.profileRepository = profileRepository;
	}

	@Override
	public List<ProfileEntity> findAllProfiles() {
		return profileRepository.findAll();
	}

	@Override
	public ProfileEntity findProfileById(Long id) {
		return profileRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Profile Id '" + id + "' Not Found"));
	}

	@Override
	public ProfileEntity addProfile(ProfileEntity profile) {
		return profileRepository.save(profile);
	}

	@Override
	public ProfileEntity updateProfile(ProfileEntity profile) {
		findProfileById(profile.getId());
		return profileRepository.save(profile);
	}

	@Override
	public void deleteProdileById(Long id) {
		findProfileById(id);
		profileRepository.deleteById(id);
	}

}
