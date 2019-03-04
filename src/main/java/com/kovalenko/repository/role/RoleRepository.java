package com.kovalenko.repository.role;

import com.kovalenko.entity.user.role.Role;
import com.kovalenko.entity.user.role.RoleType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {

    Role findByRoleName(String roleName);
}
