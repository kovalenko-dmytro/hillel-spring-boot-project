package com.kovalenko.binding;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class LoginForm {

    @NotNull
    @Size(min = 3)
    private String login;
    @NotNull
    @Size(min = 3)
    private String password;
}
