package com.tn.atelier.ahmed_rekik;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.tn.atelier.ahmed_rekik.Entity.Role;
import com.tn.atelier.ahmed_rekik.Entity.User;
import com.tn.atelier.ahmed_rekik.Service.UserService;

import jakarta.annotation.PostConstruct;

@SpringBootApplication
public class ahmed_rekikApplication {
	
	private final UserService userService;

	public static void main(String[] args) {
		SpringApplication.run(ahmed_rekikApplication.class, args);
	}
	
	public ahmed_rekikApplication(UserService userService) {
        this.userService = userService;
    }
	
	@PostConstruct
	void init_users() {
		// Ajouter les rôles uniquement s'ils n'existent pas
	    if (userService.findUserByUsername("admin") == null) {
	        userService.addRole(new Role(null, "ADMIN"));
	        userService.addRole(new Role(null, "USER"));

	        // Ajouter les utilisateurs
	        userService.saveUser(new User(null, "admin", "123", true, null));
	        userService.saveUser(new User(null, "emna", "123", true, null));
	        userService.saveUser(new User(null, "mohamed", "123", true, null));

	        // Ajouter les rôles aux utilisateurs
	        userService.addRoleToUser("admin", "ADMIN");
	        userService.addRoleToUser("admin", "USER");
	        userService.addRoleToUser("emna", "USER");
	        userService.addRoleToUser("mohamed", "USER");
	    }
	}
	
	@Bean
	public static BCryptPasswordEncoder getBCE() {
		return new BCryptPasswordEncoder();
	}

}
