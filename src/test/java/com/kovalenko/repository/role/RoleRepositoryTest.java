package com.kovalenko.repository.role;

import com.kovalenko.DocumentServiceApplicationTests;
import com.kovalenko.entity.user.role.Role;
import com.kovalenko.entity.user.role.RoleType;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

public class RoleRepositoryTest extends DocumentServiceApplicationTests {

    @Autowired
    private RoleRepository roleRepository;

    @Before
    public void clear() {
        roleRepository.deleteAll();

    }

    @Test
    public void findByRoleName_whenRoleNotExist_ShouldReturnNull() {
        Role role = roleRepository.findByRoleName(RoleType.USER.getRoleName());
        assertNull(role);
    }

    @Test
    public void findByRoleName_whenRoleAreExist_ShouldReturnRole() {
        roleRepository.save(new Role(0L, "USER"));
        Role role = roleRepository.findByRoleName(RoleType.USER.getRoleName());
        assertNotNull(role);
    }
}