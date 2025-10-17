package com.example.darelo.RH.service;

import com.example.darelo.RH.model.Role;
import com.example.darelo.RH.model.User;
import com.example.darelo.RH.repository.RoleRepository;
import com.example.darelo.RH.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepo;
    private final RoleRepository roleRepo;
    private final PasswordEncoder encoder;


    public UserService(UserRepository userRepo, RoleRepository roleRepo, PasswordEncoder encoder) {
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
        this.encoder = encoder;
    }

    public User creerUser(String fullname, String email, String password, String roleName){
        User user = new User();
        user.setFullname(fullname);
        user.setEmail(email);
        user.setPassword(encoder.encode(password));
        Role role = roleRepo.findByName(roleName).orElseThrow();
        user.getRoles().add(role);
        return userRepo.save(user);
    }

    public List<User> getAllUsers(){
        return userRepo.findAll();
    }
}
