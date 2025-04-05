package com.nonilab.service.concrete;

import com.nonilab.aop.annotation.Log;
import com.nonilab.aop.annotation.LogIgnore;
import com.nonilab.dao.entity.UserEntity;
import com.nonilab.dao.repository.UserRepository;
import com.nonilab.exception.NotFoundException;
import com.nonilab.model.enums.UserAuthority;
import com.nonilab.model.request.UserRequest;
import com.nonilab.model.response.UserResponse;
import com.nonilab.service.abstraction.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;

import static com.nonilab.mapper.RoleMapper.ROLE_MAPPER;
import static com.nonilab.mapper.UserMapper.USER_MAPPER;
import static com.nonilab.model.enums.ExceptionMessages.USER_NOT_FOUND;
import static com.nonilab.model.enums.UserStatus.DELETED;


@Log
@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceHandler implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private final Path root = Paths.get("static/image");

    @Override
    @LogIgnore
    public UserResponse getUserById(Long id) {
        var user =  fetchUserIfExists(id);
        return USER_MAPPER.toUserResponse(user);
    }

    @LogIgnore
    public UserEntity getUserByEmail(String mail) {
        return fetchIfExist(mail);
    }

    @Override
    @LogIgnore
    public void createUser(UserRequest userRequest) {
        var user = getUserEntity(userRequest);
        calculateUserAge(user);
        user.setProfile_picture(saveImage(userRequest.getImage()));
        userRepository.save(user);
    }

    @Override
    @LogIgnore
    public void updateUser(Long id, UserRequest userRequest) {
        var authority = ROLE_MAPPER.toRoleEntity(userRequest.getRole().name());
        var user = fetchUserIfExists(id);
        USER_MAPPER.updateUser(userRequest, user, authority);
        authority.setUser(user);
        calculateUserAge(user);
        user.setProfile_picture(saveImage(userRequest.getImage()));
        userRepository.save(user);
    }

    @Override
    @LogIgnore
    public void addAuthority(Long id, UserAuthority role) {
        var authority = ROLE_MAPPER.toRoleEntity(role.name());
        var user = fetchUserIfExists(id);
        USER_MAPPER.addAuthority(user, authority);
        authority.setUser(user);
        userRepository.save(user);
    }

    @Override
    @LogIgnore
    public void deleteUser(Long id) {
        var user = fetchUserIfExists(id);
        user.setUserStatus(DELETED);
        deleteImage(user.getProfile_picture());
        userRepository.save(user);
    }

    private UserEntity fetchUserIfExists(Long id) {
        return userRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(USER_NOT_FOUND.getMessage(), USER_NOT_FOUND.getCode()));
    }

    private UserEntity fetchIfExist(String email) {
        return userRepository.findByMail(email)
                .orElseThrow(() -> new NotFoundException(USER_NOT_FOUND.getMessage(), USER_NOT_FOUND.getCode()));
    }

    private void deleteImage(String imageUrl) {
        try {
            String filename = imageUrl.substring(imageUrl.lastIndexOf("/") + 1);
            System.out.printf("filename: %s\n", filename);

            filename = URLDecoder.decode(filename, StandardCharsets.UTF_8);
            System.out.printf("filename2: %s\n", filename);

            Path file = root.resolve(filename);

            if (Files.exists(file)) {
                Files.delete(file);
                System.out.println("File deleted: " + file.toAbsolutePath());
            } else {
                System.out.println("File not found: " + file.toAbsolutePath());
            }
        } catch (IOException e) {
            // Log or handle the exception explicitly
            System.err.println("Failed to delete file: " + e.getMessage());
        }
    }

    private UserEntity getUserEntity(UserRequest userRequest) {
        var authority = ROLE_MAPPER.toRoleEntity(userRequest.getRole().name());
        var user = USER_MAPPER.toUserEntity(userRequest, authority);
        authority.setUser(user);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return user;
    }

    private void calculateUserAge(UserEntity user) {
        LocalDate birthDate = user.getBirthday().toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();

        user.setAge(Period.between(birthDate, LocalDate.now()).getYears());
    }

    private String saveImage(MultipartFile file) {
        var fileName = file.getOriginalFilename();

        try{
            if (Files.exists(root.resolve(file.getOriginalFilename()))) {
                String[] parts = fileName.split("\\.");
                fileName = parts[0] + System.currentTimeMillis() + "." + parts[1];
                log.debug(fileName);
            }

            Files.copy(file.getInputStream(), root.resolve(fileName));
            return ServletUriComponentsBuilder.fromCurrentContextPath().path("/v1/users/" + fileName).toUriString();
        }
        catch (IOException ex){
            ex.printStackTrace();
            return "File upload failed: " + ex.getMessage();
        }
    }


}
