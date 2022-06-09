package hr.tvz.pandza.hardwareapp.security.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
class AuthenticationControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @Test
    void login() throws Exception {
        Map<String,Object> body = new HashMap<>();
        ObjectMapper objectMapper = new ObjectMapper();

        body.put("username", "admin");
        body.put("password", "admin");


        this.mockMvc.perform
                        (post("/authentication/login")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(body)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.jwt").isNotEmpty());
    }

    @Test
    void login_whenPasswordWrong() throws Exception {
        Map<String,Object> body = new HashMap<>();
        ObjectMapper objectMapper = new ObjectMapper();

        body.put("username", "admin");
        body.put("password", "pass");

        this.mockMvc.perform
                        (post("/authentication/login")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(body)))
                .andExpect(status().isBadRequest());
    }
}