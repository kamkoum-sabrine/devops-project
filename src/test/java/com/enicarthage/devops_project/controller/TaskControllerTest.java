package com.enicarthage.devops_project.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    // --------------------
    // GET /health
    // --------------------
    @Test
    void healthShouldReturnUp() throws Exception {
        mockMvc.perform(get("/health"))
                .andExpect(status().isOk())
                .andExpect(content().string("UP"));
    }

    // --------------------
    // GET /hello
    // --------------------
    @Test
    void helloShouldReturnMessage() throws Exception {
        mockMvc.perform(get("/hello"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello DevOps 👋"));
    }

    // --------------------
    // POST /tasks
    // --------------------
    @Test
    void createTaskShouldReturnTask() throws Exception {
        String taskJson = """
                {
                  "title": "Test task",
                  "completed": false
                }
                """;

        mockMvc.perform(post("/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(taskJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Test task"))
                .andExpect(jsonPath("$.completed").value(false));
    }

    // --------------------
    // GET /tasks
    // --------------------
    @Test
    void getTasksShouldReturnList() throws Exception {
        mockMvc.perform(get("/tasks"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }
}
