package com.lawrence.service;


import com.lawrence.Exception.UserAlreadyExistException;
import com.lawrence.model.UserEntity;
import com.lawrence.web.dto.UserData;

public interface UserService {

    UserEntity register(final UserData user) throws UserAlreadyExistException;
    boolean checkIfUserExist(final String email);
    boolean verifyUser(final String username, final String password);
//    UserEntity getUserById(final String id) throws UnkownIdentifierException;
   String changePassword(String email, String oldPassword, String newPassword);
    String resetPassword(String email);
}
