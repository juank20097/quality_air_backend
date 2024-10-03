package com.quality_air.quality_air_backend.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.quality_air.quality_air_backend.entities.User;
import com.quality_air.quality_air_backend.services.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.Parameter;

/**
 * Controller class for managing User entities.
 * 
 * This class provides RESTful API endpoints to perform CRUD operations on User entities,
 * including creating, updating, retrieving, and logging in users.
 */
@Tag(name = "User", description = "Users API")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userservice;

    /**
     * Retrieves a list of all active users in the database.
     * 
     * @return a list of active users.
     */
    @Operation(description = "This service returns a list of all active users in the database.", responses = {
            @ApiResponse(responseCode = "200", description = "List of active users successfully returned", content = @Content(mediaType = "application/json", schema = @Schema(implementation = User.class))),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content()) })
    @CrossOrigin(origins = "*")
    @GetMapping
    public List<User> getAll() {
        return userservice.getAll();
    }

    /**
     * Creates a new user in the database.
     * 
     * @param enter the User entity to be created.
     * @return the created User entity.
     */
    @Operation(description = "This service allows you to create a user in the database.", requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "New user data", required = true, content = @Content(examples = @ExampleObject(summary = "Basic User", value = "{\"name\": \"Juan\", \"lastName\": \"Pérez\", \"dni\": \"12345678A\", \"date\": \"2024-09-06\", \"email\": \"juan@example.com\", \"nickName\": \"juanp\", \"password\": \"password123\", \"status\": true}"))))
    @CrossOrigin(origins = "*")
    @PostMapping
    public User insert(@RequestBody User enter) {
        return userservice.insert(enter);
    }

    /**
     * Updates an existing user in the database.
     * 
     * @param enter the User entity with updated data.
     * @param id    the ID of the user to be updated.
     * @return the updated User entity.
     */
    @Operation(description = "This service allows you to update a database user.", requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "User data to update", required = true, content = @Content(examples = @ExampleObject(summary = "Example of a user with all fields", value = "{\"name\": \"Juan\", \"lastName\": \"Pérez\", \"dni\": \"12345678A\", \"date\": \"2024-09-06T15:50:14.462Z\", \"email\": \"juan@example.com\", \"nickName\": \"juanp\", \"password\": \"password123\", \"status\": true}"))), parameters = {
            @Parameter(name = "id", description = "User ID to update", required = true, schema = @Schema(type = "integer")) }, responses = {
            @ApiResponse(responseCode = "200", description = "User successfully updated", content = @Content(mediaType = "application/json", schema = @Schema(implementation = User.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request. The ID may not exist or the user data may be incorrect.", content = @Content()),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content()),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content()) })
    @CrossOrigin(origins = "*")
    @PutMapping(path = { "/{id}" })
    public User update(@RequestBody User enter, @PathVariable("id") int id) {
        enter.setId(id);
        return userservice.update(enter);
    }

    /**
     * Retrieves a specific user based on their ID.
     * 
     * @param id the ID of the user to retrieve.
     * @return an Optional containing the user if found, otherwise empty.
     */
    @Operation(description = "This service returns a specific user based on their ID.", parameters = {
            @Parameter(name = "id", description = "User ID to search", schema = @Schema(type = "integer")) }, responses = {
            @ApiResponse(responseCode = "200", description = "User found successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = User.class))),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content()),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content()) })
    @CrossOrigin(origins = "*")
    @GetMapping(path = { "/{id}" })
    public Optional<User> getId(@PathVariable("id") int id) {
        return userservice.getId(id);
    }

    /**
     * Authenticates a user using their email or nickname and password.
     * 
     * @param identifier the email or nickname of the user.
     * @param password   the password of the user.
     * @return a ResponseEntity containing the authentication status.
     */
    @Operation(description = "This service allows you to log in using an ID and password.", parameters = {
            @Parameter(name = "identifier", description = "Identifier: email or nickName", schema = @Schema()),
            @Parameter(name = "password", description = "Password", required = true, schema = @Schema()) }, responses = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json", schema = @Schema(example = "{\"status\": \"validPassword\"}"))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content()),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content()) })
    @CrossOrigin(origins = "*")
    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> authenticate(@RequestParam("identifier") String identifier,
            @RequestParam("password") String password) {
        Map<String, String> response = new HashMap<>();
        String result = userservice.login(identifier, password);
        response.put("status", result);
        return ResponseEntity.ok(response);
    }
}
