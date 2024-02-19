package com.app.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.entities.AdminEntity;

@Repository
public interface AdminDAO extends JpaRepository<AdminEntity, Long>{

	Optional<AdminEntity> getByUserNameAndPassword(String userName, String password);

}
