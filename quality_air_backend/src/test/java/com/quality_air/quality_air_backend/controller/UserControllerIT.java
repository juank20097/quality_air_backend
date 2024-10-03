package com.quality_air.quality_air_backend.controller;

import com.quality_air.quality_air_backend.entities.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the UserController class.
 * 
 * This class contains tests that validate the functionality of the UserController,
 * ensuring that the endpoints behave as expected.
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class UserControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    /**
     * Test to retrieve all active users.
     * 
     * This test verifies that the /user endpoint returns a list of active users
     * with a 200 OK status.
     * 
     * @throws Exception if an error occurs during the request.
     */
    @Test
    public void testGetAllUsers() throws Exception {
        System.out.println("-------------------------------------------------");
        System.out.println("INTEGRATION TEST: Running testGetAllUsers...");

        mockMvc.perform(get("/user").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
        System.out.println("INTEGRATION TEST: testGetAllUsers COMPLETED successfully.");
    }

    /**
     * Test to insert a new user.
     * 
     * This test verifies that a user can be successfully created via the /user
     * endpoint and checks that the response contains the expected user data.
     * 
     * @throws Exception if an error occurs during the request.
     */
    @Test
    public void testInsertUser() throws Exception {
        System.out.println("-------------------------------------------------");
        System.out.println("INTEGRATION TEST: Running testInsertUser...");

        User user = new User();
        user.setName("Juan");
        user.setLastName("Pérez");
        user.setDni("12345678A");
        user.setEmail("juan@example.com");
        user.setNickName("juanp");
        user.setPassword("password123");
        user.setStatus(true);

        mockMvc.perform(post("/user").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("Juan"));

        System.out.println("INTEGRATION TEST: testInsertUser COMPLETED successfully.");
    }

    /**
     * Test to update an existing user.
     * 
     * This test verifies that a user can be updated successfully via the /user/{id}
     * endpoint, checking that the response contains the updated user data.
     * 
     * @throws Exception if an error occurs during the request.
     */
    @Test
    public void testUpdateUser() throws Exception {
        System.out.println("-------------------------------------------------");
        System.out.println("INTEGRATION TEST: Running testUpdateUser...");

        User user = new User();
        user.setName("Carlos");
        user.setLastName("López");
        user.setDni("12345678A");
        user.setEmail("carlos@example.com");
        user.setNickName("carlosl");
        user.setPassword("newpassword");

        mockMvc.perform(put("/user/1").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("Carlos"));

        System.out.println("INTEGRATION TEST: testUpdateUser COMPLETED successfully.");
    }

    /**
     * Test to retrieve a user by ID.
     * 
     * This test verifies that a user can be retrieved via the /user/{id} endpoint
     * and checks that the response contains the expected user ID.
     * 
     * @throws Exception if an error occurs during the request.
     */
    @Test
    public void testGetUserById() throws Exception {
        System.out.println("-------------------------------------------------");
        System.out.println("INTEGRATION TEST: Running testGetUserById...");

        mockMvc.perform(get("/user/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1));

        System.out.println("INTEGRATION TEST: testGetUserById COMPLETED successfully.");
    }

    /**
     * Test to log in a user.
     * 
     * This test verifies that a user can log in successfully via the /user/login
     * endpoint, checking that the response indicates valid credentials.
     * 
     * @throws Exception if an error occurs during the request.
     */
    @Test
    public void testLoginUser() throws Exception {
        System.out.println("-------------------------------------------------");
        System.out.println("INTEGRATION TEST: Running testLoginUser...");

        Map<String, String> loginRequest = new HashMap<>();
        loginRequest.put("identifier", "admin");
        loginRequest.put("password", "admin");

        String queryString = loginRequest.entrySet().stream()
                .map(entry -> entry.getKey() + "=" + entry.getValue())
                .collect(Collectors.joining("&"));

        mockMvc.perform(post("/user/login?" + queryString).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status").value("validPassword"));

        System.out.println("INTEGRATION TEST: testLoginUser COMPLETED successfully.");
    }
}
