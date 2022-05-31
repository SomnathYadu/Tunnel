package com.tunnel.wanderer.tunnel;

import com.tunnel.wanderer.tunnel.entity.AgeComparator;
import com.tunnel.wanderer.tunnel.entity.User;
import com.tunnel.wanderer.tunnel.service.UserDaoService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
	
	@Autowired
	private UserDaoService service;
	
	Logger logger = LoggerFactory.getLogger("SampleLogger");
	
	
	@PostMapping(path="/users")
	public void createUser(@RequestBody User user) {
		logger.info(user.toString());
		service.createUser(user);
		return;
	}
	
	@PostMapping(path="/users/update")
	public Optional<User> updateUser(@RequestBody User user) {
		Optional<User> response = service.updateUser(user);
		if(response == null) {
			//TODO:: return 404 not found
			return null;
		}
		
		return response;
	}
	
	@GetMapping(path="/users")
	public List<User> getAllUser() {
		return service.getUsers();
	}
	
	@GetMapping(value={"/users/sorted", "/users/sorted/{order}"})
	@ResponseBody
	public List<User> getAllUserSorted(@PathVariable(required = false) Optional<String> order){
		List<User> users = service.getUsers();
		if(order.isPresent() && order.get().equals("reverse")) {
			Comparator<User> reverseComparator = Collections.reverseOrder(new AgeComparator());
			Collections.sort(users, reverseComparator);
		} else {
			Collections.sort(users, new AgeComparator());
		}
		return users;
	}
	
	@GetMapping(path="/users/{id}")
	public Optional<User> getUserById(@PathVariable Long id){
		return service.getUser(id);
	}
}
