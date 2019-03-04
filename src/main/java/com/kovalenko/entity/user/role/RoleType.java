package com.kovalenko.entity.user.role;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum RoleType {

    ADMIN("ADMIN"),
    USER("USER");

    private String roleName;
}
