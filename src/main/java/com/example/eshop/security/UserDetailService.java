package com.example.eshop.security;


import com.example.eshop.entity.User;
import com.example.eshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class UserDetailService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User byEmail = userService.findByEmail(username);

        if (byEmail==null) {
            throw new UsernameNotFoundException("user with " + username + " dose not exists");
        }

            return new SpringUser(byEmail);
    }
}
