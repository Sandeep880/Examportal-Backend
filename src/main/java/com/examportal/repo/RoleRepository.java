package com.examportal.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.examportal.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

}
