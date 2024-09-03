package com.quality_air.quality_air_backend.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quality_air.quality_air_backend.entities.User;
import com.quality_air.quality_air_backend.repo.UserRepo;

@Service
public class UserService {

	@Autowired
	private UserRepo userrepo;

	public User insert(User enter) {
		return userrepo.save(enter);
	}
	
	public User update(User enter) {
		return userrepo.save(enter);
	}
	
	public List<User> getAll(){
		return userrepo.findByStatus();
	}	
	
	public Optional<User> getId(int id){
		return userrepo.findById(id);
	}
	
	public String login(String email, String password) {
	    Optional<User> user = userrepo.findByEmailAndPassword(email, password);
	    return user.isPresent() ? "validPassword" : "invalidPassword";
	}


}
