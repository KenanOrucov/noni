package com.nonilab.model.request;

import com.nonilab.model.enums.UserAuthority;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

import static lombok.AccessLevel.PRIVATE;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = PRIVATE)
public class UserRequest {
    @NotBlank(message = "First name cannot be blank")
    @Size(max = 255, message = "First name cannot be longer than 255 characters")
    String firstName;
    @NotBlank(message = "Last name cannot be blank")
    @Size(max = 255, message = "Last name cannot be longer than 255 characters")
    String lastName;
    @NotBlank(message = "Username cannot be blank")
    @Size(max = 255, message = "Username cannot be longer than 255 characters")
    String username;
    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Invalid email format")
    String mail;
    @NotBlank(message = "Password cannot be blank")
    @Size(min = 8, max = 255, message = "Password must be between 6 and 255 characters")
    String password;
    String gender;
    String language;
    String country;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    Date birthday;

    @NotNull(message = "Role cannot be null")
    UserAuthority role;
    MultipartFile image;
}
