package com.lawrence.web.controller;

import com.lawrence.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest //This annotation works by creating the ApplicationContext used
// in our tests through SpringApplication. It starts the embedded server,
// creates a web environment and then enables @Test methods to do integration testing.
@AutoConfigureMockMvc//Enables all auto-configuration related to MockMvc and ONLY MockMvc .
// Again, this is a subset of overall auto-configuration

class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserService userService;
    @Test
    void login() throws Exception {
        when(userService.verifyUser("soc@gmail.com","soc@123")).thenReturn(true);
        mockMvc.perform(get("/login")
                .param("username","soc@gmail.com")
                .param("password","soc@123")
                .contentType(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(view().name("/home"));

    }

    @Test
    void changePassword() throws Exception {

        when(userService.changePassword("soc@gmail.com","123","345")).thenReturn("Password updated successfully");
        mockMvc.perform(post("/changePassword")
                .param("username","soc@gmail.com")
                .param("oldPassword","123")
                .param("newPassword","345")
                .contentType(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(view().name("/result"))
                .andExpect(model().attribute("result","Password updated successfully"));

    }

    @Test
    void resetPassword() throws Exception {
        when(userService.resetPassword("soc@gmail.com")).thenReturn("Your temporary password 'test123', Kinldy reset before continuing");
        mockMvc.perform(post("/resetPassword")
                .param("username","soc@gmail.com")

                .contentType(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(view().name("/result"))
                .andExpect(model().attribute("result","Your temporary password 'test123', Kinldy reset before continuing"));

    }
}