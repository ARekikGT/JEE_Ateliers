package com.tn.atelier.ahmed_rekik.Service;

import java.util.List;

import com.tn.atelier.ahmed_rekik.Entity.Role;
import com.tn.atelier.ahmed_rekik.Entity.User;

public interface UserService {
	User saveUser(User user);
	User findUserByUsername(String username);
	Role addRole(Role role);
	User addRoleToUser(String username, String rolename);
	List<User> findAllUsers();

}
