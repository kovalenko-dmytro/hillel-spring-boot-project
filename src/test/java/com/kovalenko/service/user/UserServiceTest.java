package com.kovalenko.service.user;

import com.kovalenko.DocumentServiceApplicationTests;
import com.kovalenko.entity.user.User;
import com.kovalenko.entity.user.role.Role;
import com.kovalenko.entity.user.role.RoleType;
import com.kovalenko.repository.role.RoleRepository;
import com.kovalenko.repository.user.UserRepository;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Collections;
import java.util.Optional;

import static org.junit.Assert.*;

public class UserServiceTest extends DocumentServiceApplicationTests {

    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;
    @MockBean
    private BCryptPasswordEncoder passwordEncoder;
    @MockBean
    private RoleRepository roleRepository;

    private static User user;

    @BeforeClass
    public static void createUser() {
        user = new User(0L,
                "login",
                "login",
                "login",
                1,
                Collections.emptySet(),
                Collections.emptyList());
    }

    @Test
    public void getUserByLogin_whenUserIsNotExist_shouldReturnNull() {
        String login = "user";
        Mockito.when(userRepository.findByLogin(login)).thenReturn(Optional.empty());
        User foundUser = userService.getUserByLogin(login);
        assertNull(foundUser);
    }

    @Test
    public void getUserByLogin_whenUserIsExist_shouldReturnUser() {
        Mockito.when(userRepository.findByLogin(user.getLogin())).thenReturn(Optional.of(user));

        User foundUser = userService.getUserByLogin(user.getLogin());

        assertNotNull(foundUser);
        assertEquals("login", foundUser.getLogin());
    }

    @Test
    public void register_shouldSaveUser() {
        Mockito.when(passwordEncoder.encode(user.getPassword()))
                .thenCallRealMethod();
        Mockito.when(roleRepository.findByRoleName(RoleType.USER.getRoleName()))
                .thenReturn(new Role(2, "USER"));
        Mockito.when(userRepository.save(user))
                .thenReturn(user);

        userService.register(user);

        Mockito.verify(userRepository, Mockito.times(1)).save(user);
    }
}