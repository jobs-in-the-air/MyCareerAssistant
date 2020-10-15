package com.air.careerassistant;

import com.air.careerassistant.controller.ApplicationUserController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

// Necessary to manually import
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

// necessary matchers to manually import
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class CareerAssistantApplicationTests {


    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ApplicationUserController applicationUserController;

    @Test
    void contextLoads() {
    }

    @Test
    public void homePageShouldRenderWithForm() throws Exception {
        this.mockMvc.perform(get("/"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("<form action=\"/jobsearch\" method=\"POST\">")));
    }


    @Test
    public void testLoginBeforeAccess() throws Exception {
        this.mockMvc.perform(get("/allmyjobs"))
                .andExpect(redirectedUrl("http://localhost/login"));
    }


}



