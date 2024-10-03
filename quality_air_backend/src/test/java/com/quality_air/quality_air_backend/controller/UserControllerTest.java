package com.quality_air.quality_air_backend.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Optional;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.quality_air.quality_air_backend.entities.User;
import com.quality_air.quality_air_backend.services.UserService;

/**
 * Unit tests for the UserController class.
 * 
 * This class contains tests that validate the functionality of the UserController
 * using MockMvc to perform requests and verify responses.
 */
@WebMvcTest(UserController.class)
@ActiveProfiles("test")
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    /**
     * Test to retrieve all users.
     * 
     * This test verifies that the /user endpoint returns a list of users with a
     * 200 OK status.
     * 
     * @throws Exception if an error occurs during the request.
     */
    @Test
    void testGetAllUsers() throws Exception {
        System.out.println("-------------------------------------------------");
        System.out.println("UNIT TEST: Running testGetAllUsers...");

        User user1 = new User("Juan", "Pérez", "12345678A", "juan@example.com", "juanp", "password123", true);
        User user2 = new User("Ana", "Lopez", "98765432B", "ana@example.com", "anal", "password456", true);

        when(userService.getAll()).thenReturn(Arrays.asList(user1, user2));

        mockMvc.perform(get("/user"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].name").value("Juan"))
                .andExpect(jsonPath("$[1].name").value("Ana"));
        System.out.println("UNIT TEST: testGetAllUsers COMPLETED successfully.");
    }

    /**
     * Test to create a new user.
     * 
     * This test verifies that a user can be successfully created via the /user
     * endpoint and checks that the response contains the expected user data.
     * 
     * @throws Exception if an error occurs during the request.
     */
    @Test
    void testCreateUser() throws Exception {
        System.out.println("-------------------------------------------------");
        System.out.println("UNIT TEST: Running testCreateUser...");

        User newUser = new User("Carlos", "Sanchez", "11223344C", "carlos@example.com", "carls", "password789", true);

        when(userService.insert(newUser)).thenReturn(newUser);

        mockMvc.perform(post("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(newUser)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Carlos"))
                .andExpect(jsonPath("$.email").value("carlos@example.com"));
        System.out.println("UNIT TEST: testCreateUser COMPLETED successfully.");
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
    void testUpdateUser() throws Exception {
        System.out.println("-------------------------------------------------");
        System.out.println("UNIT TEST: Running testUpdateUser...");

        User updatedUser = new User();
        updatedUser.setName("Juan");
        updatedUser.setLastName("Pérez");
        updatedUser.setDni("12345678A");
        updatedUser.setEmail("juan_updated@example.com");
        updatedUser.setNickName("juanp");
        updatedUser.setPassword("password123");
        updatedUser.setStatus(true);

        when(userService.update(any(User.class))).thenReturn(updatedUser);

        mockMvc.perform(put("/user/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Juan\",\"lastName\":\"Pérez\",\"dni\":\"12345678A\",\"email\":\"juan_updated@example.com\",\"nickName\":\"juanp\",\"password\":\"password123\",\"status\":true}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("juan_updated@example.com"));

        System.out.println("UNIT TEST: testUpdateUser COMPLETED successfully.");
    }

    /**
     * Test to retrieve a user by ID.
     * 
     * This test verifies that a user can be retrieved via the /user/{id} endpoint
     * and checks that the response contains the expected user data.
     * 
     * @throws Exception if an error occurs during the request.
     */
    @Test
    void testGetUserById() throws Exception {
        System.out.println("-------------------------------------------------");
        System.out.println("UNIT TEST: Running testGetUserById...");

        User user = new User("Juan", "Pérez", "12345678A", "juan@example.com", "juanp", "password123", true);

        when(userService.getId(1)).thenReturn(Optional.of(user));

        mockMvc.perform(get("/user/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Juan"));

        System.out.println("UNIT TEST: testGetUserById COMPLETED successfully.");
    }

    /**
     * Test to authenticate a user.
     * 
     * This test verifies that a user can log in successfully via the /user/login
     * endpoint, checking that the response indicates valid credentials.
     * 
     * @throws Exception if an error occurs during the request.
     */
    @Test
    void testAuthenticateUser() throws Exception {
        System.out.println("-------------------------------------------------");
        System.out.println("UNIT TEST: Running testAuthenticateUser...");

        String identifier = "juanp";
        String password = "password123";
        Map<String, String> response = new HashMap<>();
        response.put("status", "validPassword");

        when(userService.login(identifier, password)).thenReturn("validPassword");

        mockMvc.perform(post("/user/login")
                .param("identifier", identifier)
                .param("password", password))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("validPassword"));
        
        System.out.println("UNIT TEST: testAuthenticateUser COMPLETED successfully.");
    }
}
