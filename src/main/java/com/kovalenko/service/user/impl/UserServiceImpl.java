package com.kovalenko.service.user.impl;

import com.kovalenko.entity.user.User;
import com.kovalenko.entity.user.role.Role;
import com.kovalenko.entity.user.role.RoleType;
import com.kovalenko.repository.role.RoleRepository;
import com.kovalenko.repository.user.UserRepository;
import com.kovalenko.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           BCryptPasswordEncoder passwordEncoder,
                           RoleRepository roleRepository) {

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    @Override
    public User getUserByLogin(String login) {
        return userRepository.findByLogin(login).orElse(null);
    }

    @Override
    public void register(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setEnabled(1);
        Role role = roleRepository.findByRoleName(RoleType.USER.getRoleName());
        user.setRoles(new HashSet<>(Collections.singleton(role)));
        userRepository.save(user);
    }
}
