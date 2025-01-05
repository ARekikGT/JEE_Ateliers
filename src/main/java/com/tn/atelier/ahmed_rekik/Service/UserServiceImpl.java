package com.tn.atelier.ahmed_rekik.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tn.atelier.ahmed_rekik.Entity.Role;
import com.tn.atelier.ahmed_rekik.Entity.User;
import com.tn.atelier.ahmed_rekik.Repository.RoleRepository;
import com.tn.atelier.ahmed_rekik.Repository.UserRepository;

@Transactional
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRep;

    @Autowired
    RoleRepository roleRep;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder; // pour crypter le mot de passe

    @Override
    public User saveUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userRep.save(user);
    }

    @Override
    public User addRoleToUser(String username, String rolename) {
        User usr = userRep.findByUsername(username);
        Role r = roleRep.findByRole(rolename);
        usr.getRoles().add(r);
        return usr;
    }

    @Override
    public Role addRole(Role role) {
        return roleRep.save(role);
    }

    @Override
    public User findUserByUsername(String username) {
        return userRep.findByUsername(username);
    }

    @Override
    public List<User> findAllUsers() {
        return userRep.findAll();
    }
}
