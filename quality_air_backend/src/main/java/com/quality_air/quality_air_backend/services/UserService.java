package com.quality_air.quality_air_backend.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quality_air.quality_air_backend.entities.User;
import com.quality_air.quality_air_backend.repo.UserRepo;

import jakarta.annotation.PostConstruct;

@Service
public class UserService {

	@Autowired
	private UserRepo userrepo;

	@PostConstruct
	public void insertarDatos() {
		User adminUser = new User();

		adminUser.setName("Admin");
		adminUser.setLastName("Admin");
		adminUser.setDni("12345678");
		adminUser.setEmail("admin@correo.com");
		adminUser.setNickName("admin");
		adminUser.setPassword("admin");
		adminUser.setStatus(true);

		userrepo.save(adminUser);
	}

	public User insert(User enter) {
		return userrepo.save(enter);
	}

	public User update(User enter) {
		return userrepo.save(enter);
	}

	public List<User> getAll() {
		return userrepo.findByStatus();
	}

	public Optional<User> getId(int id) {
		return userrepo.findById(id);
	}

	public String login(String identifier, String password) {
		Optional<User> user = userrepo.findByIdentifierAndPassword(identifier, password);
		return user.isPresent() ? "validPassword" : "invalidPassword";
	}

}
