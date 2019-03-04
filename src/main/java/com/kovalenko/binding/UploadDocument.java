package com.kovalenko.binding;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class UploadDocument {

    @NotNull
    private MultipartFile file;
    @NotNull
    @Size(min = 1)
    private String description;
}
