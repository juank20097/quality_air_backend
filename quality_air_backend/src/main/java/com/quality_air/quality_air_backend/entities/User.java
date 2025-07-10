package com.quality_air.quality_air_backend.entities;

import java.io.Serializable;
import java.sql.Date;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
    
  //bi-directional many-to-one association to Enterprise
  	@ManyToOne
  	@JoinColumn(name="id_rol")
  	private Rol rol;

}
