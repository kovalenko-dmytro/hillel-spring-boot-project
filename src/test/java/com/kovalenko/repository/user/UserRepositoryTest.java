package com.kovalenko.repository.user;

import com.kovalenko.DocumentServiceApplicationTests;
import com.kovalenko.entity.user.User;
import com.kovalenko.entity.user.role.Role;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;

import static org.junit.Assert.*;

public class UserRepositoryTest extends DocumentServiceApplicationTests {

    @Autowired
    private UserRepository userRepository;

    private static User user;

    @BeforeClass
    public static void createUser() {
        user = new User(0L,
                "aaa",
                "aaa",
                "aaa",
                1,
                Collections.emptySet(),
                Collections.emptyList());
    }

    @Before
    public void clear() {
        userRepository.deleteAll();
    }

    @Test
    public void save_whenUserSaved_ShouldReturnUser() {
        User savedUser = userRepository.save(user);
        assertNotNull(savedUser);
    }

    @Test
    public void findByLogin_whenUserNotExist_ShouldReturnNull() {
        User user = userRepository.findByLogin("aaa").orElse(null);
        assertNull(user);
    }

    @Test
    public void findByLogin_whenUserIsExist_ShouldReturnUser() {
        userRepository.save(user);
        User user = userRepository.findByLogin("aaa").orElse(null);
        assertNotNull(user);
    }
}