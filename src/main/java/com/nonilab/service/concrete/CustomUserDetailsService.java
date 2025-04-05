package com.nonilab.service.concrete;

import com.nonilab.aop.annotation.LogIgnore;
import com.nonilab.dao.repository.UserRepository;
import com.nonilab.model.userdetails.CustomGrantedAuthority;
import com.nonilab.model.userdetails.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

import static lombok.AccessLevel.PRIVATE;

@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = PRIVATE)
public class CustomUserDetailsService implements UserDetailsService {
    UserRepository userRepository;

    @LogIgnore
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        var user = userRepository.findByMail(email).orElseThrow();
        var customGrantedAuthorities = user.getAuthorities().stream().map(CustomGrantedAuthority::new).collect(Collectors.toSet());
        return new CustomUserDetails(user, customGrantedAuthorities);
    }
}