package com.kokwai.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.*;
import static com.kokwai.constant.UserConstant.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@JsonInclude(NON_DEFAULT)
public class User {

    private Long id;

    @NotEmpty(message = FIRST_NAME_NOT_EMPTY_CONSTANT)
    private String firstName;

    @NotEmpty(message = LAST_NAME_NOT_EMPTY_CONSTANT)
    private String lastName;

    @NotEmpty(message = EMAIL_NOT_EMPTY_CONSTANT)
    @Email(message = EMAIL_INVALID_FORMAT_CONSTANT)
    private String email;

    @NotEmpty(message = PASSWORD_NOT_EMPTY_CONSTANT)
    private String password;
    private String address;
    private String phone;
    private String title;
    private String bio;
    private Boolean enabled;
    private Boolean nonLocked;
    private Boolean usingMfa;
    private LocalDateTime createdAt;
    private String imageUrl;

}
