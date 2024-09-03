package com.quality_air.quality_air_backend.controller;

import java.util.List;
import java.util.Optional;

import javax.management.openmbean.OpenType;

import org.springframework.beans.factory.annotation.Autowired;
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

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userservice;

	@CrossOrigin(origins = "*")
	@GetMapping
	public List<User> getAll() {
		return userservice.getAll();
	}

	@CrossOrigin(origins = "*")
	@PostMapping
	public User insert(@RequestBody User enter) {
		return userservice.insert(enter);
	}	

	@CrossOrigin(origins = "*")
	@PutMapping(path = { "/{id}" })
	public User update(@RequestBody User enter, @PathVariable("id") int id) {
		enter.setId(id);
		return userservice.update(enter);
	}

	@CrossOrigin(origins = "*")
	@GetMapping(path = { "/{id}" })
	public Optional<User> getId(@PathVariable("id") int id) {
		return userservice.getId(id);
	}
	
	@CrossOrigin(origins = "*")
    @PostMapping("/login")
    public String authenticate(@RequestParam("correo") String correo, @RequestParam("password") String password) {
    	System.out.println("CORREO CONTROLER: "+correo+" PASSWORD CONTROLER"+password);
    	return userservice.login(correo, password);
    }

}
