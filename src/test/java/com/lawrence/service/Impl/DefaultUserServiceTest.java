package com.lawrence.service.Impl;

import com.lawrence.Exception.UserAlreadyExistException;
import com.lawrence.model.UserEntity;
import com.lawrence.repository.UserRepo;
import com.lawrence.service.UserService;
import com.lawrence.service.impl.DefaultUserService;
import com.lawrence.web.dto.UserData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;


import static java.util.Optional.empty;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@SpringBootTest
class DefaultUserServiceTest {
    @InjectMocks
    DefaultUserService userService;

    @Mock
    UserRepo userRepo;
@BeforeEach
public void init()
{
    MockitoAnnotations.initMocks(this);
}

    @Test
    void registerAssertThrowsUserAlreadyExistException() throws UserAlreadyExistException {
        UserData user = new UserData();
        user.setEmail("sa@gmail.com");
        user.setFirstName("sa");
        user.setLastName("la");
        user.setPassword("Sa@123");

        UserEntity userEntity = new UserEntity();
        userEntity.setEmail("sa@gmail.com");
        userEntity.setFirstName("sa");
        userEntity.setLastName("la");
        userEntity.setPassword("Sa@123");
//        when(userService.register(user)).thenThrow(UserAlreadyExistException.class);
        given(userRepo.findByEmail(user.getEmail())).willReturn(userEntity);
//        when(userService.checkIfUserExist("sa@gmail.com")).thenReturn(true);

        assertThrows(UserAlreadyExistException.class, ()->{userService.register(user);});
       verify( userRepo,never()).save(any(UserEntity.class));
    }

    @Test
    void registerSuccessfully() throws UserAlreadyExistException {
        UserData user = new UserData();
        user.setEmail("sa@gmail.com");
        user.setFirstName("sa");
        user.setLastName("la");
        user.setPassword("Sa@123");

        UserEntity userEntity = new UserEntity();
        userEntity.setEmail("sa@gmail.com");
        userEntity.setFirstName("sa");
        userEntity.setLastName("la");
        userEntity.setPassword("Sa@123");
        given(userRepo.findByEmail(user.getEmail())).willReturn(null);
        given(userRepo.save(userEntity)).willAnswer(invocation->invocation.getArgument(0));
        UserEntity savedUserEntity =userService.register(user);
        assertThat(savedUserEntity).isNotNull();
//        when(userService.checkIfUserExist("sa@gmail.com")).thenReturn(true);

        verify( userRepo).save(any(UserEntity.class));
    }


    @Test
    void checkIfUserExist() {
        UserEntity user = new UserEntity();
        user.setEmail("sa@gmail.com");
        user.setId(1L);
        when(userRepo.findByEmail("sa@gmail.com")).thenReturn(user);
        boolean result = userService.checkIfUserExist("sa@gmail.com");
        assertTrue(result);
    }

    @Test
    void verifyUser() {
        UserEntity user = new UserEntity();
        user.setEmail("sa@gmail.com");
        user.setPassword("S@123");
        user.setId(1L);
        when(userRepo.findByEmail("sa@gmail.com")).thenReturn(user);

        assertTrue(userService.verifyUser("sa@gmail.com","S@123"));
//        assertEquals(userRepo.findByEmail("sa@gmail.com").getPassword(),user.getPassword());
    }

    @Test
    void changePassword() {
        UserEntity user = new UserEntity();
        user.setEmail("sa@gmail.com");
        user.setPassword("S@123");
        user.setId(1L);
        when(userRepo.findByEmail("sa@gmail.com")).thenReturn(user);
        String result = userService.changePassword("sa@gmail.com","S@123","Sa@1234");
        assertEquals("Password updated successfully",result);
    }

    @Test
    void resetPassword() {
        UserEntity user = new UserEntity();
        user.setEmail("sa@gmail.com");
        user.setPassword("S@123");
        user.setId(1L);
        when(userRepo.findByEmail("sa@gmail.com")).thenReturn(user);
        String result = userService.resetPassword("sa@gmail.com");
        assertEquals("Your temporary password 'test123', Kinldy reset before continuing",result);
    }
}