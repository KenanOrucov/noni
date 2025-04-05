package com.nonilab.service.abstraction;

import com.nonilab.dao.entity.UserEntity;
import com.nonilab.model.enums.UserAuthority;
import com.nonilab.model.request.UserRequest;
import com.nonilab.model.response.UserResponse;
import org.springframework.web.multipart.MultipartFile;

public interface UserService {
    UserResponse getUserById(Long id);
    void createUser(UserRequest userRequest);
    UserEntity getUserByEmail(String username);
    void updateUser(Long id, UserRequest userRequest);
    void addAuthority(Long id, UserAuthority role);
    void deleteUser(Long id);
}
