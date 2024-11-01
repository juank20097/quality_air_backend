package com.quality_air.quality_air_backend.services;

import java.util.Date;
import java.text.SimpleDateFormat;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quality_air.quality_air_backend.entities.User;
import com.quality_air.quality_air_backend.repo.UserRepo;

import io.swagger.v3.oas.annotations.Hidden;
import jakarta.annotation.PostConstruct;

/**
 * Service class for managing User entities.
 * 
 * This class provides methods to perform operations on User entities, 
 * including inserting, updating, retrieving, and logging in users.
 */
@Hidden
@Service
public class UserService {

    @Autowired
    private UserRepo userrepo;

    /**
     * Initializes default data by inserting an admin user into the database.
     */
    @PostConstruct
    public void insertarDatos() {
        User adminUser = new User();

        adminUser.setName("Admin");
        adminUser.setLastName("Admin");
        adminUser.setDni("12345678");
        adminUser.setEmail("admin@correo.com");
        try {
            // Usa java.util.Date
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            Date parsedDate = formatter.parse("28/10/2012");
            // Convierte a java.sql.Date si es necesario
            adminUser.setDate(new java.sql.Date(parsedDate.getTime())); 
        } catch (Exception e) {
            e.printStackTrace();
        }
        adminUser.setNickName("admin");
        adminUser.setPassword("admin");
        adminUser.setStatus(true);

        userrepo.save(adminUser);
    }

    /**
     * Inserts a new user into the database.
     * 
     * @param enter the User entity to be inserted.
     * @return the saved User entity.
     */
    public User insert(User enter) {
        return userrepo.save(enter);
    }

    /**
     * Updates an existing user in the database.
     * 
     * @param enter the User entity to be updated.
     * @return the updated User entity.
     */
    public User update(User enter) {
        return userrepo.save(enter);
    }

    /**
     * Retrieves a list of all active users.
     * 
     * @return a list of users with active status.
     */
    public List<User> getAll() {
        return userrepo.findByStatus();
    }

    /**
     * Retrieves a user by their ID.
     * 
     * @param id the ID of the user to be retrieved.
     * @return an Optional containing the user if found, otherwise empty.
     */
    public Optional<User> getId(int id) {
        return userrepo.findById(id);
    }

    /**
     * Validates user login credentials.
     * 
     * @param identifier the email or nickname of the user.
     * @param password   the password of the user.
     * @return a string indicating whether the credentials are valid or not.
     */
    public String login(String identifier, String password) {
        Optional<User> user = userrepo.findByIdentifierAndPassword(identifier, password);
        return user.isPresent() ? "validPassword" : "invalidPassword";
    }
}
