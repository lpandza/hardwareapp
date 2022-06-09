package hr.tvz.pandza.hardwareapp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@SpringBootTest
@AutoConfigureMockMvc
class HardwareControllerTest {

    private static final String ADMIN_TOKEN =
            "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTY1NTEzMTQ0NywiaWF0Ijo" +
            "xNjU0NTI2NjQ3LCJhdXRob3JpdGllcyI6IlJPTEVfQURNSU4ifQ.5gKocY4xPc-SmR6mjZKIMBTf1p-Ce2z5UqAHItApn64Y6rtq" +
            "OmYqGfEcDJQly71jQV12pJhTUtuYc0PTr7YKMw";

    private static final String USER_TOKEN =
            "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ1c2VyIiwiZXhwIjoxNjU1MzA3NDYzLCJpYXQiOjE2NTQ3MDI2NjMsImF1dGhvcml0" +
                    "aWVzIjoiUk9MRV9VU0VSIn0.FjMBEFQGAjFaIAzmFKKl0c415WU2IF8zbAupoxn2eFSONuGnoIBoTvJ3FGe_DFMJNFT9" +
                    "bLVlvhZ5BAMMe087fg";

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getAll() throws Exception {
        this.mockMvc.perform
                        (get("/hardware")
                                .header(HttpHeaders.AUTHORIZATION, "Bearer " + ADMIN_TOKEN)
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$[0].code").value(1111))
                .andExpect(jsonPath("$[0].name").value("Razer DeathAdder"))
                .andExpect(jsonPath("$[0].price").value(300D))
                .andExpect(jsonPath("$[0].reviews").isNotEmpty())
                .andExpect(jsonPath("$").isNotEmpty());
    }

    @Test
    void getHardwareByCode() throws Exception {
        this.mockMvc.perform
                        (get("/hardware/1111")
                                .header(HttpHeaders.AUTHORIZATION, "Bearer " + ADMIN_TOKEN)
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.code").value(1111))
                .andExpect(jsonPath("$.name").value("Razer DeathAdder"))
                .andExpect(jsonPath("$.price").value(300D))
                .andExpect(jsonPath("$.reviews").isNotEmpty())
                .andExpect(jsonPath("$").isNotEmpty());
    }

    @Transactional
    @Test
    void save() throws Exception {
        Map<String,Object> body = new HashMap<>();
        ObjectMapper objectMapper = new ObjectMapper();


        body.put("name", "name");
        body.put("code", "123");
        body.put("price", "200");
        body.put("type", "CPU");
        body.put("quantity", "12");

        this.mockMvc.perform
                        (post("/hardware")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + ADMIN_TOKEN)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(body)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.code").value(123))
                .andExpect(jsonPath("$.name").value("name"))
                .andExpect(jsonPath("$.price").value(200D))
                .andExpect(jsonPath("$").isNotEmpty());
    }

    @Transactional
    @Test
    void save_asRegularUser() throws Exception {
        Map<String,Object> body = new HashMap<>();
        ObjectMapper objectMapper = new ObjectMapper();


        body.put("name", "name");
        body.put("code", "123");
        body.put("price", "1234");
        body.put("type", "CPU");
        body.put("quantity", "12");

        this.mockMvc.perform
                        (post("/hardware")
                                .header(HttpHeaders.AUTHORIZATION, "Bearer " + USER_TOKEN)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(body)))
                .andExpect(status().isForbidden());
    }

    @Transactional
    @Test
    void update_AsAdmin() throws Exception {
        Map<String,Object> body = new HashMap<>();
        ObjectMapper objectMapper = new ObjectMapper();


        body.put("name", "newName");
        body.put("code", "1111");
        body.put("price", "200");
        body.put("type", "CPU");
        body.put("quantity", "12");

        this.mockMvc.perform
                        (put("/hardware/1111")
                                .header(HttpHeaders.AUTHORIZATION, "Bearer " + ADMIN_TOKEN)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(body)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("newName"))
                .andExpect(jsonPath("$.price").value(200D))
                .andExpect(jsonPath("$").isNotEmpty());
    }

    @Transactional
    @Test
    void delete_AsAdmin() throws Exception {
        this.mockMvc.perform
                        (delete("/hardware/1111")
                                .header(HttpHeaders.AUTHORIZATION, "Bearer " + ADMIN_TOKEN)
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

}