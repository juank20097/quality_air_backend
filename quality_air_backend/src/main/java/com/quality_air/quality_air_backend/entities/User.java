package com.quality_air.quality_air_backend.entities;

import java.io.Serializable;
import java.sql.Date;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

/**
 * Represents a user entity in the application.
 * 
 * This class maps to the "user" table in the database and holds the user's 
 * personal information including name, last name, identity number (DNI), 
 * date of birth, email, nickname, password, and status.
 */
@Schema
@Entity
@Table(name = "user")
@Data
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * Unique identifier for the user.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * First name of the user.
     */
    @Column(name = "name")
    private String name;

    /**
     * Last name of the user.
     */
    @Column(name = "last_name")
    private String lastName;

    /**
     * Identity number of the user.
     * This field is required.
     */
    @Column(name = "dni", nullable = false)
    private String dni;

    /**
     * Date of birth of the user.
     */
    @Temporal(TemporalType.DATE)
    @Column(name = "date")
    private Date date;

    /**
     * Email address of the user.
     * This field is required.
     */
    @Column(name = "email", nullable = false)
    private String email;

    /**
     * Nickname of the user.
     * This field is required.
     */
    @Column(name = "nick_name", nullable = false)
    private String nickName;

    /**
     * Password of the user.
     * This field is required.
     */
    @Column(name = "password", nullable = false)
    private String password;

    /**
     * Status of the user (active or inactive).
     * This field is required.
     */
    @Column(name = "status", nullable = false)
    private boolean status;

    /**
     * Default constructor for User.
     */
    public User() {
    }

    /**
     * Parameterized constructor for User.
     *
     * @param name     First name of the user.
     * @param lastName Last name of the user.
     * @param dni      Identity number of the user.
     * @param email    Email address of the user.
     * @param nickName Nickname of the user.
     * @param password Password of the user.
     * @param status   Status of the user.
     */
    public User(String name, String lastName, String dni, String email, String nickName, String password,
            boolean status) {
        super();
        this.name = name;
        this.lastName = lastName;
        this.dni = dni;
        this.email = email;
        this.nickName = nickName;
        this.password = password;
        this.status = status;
    }

    // Getters and Setters

    /**
     * Gets the unique identifier for the user.
     * 
     * @return the user ID
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets the unique identifier for the user.
     * 
     * @param id the user ID to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Gets the first name of the user.
     * 
     * @return the user's first name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the first name of the user.
     * 
     * @param name the first name to set for the user
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the last name of the user.
     * 
     * @return the user's last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the last name of the user.
     * 
     * @param lastName the last name to set for the user
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Gets the identity number of the user.
     * 
     * @return the user's DNI
     */
    public String getDni() {
        return dni;
    }

    /**
     * Sets the identity number of the user.
     * 
     * @param dni the DNI to set for the user
     */
    public void setDni(String dni) {
        this.dni = dni;
    }

    /**
     * Gets the date of birth of the user.
     * 
     * @return the user's date of birth
     */
    public Date getDate() {
        return date;
    }

    /**
     * Sets the date of birth of the user.
     * 
     * @param date the date of birth to set for the user
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * Gets the email address of the user.
     * 
     * @return the user's email address
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email address of the user.
     * 
     * @param email the email address to set for the user
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the nickname of the user.
     * 
     * @return the user's nickname
     */
    public String getNickName() {
        return nickName;
    }

    /**
     * Sets the nickname of the user.
     * 
     * @param nickName the nickname to set for the user
     */
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    /**
     * Gets the password of the user.
     * 
     * @return the user's password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password of the user.
     * 
     * @param password the password to set for the user
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets the status of the user.
     * 
     * @return true if the user is active, false otherwise
     */
    public boolean isStatus() {
        return status;
    }

    /**
     * Sets the status of the user.
     * 
     * @param status the status to set for the user
     */
    public void setStatus(boolean status) {
        this.status = status;
    }
}
