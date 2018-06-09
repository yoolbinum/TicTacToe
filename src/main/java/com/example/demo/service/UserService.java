package com.example.demo.service;

import com.example.demo.model.AppUser;
import com.example.demo.model.Role;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
public class UserService {
    final UserRepository userRepository;

    final RoleRepository roleRepository;

    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public AppUser findByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

    public void saveNewUser(AppUser user) {
        HashSet<Role> hash = new HashSet<>();
        hash.add(roleRepository.findByRole("USER"));
        user.setRoles(hash);
        userRepository.save(user);
    }

    public void saveUser(AppUser user) {
        userRepository.save(user);
    }

    public void addRole(AppUser user, String roleName) {
        Role role = roleRepository.findByRole(roleName);
        user.addRole(role);
    }


}