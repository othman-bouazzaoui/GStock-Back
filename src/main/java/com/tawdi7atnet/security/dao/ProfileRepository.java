package com.tawdi7atnet.security.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tawdi7atnet.security.entities.ProfileEntity;

@Repository
public interface ProfileRepository extends JpaRepository<ProfileEntity, Long>{

}
