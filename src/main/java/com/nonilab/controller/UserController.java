package com.nonilab.controller;

import com.nonilab.model.request.UserRequest;
import com.nonilab.model.response.UserResponse;
import com.nonilab.service.abstraction.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@RestController
@RequestMapping("/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @GetMapping("/{id}")
    public UserResponse getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @GetMapping(value = "image/{image}", produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] getImage(@PathVariable String image) throws IOException {
        return Files.readAllBytes(Paths.get("static/image/" + image));
    }

    @PostMapping(value = "register", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void saveUser(@ModelAttribute @Valid UserRequest userRequest) {
        userService.createUser(userRequest);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PutMapping("/{id}")
    public void updateUser(@PathVariable Long id, @ModelAttribute @Valid UserRequest userRequest) {
        userService.updateUser(id, userRequest);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}
