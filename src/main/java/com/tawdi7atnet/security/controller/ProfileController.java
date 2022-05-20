package com.tawdi7atnet.security.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tawdi7atnet.security.entities.ProfileEntity;
import com.tawdi7atnet.security.service.ProfileService;

@RestController
@RequestMapping("api/v1/profiles")
public class ProfileController {
	
	private ProfileService profileService;
	
	public ProfileController(ProfileService profileService) {
		this.profileService = profileService;
	}
	
	@GetMapping
	@PostAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
	public ResponseEntity<List<ProfileEntity>> findAllProfiles(){
		return ResponseEntity.ok(profileService.findAllProfiles());
	}
	
	@PostMapping("add")
	@PostAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
	public ResponseEntity<ProfileEntity> addProfile(@RequestBody ProfileEntity profile){
		return ResponseEntity.status(HttpStatus.CREATED).body(profileService.addProfile(profile));
	}
	
	@PutMapping("update")
	@PostAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
	public ResponseEntity<ProfileEntity> updateProfile(@RequestBody ProfileEntity profile){
		return ResponseEntity.status(HttpStatus.CREATED).body(profileService.updateProfile(profile));
	}
	
	@DeleteMapping("{id}")
	@PostAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
	public ResponseEntity<?> deleteProfileById(@PathVariable Long id){
		profileService.deleteProdileById(id);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("{id}")
	@PostAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
	public ResponseEntity<ProfileEntity> findProfileById(@PathVariable Long id){
		return ResponseEntity.ok(profileService.findProfileById(id));
	}

}
