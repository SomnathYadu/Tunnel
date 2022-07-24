package com.tunnel.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import com.tunnel.entity.Role;
import com.tunnel.entity.UserRole;
import com.tunnel.exceptions.UserNotFoundException;
import com.tunnel.repository.RoleRepository;
import com.tunnel.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tunnel.entity.User;
import com.tunnel.exceptions.UserNameAlreadyExistsException;

@SuppressWarnings("unused")
@Repository
@Transactional
public class UserService {

	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	Logger logger = LoggerFactory.getLogger("SampleLogger");
	
	public User createUser(User user) throws UserNameAlreadyExistsException {
		//Check if "username" is already existing and throw error if it doesn't

		List<User> users = userRepository.findByUserName(user.getUsername());

		if (users != null && users.size() > 0) {
			throw new UserNameAlreadyExistsException("Username "+ user.getUsername() + " already exist");
		}

		Role role = roleRepository.findByRoleName("SITE_USER").get(0);

		UserRole userRole = new UserRole();
		userRole.setUser(user);
		userRole.setRole(role);

		user.setUserRole(userRole);

		return userRepository.save(user);
	}

	public List<User> getUsers() {
		return userRepository.findAll();
	}

	public Optional<User> getUser(Long id) {
		return userRepository.findById(id);
	}

	public User getUserByUserName(String username) {
		List<User> users = userRepository.findByUserName(username);

		if(users == null || users.size() == 0)
			throw new UserNotFoundException("User does not exist");

		return users.get(0);
	}

	public Optional<User> updateUser(User user) {
		Optional<User> users = userRepository.findById(user.getUserId());
		if (users.isEmpty()) {
			throw new UserNotFoundException("Username "+ user.getUsername() + " already exist");
		}

		userRepository.save(users.get());
		return users;
	}

	public User updateUserPassword(String password, String username) {
		List<User> users = userRepository.findByUserName(username);
		if (users == null || users.isEmpty()) {
			throw new UserNotFoundException("Username "+ username + " already exist");
		}

		User user = users.get(0);
		user.setPassword(password);
		userRepository.save(user);
		return user;
	}

	public User findByUserName(String username) {
		List<User> result = userRepository.findByUserName(username);
		if(result.size() == 0)
			throw new UserNotFoundException("User " + username + " does not exist");

		User user = result.get(0);
		return user;
	}
}
