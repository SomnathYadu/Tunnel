package com.tunnel.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import com.tunnel.exceptions.UserNotFoundException;
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

	Logger logger = LoggerFactory.getLogger("SampleLogger");
	
	public User createUser(User user) throws UserNameAlreadyExistsException {
		//Check if "username" is already existing and throw error if it doesn't
		List<User> users = userRepository.findByUserName(user.getUsername());
		if (users != null && users.size() > 0) {
			throw new UserNameAlreadyExistsException("Username "+ user.getUsername() + " already exist");
		}

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

	public Optional<User> updateUser(User userUpdate) {
		Optional<User> user = userRepository.findById(userUpdate.getUserId());
		if (user == null) {
			return user;
		}

		userRepository.save(userUpdate);
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
