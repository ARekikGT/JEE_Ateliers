package com.tn.atelier.ahmed_rekik.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tn.atelier.ahmed_rekik.Entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
	Role findByRole(String role);

}
