package com.lawrence.web.controller;

import com.lawrence.model.UserEntity;
import com.lawrence.service.UserService;
import com.lawrence.web.dto.UserData;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest //This annotation works by creating the ApplicationContext used
// in our tests through SpringApplication. It starts the embedded server,
// creates a web environment and then enables @Test methods to do integration testing.
@AutoConfigureMockMvc
//Enables all auto-configuration related to MockMvc and ONLY MockMvc .
// Again, this is a subset of overall auto-configuration

class RegistrationTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserService userService;
//
    @Test
    void userRegistration() throws Exception {
        UserData user = new UserData();
        user.setPassword("password");
        user.setEmail("sa@gmail.com");
        user.setFirstName("sa");
        user.setLastName("la");

        UserEntity userEntity = new UserEntity();
        userEntity.setPassword("password");
        userEntity.setEmail("sa@gmail.com");
        userEntity.setFirstName("sa");
        userEntity.setLastName("la");
        userEntity.setId(1L);
        when(userService.register(user)).thenReturn(userEntity);


        mockMvc.perform(post("/register")
                .param("username","soc@gmail.com")
                .param("username","soc@gmail.com")
                .contentType(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(view().name("/result"))
//                .andExpect(model().attribute("registrationMsg", hasItem(allOf(
//
//                        hasProperty("firstname", is("sa"))))))
                        ;

    }
}