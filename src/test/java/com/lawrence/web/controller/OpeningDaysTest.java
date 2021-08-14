package com.lawrence.web.controller;

import com.lawrence.service.OpeningDayService;
import com.lawrence.web.dto.OpeningDayDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest //This annotation works by creating the ApplicationContext used
// in our tests through SpringApplication. It starts the embedded server,
// creates a web environment and then enables @Test methods to do integration testing.
@AutoConfigureMockMvc//Enables all auto-configuration related to MockMvc and ONLY MockMvc .
// Again, this is a subset of overall auto-configuration
class OpeningDaysTest {

    @Autowired
    MockMvc mockMvc;
    @MockBean
    OpeningDayService openingDayService;
    @Test
    void addOpeningDays() throws Exception {
        OpeningDayDto openingDayDto = new OpeningDayDto();
        openingDayDto.setClosingTime("");
        openingDayDto.setOpeningTime("");
        openingDayService.addOpeningDay(openingDayDto);
        this.mockMvc.perform(post("/addOpeningDays")
                .contentType(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(view().name("/result"))
                .andExpect(model().attribute("result","opening day added successfully"));
    }
}