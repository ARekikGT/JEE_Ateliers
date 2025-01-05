package com.tn.atelier.ahmed_rekik.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tn.atelier.ahmed_rekik.Entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	User findByUsername(String username);

}
