package com.example.eshop.service.impl;

import com.example.eshop.entity.User;
import com.example.eshop.repository.UserRepository;
import com.example.eshop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final SendMailService sendMailService;

    @Override
    public User register(User user) {
        String activationToken = UUID.randomUUID().toString();
        user.setActive(false);
        user.setToken(activationToken);
        User save = userRepository.save(user);
//        String verifyUrl = "http://localhost:8081/user/verify?token=" + activationToken;
//        sendMailService.send(user.getEmail(), "Welcome",
//                String.format("Welcome %s . You have successfully registered to our website!!!." +
//                                "Please open %s for activating your account!",
//                        user.getName(), verifyUrl));
        sendMailService.sendWelcomeMail(user);
        return save;
    }

    public User save(User user) {
       return userRepository.save(user);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }

    @Override
    public User findByToken(String token) {
        return userRepository.findByToken(token).orElse(null);
    }
}
