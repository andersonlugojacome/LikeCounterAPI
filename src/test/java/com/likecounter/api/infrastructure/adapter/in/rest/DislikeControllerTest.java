package com.likecounter.api.infrastructure.adapter.in.rest;

import com.likecounter.api.infrastructure.adapter.out.persistence.repository.SpringDataDislikeCounterRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class DislikeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private SpringDataDislikeCounterRepository repository;

    @BeforeEach
    void setUp() {
        repository.deleteAll();
    }

    @Test
    void shouldReturnCurrentDislikeCount() throws Exception {
        mockMvc.perform(get("/dislikes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.count").value(0));
    }

    @Test
    void shouldRegisterDislike() throws Exception {
        mockMvc.perform(post("/dislikes"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.message").value("Dislike registrado correctamente"))
                .andExpect(jsonPath("$.count").value(1));
    }

    @Test
    void shouldIncrementDislikeCount() throws Exception {
        mockMvc.perform(post("/dislikes"));
        mockMvc.perform(post("/dislikes"));
        mockMvc.perform(post("/dislikes"));

        mockMvc.perform(get("/dislikes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.count").value(3));
    }

    @Test
    void shouldPersistDislikeCountBetweenRequests() throws Exception {
        mockMvc.perform(post("/dislikes"));
        mockMvc.perform(post("/dislikes"));

        mockMvc.perform(get("/dislikes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.count").value(2));

        mockMvc.perform(post("/dislikes"));

        mockMvc.perform(get("/dislikes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.count").value(3));
    }
}
