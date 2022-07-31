package com.tunnel.controller;

import java.net.URI;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.tunnel.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.tunnel.entity.AgeComparator;
import com.tunnel.entity.User;
import com.tunnel.exceptions.UserNameAlreadyExistsException;
import com.tunnel.exceptions.UserNotFoundException;
import com.tunnel.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService service;

	@Autowired
	UserRepository userRepository;

	Logger logger = LoggerFactory.getLogger("SampleLogger");

	@PostMapping(path = "/users")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {

		// Using copy constructor to verify age of user
		User verifiedUser = new User(user);
		User savedUser = service.createUser(verifiedUser);

		return new ResponseEntity<Object>(savedUser, HttpStatus.CREATED);
	}

	@PostMapping(path = "/users/update")
	public Optional<User> updateUser(@RequestBody User user) {
		User verifiedUser = new User(user);
		Optional<User> response = service.updateUser(verifiedUser);
		if (response.isEmpty()) {
			// TODO:: return 404 not found
			return null;
		}

		return response;
	}

	@PostMapping(path = "/users/update/password/{password}")
	public User updateUserPassword(@RequestBody User user, @RequestParam String password) {
		User response = service.updateUserPassword(password, user.getUsername());

		return response;
	}

	@GetMapping(path = "/users")
	public List<User> retrieveAllUser() {
		return service.getUsers();
	}

	@GetMapping(value = { "/users/sorted", "/users/sorted/{order}" })
	@ResponseBody
	public List<User> getAllUserSorted(@PathVariable(required = false) Optional<String> order) {
		List<User> users = service.getUsers();
		if (order.isPresent() && order.get().equals("reverse")) {
			Comparator<User> reverseComparator = Collections.reverseOrder(new AgeComparator());
			Collections.sort(users, reverseComparator);
		} else {
			Collections.sort(users, new AgeComparator());
		}
		return users;
	}

	@GetMapping(path = "/users/{id}")
	public EntityModel<Optional<User>> getUserById(@PathVariable Long id) {
		Optional<User> user = service.getUser(id);
		if(user.isEmpty())
			throw new UserNotFoundException("User with id " + id + " not found");
		
		EntityModel<Optional<User>> entityModel = EntityModel.of(user);
		
		WebMvcLinkBuilder linkToUsers = WebMvcLinkBuilder.linkTo(
				WebMvcLinkBuilder.methodOn(this.getClass()).retrieveAllUser());
		
		entityModel.add(linkToUsers.withRel("all-users"));
		return entityModel;
	}

	@GetMapping(path = "/test")
	public Object getUserById() {
		return userRepository.getRoleName("wanda");
//		return q;
	}
}
