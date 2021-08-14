package com.lawrence.service.impl;


import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lawrence.Exception.UserAlreadyExistException;
import com.lawrence.model.UserEntity;
import com.lawrence.repository.UserRepo;
import com.lawrence.service.UserService;
import com.lawrence.web.dto.UserData;

@Service("userService")
public class DefaultUserService implements UserService {

    @Autowired
    private UserRepo userRepository;


    @Override
    public UserEntity register(UserData user) throws UserAlreadyExistException {
        if (checkIfUserExist(user.getEmail())) {
            throw new UserAlreadyExistException("User already exists for this email");
        }
        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(user, userEntity);
        return userRepository.save(userEntity);

    }


    @Override
    public boolean checkIfUserExist(String email) {
        return userRepository.findByEmail(email) != null ? true : false;
    }

    @Override
    public boolean verifyUser(String username, String password) {

        UserEntity user = userRepository.findByEmail(username);
        if (user == null) {
            throw new RuntimeException("User does not exist.");
        }
        if (!user.getPassword().equals(password)) {
            throw new RuntimeException("Password mismatch.");
        }
        return true;
    }

    @Override
    public String changePassword(String email, String oldPassword, String newPassword) {
        boolean userVerified = verifyUser(email, oldPassword);
        if (userVerified) {
            UserEntity user = userRepository.findByEmail(email);
            user.setPassword(newPassword);
            userRepository.save(user);
            return "Password updated successfully";
        }
        return "Invalid credentials";
    }

    @Override
    public String resetPassword(String email) {
        UserEntity user = userRepository.findByEmail(email);
        if (user != null) {
            user.setPassword("test123");
            userRepository.save(user);
            return "Your temporary password 'test123', Kinldy reset before continuing";
        }
        return "Wrong username";
    }


}
