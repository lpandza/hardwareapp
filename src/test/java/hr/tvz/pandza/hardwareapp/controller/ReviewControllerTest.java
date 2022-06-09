package hr.tvz.pandza.hardwareapp.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
class ReviewControllerTest {

    private static final String ADMIN_TOKEN =
            "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTY1NTEzMTQ0NywiaWF0Ijo" +
                    "xNjU0NTI2NjQ3LCJhdXRob3JpdGllcyI6IlJPTEVfQURNSU4ifQ.5gKocY4xPc-SmR6mjZKIMBTf1p-Ce2z5UqAHItApn64Y6rtq" +
                    "OmYqGfEcDJQly71jQV12pJhTUtuYc0PTr7YKMw";

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getAll() throws Exception {
        this.mockMvc.perform
                        (get("/review")
                                .header(HttpHeaders.AUTHORIZATION, "Bearer " + ADMIN_TOKEN)
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].title").value("ReviewTitle"))
                .andExpect(jsonPath("$[0].text").value("Prvi review text..."))
                .andExpect(jsonPath("$[0].score").value(2))
                .andExpect(jsonPath("$").isNotEmpty());
    }

    @Test
    void getByHardwareCode() throws Exception {
        this.mockMvc.perform
                        (get("/review/1111")
                                .header(HttpHeaders.AUTHORIZATION, "Bearer " + ADMIN_TOKEN)
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].title").value("ReviewTitle"))
                .andExpect(jsonPath("$[0].text").value("Prvi review text..."))
                .andExpect(jsonPath("$[0].score").value(2))
                .andExpect(jsonPath("$").isNotEmpty());
    }
}